package com.example.webpos.order.service;

import com.example.webpos.common.error.exception.ItemInfoEmptyException;
import com.example.webpos.common.error.exception.ItemNotFoundException;
import com.example.webpos.common.error.exception.MemberNotFoundException;
import com.example.webpos.item.domain.Item;
import com.example.webpos.item.repository.ItemRepository;
import com.example.webpos.member.domain.Member;
import com.example.webpos.member.repository.MemberRepository;
import com.example.webpos.order.domain.OrderItem;
import com.example.webpos.order.domain.Orders;
import com.example.webpos.order.dto.OrdersCreateReq;
import com.example.webpos.order.dto.OrdersInfo;
import com.example.webpos.order.dto.OrdersRes;
import com.example.webpos.order.repository.OrderItemRepository;
import com.example.webpos.order.repository.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final MemberRepository memberRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrdersRepository ordersRepository;
    private final ItemRepository itemRepository;

    @Override
    public List<OrdersInfo> list() {
        return ordersRepository.findAll().stream().map(orders -> OrdersInfo.of(orders)).toList();
    }

    @Override
    public OrdersInfo get(Long orderId) {
        Orders orders = ordersRepository.findById(orderId).orElseGet(null);

        List<OrderItem> orderItemList = orderItemRepository.findByOrder(orderId);

        List<OrdersInfo.ItemInfo> itemInfos = orderItemList.stream().map(orderItem -> OrdersInfo.ItemInfo.of(orderItem.getItem(), orderItem.getAmount())).toList();

        return OrdersInfo.of(orders, itemInfos);
    }

    @Override
    @Transactional
    public List<OrdersRes> order(Long currentMemberId, OrdersCreateReq ordersCreateReqList) {
        Member member = findMember(currentMemberId);

        validateItemInfo(ordersCreateReqList.getItemInfos());

        Orders orders = Orders.createOrders(member, ordersCreateReqList.getPeopleCount());

        List<OrderItem> orderItems = new ArrayList<>();

        for (OrdersCreateReq.ItemInfo itemInfo : ordersCreateReqList.getItemInfos()) {
            OrderItem orderItem = new OrderItem();
            Item item = itemRepository.findById(itemInfo.getItemId()).orElseThrow(ItemNotFoundException::new);
            orderItem.setItem(item);
            orderItem.setOrders(orders);
            orderItem.setAmount(itemInfo.getAmount());
            orderItems.add(orderItem);
        }

        return orderItemRepository.saveAll(orderItems)
                .stream()
                .map(orderItem -> OrdersRes.of(orderItem, orderItem.getItem(), orderItem.getOrders()))
                .toList();
    }

    private Member findMember(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow(MemberNotFoundException::new);
    }

    private void validateItemInfo(List<OrdersCreateReq.ItemInfo> itemInfos) {
        if (itemInfos.isEmpty()) {
            throw new ItemInfoEmptyException();
        }
    }
}

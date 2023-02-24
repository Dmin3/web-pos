package com.example.webpos.order.service;

import com.example.webpos.common.error.exception.ItemNotFoundException;
import com.example.webpos.item.domain.Item;
import com.example.webpos.item.repository.ItemRepository;
import com.example.webpos.order.domain.OrderItem;
import com.example.webpos.order.domain.OrderStatus;
import com.example.webpos.order.domain.Orders;
import com.example.webpos.order.dto.OrdersCreateReq;
import com.example.webpos.order.dto.OrdersRes;
import com.example.webpos.order.repository.OrderItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderItemRepository orderItemRepository;
    private final ItemRepository itemRepository;

    @Override
    public List<OrdersRes> list(Long orderId) {
        List<OrderItem> orderItemList = orderItemRepository.findByOrder(orderId);
        for (OrderItem orderItem : orderItemList) {
            System.out.println("orderItem : " + orderItem.toString());
        }
        return orderItemList.stream().map(orderItem -> OrdersRes.of(orderItem, orderItem.getItem(), orderItem.getOrders())).toList();
    }

    @Override
    @Transactional
    public List<OrdersRes> order(List<OrdersCreateReq> ordersCreateReqList) {
        Orders orders = new Orders(OrderStatus.START);

        List<OrderItem> orderItems = new ArrayList<>();

        for (OrdersCreateReq ordersCreateReq : ordersCreateReqList) {
            OrderItem orderItem = new OrderItem();
            Item item = itemRepository.findById(ordersCreateReq.getItemId()).orElseThrow(ItemNotFoundException::new);
            orderItem.addItem(item);
            orderItem.addOrders(orders);
            orderItem.setAmount(ordersCreateReq.getAmount());
            orderItems.add(orderItem);
        }

        return orderItemRepository.saveAll(orderItems)
                .stream()
                .map(orderItem -> OrdersRes.of(orderItem, orderItem.getItem(), orderItem.getOrders()))
                .toList();
    }
}

package com.example.webpos.service.order;

import com.example.webpos.common.error.exception.ItemNotFoundException;
import com.example.webpos.domain.item.Item;
import com.example.webpos.domain.orderitem.OrderItem;
import com.example.webpos.domain.orderitem.OrderStatus;
import com.example.webpos.domain.orders.Orders;
import com.example.webpos.dto.orders.OrderItemInfo;
import com.example.webpos.dto.orders.OrdersCreateReq;
import com.example.webpos.dto.orders.OrdersRes;
import com.example.webpos.repository.item.ItemRepository;
import com.example.webpos.repository.orderitem.OrderItemRepository;
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
    @Transactional
    public List<OrdersRes> order(OrdersCreateReq ordersCreateReq) {
        Orders orders = new Orders(OrderStatus.START);

        List<OrderItem> orderItems = new ArrayList<>();

        for (OrderItemInfo orderItemInfo : ordersCreateReq.getOrderItemInfoList()) {
            OrderItem orderItem = new OrderItem();
            Item item = itemRepository.findById(orderItemInfo.getItemId()).orElseThrow(ItemNotFoundException::new);
            orderItem.setAmount(orderItemInfo.getAmount());
            orderItem.addItem(item);
            orderItem.addOrders(orders);
            orderItems.add(orderItem);
        }

        return orderItemRepository.saveAll(orderItems)
                .stream()
                .map(orderItem -> OrdersRes.of(orderItem, orderItem.getItem(), orderItem.getOrders()))
                .toList();
    }
}

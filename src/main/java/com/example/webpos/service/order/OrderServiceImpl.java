package com.example.webpos.service.order;

import com.example.webpos.common.error.exception.ItemNotFoundException;
import com.example.webpos.domain.item.Item;
import com.example.webpos.domain.orderitem.OrderItem;
import com.example.webpos.domain.orderitem.OrderStatus;
import com.example.webpos.domain.orders.Orders;
import com.example.webpos.dto.orders.OrdersInfo;
import com.example.webpos.dto.orders.OrdersReq;
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
    public List<OrdersRes> order(OrdersReq ordersReq) {
        Orders orders = new Orders(OrderStatus.START);

        List<OrderItem> orderItems = new ArrayList<>();

        for (OrdersInfo ordersInfo : ordersReq.getOrdersInfoList()) {
            OrderItem orderItem = new OrderItem();
            Item item = itemRepository.findById(ordersInfo.getItemId()).orElseThrow(ItemNotFoundException::new);
            orderItem.setAmount(ordersInfo.getAmount());
            orderItem.addItem(item);
            orderItem.addOrders(orders);
            orderItems.add(orderItem);
        }

        return orderItemRepository.saveAll(orderItems).stream().map(oi -> oi.toResult()).toList();
    }
}

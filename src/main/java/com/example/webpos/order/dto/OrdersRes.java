package com.example.webpos.order.dto;

import com.example.webpos.item.domain.Item;
import com.example.webpos.order.domain.OrderItem;
import com.example.webpos.order.domain.OrderStatus;
import com.example.webpos.order.domain.Orders;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class OrdersRes {
    private Long id;
    private Long itemId;
    private String itemName;
    private Long ordersId;
    private OrderStatus orderStatus;
    private Integer amount;
    private Integer price;

    public OrdersRes(OrderItem orderItem, Item item, Orders orders) {
        this.id = orderItem.getId();
        this.itemId = item.getId();
        this.ordersId = orders.getId();
        this.itemName = item.getName();
        this.amount = orderItem.getAmount();
        this.price = item.getPrice();
        this.orderStatus = orders.getOrderStatus();
    }

    public static OrdersRes of(OrderItem orderItem, Item item, Orders orders) {
        return new OrdersRes(orderItem, item, orders);
    }
}

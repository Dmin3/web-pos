package com.example.webpos.dto.orders;

import com.example.webpos.domain.item.Item;
import com.example.webpos.domain.orderitem.OrderItem;
import com.example.webpos.domain.orderitem.OrderStatus;
import com.example.webpos.domain.orders.Orders;
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

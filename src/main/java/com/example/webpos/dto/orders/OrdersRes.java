package com.example.webpos.dto.orders;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class OrdersRes {
    private Long id;
    private Long itemId;
    private Long ordersId;
    private String itemName;
    private Integer amount;
    private Integer price;

    public OrdersRes(Long id, Long itemId, Long ordersId, String itemName, Integer amount, Integer price) {
        this.id = id;
        this.itemId = itemId;
        this.ordersId = ordersId;
        this.itemName = itemName;
        this.amount = amount;
        this.price = price;
    }
}

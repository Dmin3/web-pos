package com.example.webpos.order.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class OrdersCreateReq {
    private Long itemId;
    private int amount;

    public OrdersCreateReq(Long itemId, int amount) {
        this.itemId = itemId;
        this.amount = amount;
    }
}

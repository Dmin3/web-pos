package com.example.webpos.order.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class OrdersCreateReq {
    private List<OrderItemInfo> orderItemInfoList;
}

package com.example.webpos.dto.orders;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class OrdersCreateReq {
    private List<OrderItemInfo> orderItemInfoList;
}

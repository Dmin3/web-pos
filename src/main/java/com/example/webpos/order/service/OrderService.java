package com.example.webpos.order.service;

import com.example.webpos.order.dto.OrdersCreateReq;
import com.example.webpos.order.dto.OrdersRes;

import java.util.List;

public interface OrderService {
    List<OrdersRes> order(List<OrdersCreateReq> ordersCreateReq);

    List<OrdersRes> list(Long orderId);
}

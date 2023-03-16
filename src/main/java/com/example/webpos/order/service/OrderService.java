package com.example.webpos.order.service;

import com.example.webpos.order.dto.OrdersCreateReq;
import com.example.webpos.order.dto.OrdersInfo;
import com.example.webpos.order.dto.OrdersRes;

import java.util.List;

public interface OrderService {
    List<OrdersRes> order(Long currentMemberId, OrdersCreateReq ordersCreateReq);

    OrdersInfo get(Long orderId);

    List<OrdersInfo> list();
}

package com.example.webpos.service.order;

import com.example.webpos.dto.orders.OrdersCreateReq;
import com.example.webpos.dto.orders.OrdersRes;

import java.util.List;

public interface OrderService {
    List<OrdersRes> order(OrdersCreateReq ordersCreateReq);
}

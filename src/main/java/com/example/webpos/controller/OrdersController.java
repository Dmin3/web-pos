package com.example.webpos.controller;

import com.example.webpos.dto.orders.OrdersReq;
import com.example.webpos.dto.orders.OrdersRes;
import com.example.webpos.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrdersController {
    private final OrderService orderService;

    @PostMapping()
    public List<OrdersRes> orders(@RequestBody OrdersReq ordersReq) {
        return orderService.order(ordersReq);
    }
}

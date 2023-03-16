package com.example.webpos.order.controller;

import com.example.webpos.auth.SecurityUtil;
import com.example.webpos.order.dto.OrdersCreateReq;
import com.example.webpos.order.dto.OrdersInfo;
import com.example.webpos.order.dto.OrdersRes;
import com.example.webpos.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrdersController {
    private final OrderService orderService;

    @GetMapping()
    public List<OrdersInfo> list() {
        return orderService.list();
    }

    @GetMapping("/{orderId}")
    public OrdersInfo get(@PathVariable Long orderId) {
        return orderService.get(orderId);
    }

    @PostMapping()
    public List<OrdersRes> orders(@RequestBody OrdersCreateReq ordersCreateReq) {
        Long currentMemberId = SecurityUtil.getCurrentMemberId();
        return orderService.order(currentMemberId, ordersCreateReq);
    }
}

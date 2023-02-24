package com.example.webpos.order.controller;

import com.example.webpos.order.dto.OrdersCreateReq;
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

    @GetMapping("/{orderId}")
    public List<OrdersRes> list(@PathVariable Long orderId){
        return orderService.list(orderId);
    }

    @PostMapping()
    public List<OrdersRes> orders(@RequestBody List<OrdersCreateReq> ordersCreateReqList) {
        return orderService.order(ordersCreateReqList);
    }
}

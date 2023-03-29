package com.example.webpos.order.controller;

import com.example.webpos.auth.SecurityUtil;
import com.example.webpos.order.dto.OrdersCreateReq;
import com.example.webpos.order.dto.OrdersInfo;
import com.example.webpos.order.dto.OrdersRes;
import com.example.webpos.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrdersController {
    private final OrderService orderService;

    @GetMapping()
    public ResponseEntity<List<OrdersInfo>> list() {
        Long memberId = SecurityUtil.getCurrentMemberId();
        return ResponseEntity.ok(orderService.list(memberId));
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrdersInfo> get(@PathVariable Long orderId) {
        return ResponseEntity.ok(orderService.get(orderId));
    }

    @PostMapping()
    public ResponseEntity<List<OrdersRes>> orders(@RequestBody OrdersCreateReq ordersCreateReq) {
        Long currentMemberId = SecurityUtil.getCurrentMemberId();
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.order(currentMemberId, ordersCreateReq));
    }
}

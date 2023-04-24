package com.example.webpos.order.repository;

import com.example.webpos.order.domain.OrderItem;

import java.util.List;

public interface OrderItemRepositoryCustom {
    List<OrderItem> findByOrdersId(Long orderId);

    List<OrderItem> findAllByMemberId(Long memberId);
}

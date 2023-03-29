package com.example.webpos.order.repository;

import com.example.webpos.order.domain.Orders;
import com.example.webpos.order.dto.OrdersInfo;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
}

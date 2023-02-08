package com.example.webpos.repository.order;

import com.example.webpos.domain.order.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders, Long> {
}

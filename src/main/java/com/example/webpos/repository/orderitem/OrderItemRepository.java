package com.example.webpos.repository.orderitem;

import com.example.webpos.domain.orderitem.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}

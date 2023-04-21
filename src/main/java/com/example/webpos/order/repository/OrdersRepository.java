package com.example.webpos.order.repository;

import com.example.webpos.order.domain.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
    @Query("select o from Orders o join fetch o.member")
    List<Orders> findAllFetchJoin();
}

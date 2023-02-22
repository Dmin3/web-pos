package com.example.webpos.order.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    public static Orders createOrders(OrderStatus orderStatus) {
        return new Orders(orderStatus);
    }

    public Orders(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}

package com.example.webpos.order.domain;

import com.example.webpos.member.domain.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;


    public static Orders createOrders(OrderStatus orderStatus) {
        return new Orders(orderStatus);
    }

    public Orders(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}

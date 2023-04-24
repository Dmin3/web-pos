package com.example.webpos.order.domain;

import com.example.webpos.common.BaseTimeEntity;
import com.example.webpos.member.domain.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Orders extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orders_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus orderStatus;

    @Column(nullable = false)
    private Integer peopleCount;

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public static Orders createOrders(Member member, Integer peopleCount) {
        return new Orders(member, OrderStatus.START, peopleCount);
    }

    public Orders(Member member, OrderStatus orderStatus, Integer peopleCount) {
        this.member = member;
        this.orderStatus = orderStatus;
        this.peopleCount = peopleCount;
    }
}

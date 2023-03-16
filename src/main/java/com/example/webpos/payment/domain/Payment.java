package com.example.webpos.payment.domain;

import com.example.webpos.common.BaseTimeEntity;
import com.example.webpos.order.domain.Orders;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Payment extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orders_id")
    private Orders orders;

    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    private Integer totalPrice;

    @Builder
    public Payment(final Orders orders, final PaymentType paymentType, final Integer totalPrice) {
        this.orders = orders;
        this.paymentType = paymentType;
        this.totalPrice = totalPrice;
    }
}

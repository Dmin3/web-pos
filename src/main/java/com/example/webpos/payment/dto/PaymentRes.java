package com.example.webpos.payment.dto;

import com.example.webpos.order.domain.OrderStatus;
import com.example.webpos.payment.domain.PaymentType;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PaymentRes {
    private Long paymentId;
    private Long orderId;
    private Long totalPrice;
    private OrderStatus orderStatus;
    private PaymentType paymentType;

    public PaymentRes(Long paymentId, Long orderId, Long totalPrice, OrderStatus orderStatus, PaymentType paymentType) {
        this.paymentId = paymentId;
        this.orderId = orderId;
        this.totalPrice = totalPrice;
        this.orderStatus = orderStatus;
        this.paymentType = paymentType;
    }
}

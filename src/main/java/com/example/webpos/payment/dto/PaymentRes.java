package com.example.webpos.payment.dto;

import com.example.webpos.order.domain.OrderStatus;
import com.example.webpos.payment.domain.Payment;
import com.example.webpos.payment.domain.PaymentType;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PaymentRes {
    private Long paymentId;
    private Long orderId;
    private Integer totalPrice;
    private OrderStatus orderStatus;
    private PaymentType paymentType;

    public static PaymentRes of(Payment payment) {
        return new PaymentRes(payment.getId(), payment.getOrders().getId(), payment.getTotalPrice(), payment.getOrders().getOrderStatus(), payment.getPaymentType());
    }

    public PaymentRes(Long paymentId, Long orderId, Integer totalPrice, OrderStatus orderStatus, PaymentType paymentType) {
        this.paymentId = paymentId;
        this.orderId = orderId;
        this.totalPrice = totalPrice;
        this.orderStatus = orderStatus;
        this.paymentType = paymentType;
    }
}

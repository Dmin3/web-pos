package com.example.webpos.payment.service;

import com.example.webpos.payment.domain.PaymentType;
import com.example.webpos.payment.dto.PaymentRes;

public interface PaymentService {
    PaymentRes pay(Long orderId);

    PaymentType getPaymentType();
}

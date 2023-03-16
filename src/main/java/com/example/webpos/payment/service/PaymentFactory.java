package com.example.webpos.payment.service;

import com.example.webpos.payment.domain.PaymentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class PaymentFactory {
    private Map<PaymentType, PaymentService> paymentServiceMap;

    @Autowired
    public PaymentFactory(Set<PaymentService> paymentServices) {
        createPaymentService(paymentServices);
    }

    public PaymentService findPaymentService(PaymentType paymentType) {
        return paymentServiceMap.get(paymentType);
    }

    private void createPaymentService(Set<PaymentService> paymentServices) {
        paymentServiceMap = new HashMap<>();
        paymentServices.forEach(paymentService ->
                paymentServiceMap.put(paymentService.getPaymentType(), paymentService)
        );
    }
}

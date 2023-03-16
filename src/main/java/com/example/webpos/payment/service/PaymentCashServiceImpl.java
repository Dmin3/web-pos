package com.example.webpos.payment.service;

import com.example.webpos.payment.domain.PaymentType;
import com.example.webpos.payment.dto.PaymentRes;
import com.example.webpos.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentCashServiceImpl implements PaymentService{
    private final PaymentRepository paymentRepository;

    @Override
    public PaymentRes pay(Long orderId) {
        return null;
    }

    @Override
    public PaymentType getPaymentType() {
        return null;
    }
}

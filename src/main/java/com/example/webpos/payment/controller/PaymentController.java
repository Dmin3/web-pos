package com.example.webpos.payment.controller;

import com.example.webpos.payment.domain.PaymentType;
import com.example.webpos.payment.dto.PaymentRes;
import com.example.webpos.payment.service.PaymentFactory;
import com.example.webpos.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pay")
@Slf4j
public class PaymentController {
    private final PaymentFactory paymentFactory;

    @PostMapping("/{orderId}")
    public PaymentRes pay(
            @PathVariable Long orderId,
            // Convert 시키기
            @RequestParam PaymentType type
    ) {
        log.info("payment = " + type);

        PaymentService paymentService = paymentFactory.findPaymentService(type);
        return paymentService.pay(orderId);
    }
}

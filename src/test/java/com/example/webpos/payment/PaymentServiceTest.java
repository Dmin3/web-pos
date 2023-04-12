package com.example.webpos.payment;

import com.example.webpos.payment.domain.PaymentType;
import com.example.webpos.payment.service.PaymentCardServiceImpl;
import com.example.webpos.payment.service.PaymentCashServiceImpl;
import com.example.webpos.payment.service.PaymentFactory;
import com.example.webpos.payment.service.PaymentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class PaymentServiceTest {
    @Autowired
    private PaymentFactory paymentFactory;

    @Test
    void 카드결제_구현체_가져오기(){
        //given
        PaymentType card = PaymentType.CARD;

        //when
        PaymentService paymentService = paymentFactory.findPaymentService(card);


        //then
        System.out.println("paymentService.getClass() = " + paymentService.getClass());
        assertThat(paymentService.getClass()).isEqualTo(PaymentCardServiceImpl.class);
    }

    @Test
    void 현금결제_구현체_가져오기(){
        //given
        PaymentType card = PaymentType.CASH;

        //when
        PaymentService paymentService = paymentFactory.findPaymentService(card);

        //then
        System.out.println("paymentService.getClass() = " + paymentService.getClass());
        assertThat(paymentService.getClass()).isEqualTo(PaymentCashServiceImpl.class);
    }
}

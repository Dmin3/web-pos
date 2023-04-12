package com.example.webpos.common.convert;

import com.example.webpos.payment.domain.PaymentType;
import org.springframework.core.convert.converter.Converter;

public class StringToPaymentTypeConverter implements Converter<String, PaymentType> {
    @Override
    public PaymentType convert(String source) {
        String s = source.toUpperCase();
        return PaymentType.valueOf(s);
    }
}

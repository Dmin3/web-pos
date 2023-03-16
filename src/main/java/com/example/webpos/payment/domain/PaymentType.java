package com.example.webpos.payment.domain;

public enum PaymentType {
    CASH("현금"),
    CARD("카드")
    ;

    private final String name;

    PaymentType(String name) {
        this.name = name;
    }
}

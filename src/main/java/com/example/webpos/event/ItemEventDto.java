package com.example.webpos.event;

import lombok.Data;

@Data
public class ItemEventDto {
    private String name;
    private int price;
    private Long memberId;

    public ItemEventDto(String name, int price, Long memberId) {
        this.name = name;
        this.price = price;
        this.memberId = memberId;
    }
}

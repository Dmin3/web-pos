package com.example.webpos.dto.item;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ItemRes {
    private Long id;
    private String name;
    private Integer price;

    public ItemRes(Long id, String name, Integer price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}

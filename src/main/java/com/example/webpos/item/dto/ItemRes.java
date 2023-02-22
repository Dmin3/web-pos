package com.example.webpos.item.dto;

import com.example.webpos.item.domain.Item;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ItemRes {
    private Long id;
    private String name;
    private Integer price;

    public static ItemRes of(Item item) {
        return ItemRes.builder()
                .id(item.getId())
                .name(item.getName())
                .price(item.getPrice())
                .build();
    }

    @Builder
    public ItemRes(Long id, String name, Integer price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}

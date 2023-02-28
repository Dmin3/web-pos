package com.example.webpos.item.dto;

import com.example.webpos.item.domain.Item;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ItemSaveReq {
    private String name;
    private Integer price;

    public ItemSaveReq(String name, Integer price) {
        this.name = name;
        this.price = price;
    }

    public Item toEntity(Long memberId) {
        return new Item(
                name,
                price,
                memberId
        );
    }
}

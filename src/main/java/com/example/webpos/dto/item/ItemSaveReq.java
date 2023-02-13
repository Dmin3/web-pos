package com.example.webpos.dto.item;

import com.example.webpos.domain.item.Item;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ItemSaveReq {
    private String name;
    private Integer price;

    public Item toEntity() {
        return new Item(
                name,
                price
        );
    }
}

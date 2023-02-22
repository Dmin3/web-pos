package com.example.webpos.item.domain;

import com.example.webpos.common.BaseTimeEntity;

import com.example.webpos.item.dto.ItemRes;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Item extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;

    private String name;

    private Integer price;

    public Item(String name, Integer price) {
        this.name = name;
        this.price = price;
    }

    public ItemRes toResult() {
        return new ItemRes(
                id,
                name,
                price
        );
    }

}

package com.example.webpos.domain.item;

import com.example.webpos.domain.BaseTimeEntity;

import com.example.webpos.dto.item.ItemRes;
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

    private Integer amount;

    private Integer price;

    public Item(String name, Integer amount, Integer price) {
        this.name = name;
        this.amount = amount;
        this.price = price;
    }

    public ItemRes toResult() {
        return new ItemRes(
                id,
                name,
                amount,
                price
        );
    }

}

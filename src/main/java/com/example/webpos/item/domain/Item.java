package com.example.webpos.item.domain;

import com.example.webpos.common.BaseTimeEntity;
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

    private Long memberId;

    public Item(String name, Integer price, Long memberId) {
        this.name = name;
        this.price = price;
        this.memberId = memberId;
    }
}

package com.example.webpos.domain.orderitem;

import com.example.webpos.domain.BaseTimeEntity;
import com.example.webpos.domain.item.Item;
import com.example.webpos.domain.order.Order;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class OrderItem extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    private Item item;

    public OrderItem(Order order, Item item) {
        this.order = order;
        this.item = item;
    }
}

package com.example.webpos.order.domain;

import com.example.webpos.common.BaseTimeEntity;
import com.example.webpos.item.domain.Item;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class OrderItem extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private Orders orders;

    private Integer amount;

    public void setItem(Item item) {
        this.item = item;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

}

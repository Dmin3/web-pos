package com.example.webpos.domain.orderitem;

import com.example.webpos.domain.BaseTimeEntity;
import com.example.webpos.domain.item.Item;
import com.example.webpos.domain.orders.Orders;
import com.example.webpos.dto.orders.OrdersRes;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

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

    public void addItem(Item item) {
        this.item = item;
    }

    public void addOrders(Orders orders) {
        this.orders = orders;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public OrdersRes toResult() {
        return new OrdersRes(
                id,
                item.getId(),
                orders.getId(),
                item.getName(),
                amount,
                item.getPrice()
        );
    }
}

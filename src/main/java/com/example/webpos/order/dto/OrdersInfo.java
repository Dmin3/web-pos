package com.example.webpos.order.dto;

import com.example.webpos.item.domain.Item;
import com.example.webpos.order.domain.OrderStatus;
import com.example.webpos.order.domain.Orders;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class OrdersInfo {
    private Long orderId;
    private Integer peopleCount;
    private OrderStatus orderStatus;
    private List<ItemInfo> itemInfos;

    public static OrdersInfo of(Orders orders, List<ItemInfo> itemInfos) {
        return new OrdersInfo(orders, itemInfos);
    }

    public static OrdersInfo of(Orders orders) {
        return new OrdersInfo(orders);
    }

    public OrdersInfo(Orders orders, List<ItemInfo> itemInfos) {
        this.orderId = orders.getId();
        this.peopleCount = orders.getPeopleCount();
        this.orderStatus = orders.getOrderStatus();
        this.itemInfos = itemInfos;
    }

    public OrdersInfo(Orders orders) {
        this.orderId = orders.getId();
        this.peopleCount = orders.getPeopleCount();
        this.orderStatus = orders.getOrderStatus();
    }

    @Getter
    @NoArgsConstructor
    public static class ItemInfo {
        private Long itemId;
        private String name;
        private Integer price;
        private Integer amount;

        public static ItemInfo of(Item item, Integer amount) {
            return new ItemInfo(
                    item.getId(),
                    item.getName(),
                    item.getPrice(),
                    amount
            );
        }

        public ItemInfo(Long itemId, String name, Integer price, Integer amount) {
            this.itemId = itemId;
            this.name = name;
            this.price = price;
            this.amount = amount;
        }
    }
}

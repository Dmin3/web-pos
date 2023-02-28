package com.example.webpos.order.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class OrdersCreateReq {
    private List<ItemInfo> itemInfos;
    private Integer peopleCount;

    public OrdersCreateReq(List<ItemInfo> itemInfos, Integer peopleCount) {
        this.itemInfos = itemInfos;
        this.peopleCount = peopleCount;
    }

    @Getter
    @NoArgsConstructor
    public static class ItemInfo {
        private Long itemId;
        private int amount;

        public ItemInfo(Long itemId, int amount) {
            this.itemId = itemId;
            this.amount = amount;
        }
    }
}

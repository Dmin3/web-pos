package com.example.webpos.dto.orders;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderItemInfo {
    private Long itemId;
    private Integer amount;
}

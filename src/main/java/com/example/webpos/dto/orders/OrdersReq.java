package com.example.webpos.dto.orders;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Getter
@NoArgsConstructor
public class OrdersReq {
    private List<OrdersInfo> ordersInfoList;
}

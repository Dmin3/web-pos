package com.example.webpos.order.repository;

import com.example.webpos.order.domain.OrderItem;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.webpos.item.domain.QItem.item;
import static com.example.webpos.order.domain.QOrderItem.orderItem;
import static com.example.webpos.order.domain.QOrders.orders;

@Repository
@RequiredArgsConstructor
public class OrderItemRepositoryImpl implements OrderItemRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<OrderItem> findByOrdersId(Long orderId) {
        return queryFactory.selectFrom(orderItem)
                .innerJoin(orderItem.orders, orders).fetchJoin()
                .innerJoin(orderItem.item, item).fetchJoin()
                .where(orderItem.orders.id.eq(orderId))
                .fetch();
    }

    @Override
    public List<OrderItem> findAllByMemberId(Long memberId) {
        return queryFactory.selectFrom(orderItem)
                .innerJoin(orderItem.orders, orders)
                .innerJoin(orderItem.item, item)
                .where(orders.member.id.eq(memberId))
                .fetch();
    }
}

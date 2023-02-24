package com.example.webpos.order.repository;

import com.example.webpos.item.domain.QItem;
import com.example.webpos.member.domain.QMember;
import com.example.webpos.order.domain.OrderItem;
import com.example.webpos.order.domain.QOrderItem;
import com.example.webpos.order.domain.QOrders;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderItemRepositoryImpl implements OrderItemRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<OrderItem> findByOrder(Long orderId) {
        QOrderItem orderItem = QOrderItem.orderItem;
        QItem item = QItem.item;
        QOrders orders = QOrders.orders;
        return queryFactory.selectFrom(orderItem)
                .join(orderItem.orders, orders).fetchJoin()
                .join(orderItem.item, item).fetchJoin()
                .where(orderItem.orders.id.eq(orderId))
                .fetch();
    }
}

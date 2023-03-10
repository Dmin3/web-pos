package com.example.webpos.item.repository;

import com.example.webpos.item.domain.Item;
import com.example.webpos.item.domain.QItem;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepositoryImpl implements ItemRepositoryCustom {
    private final JPAQueryFactory query;

    @Override
    public List<Item> findItemList(Long memberId) {
        QItem item = QItem.item;
        return query.selectFrom(item)
                .where(item.memberId.eq(memberId))
                .fetch();
    }
}

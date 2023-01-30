package com.example.webpos.repository.item;

import com.example.webpos.domain.item.Item;
import com.example.webpos.domain.item.QItem;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepositoryImpl implements ItemRepositoryCustom{
    private final JPAQueryFactory query;

    @Override
    public List<Item> findItemList() {
        QItem item = QItem.item;
        return query.selectFrom(item).fetch();
    }
}

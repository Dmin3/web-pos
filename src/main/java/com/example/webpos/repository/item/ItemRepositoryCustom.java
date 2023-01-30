package com.example.webpos.repository.item;

import com.example.webpos.domain.item.Item;

import java.util.List;

public interface ItemRepositoryCustom {
    List<Item> findItemList();
}

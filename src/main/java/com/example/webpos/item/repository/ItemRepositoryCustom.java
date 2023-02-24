package com.example.webpos.item.repository;

import com.example.webpos.item.domain.Item;

import java.util.List;

public interface ItemRepositoryCustom {
    List<Item> findItemList(Long memberId);
}

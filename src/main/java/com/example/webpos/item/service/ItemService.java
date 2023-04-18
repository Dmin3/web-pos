package com.example.webpos.item.service;

import com.example.webpos.item.domain.Item;
import com.example.webpos.item.dto.ItemRes;
import com.example.webpos.item.dto.ItemSaveReq;

import java.util.List;

public interface ItemService {
    List<ItemRes> list(Long memberId);

    ItemRes get(Long itemId);

    ItemRes save(Long memberId, ItemSaveReq req);

    Boolean delete(Long itemId);
}

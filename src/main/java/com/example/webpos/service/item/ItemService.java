package com.example.webpos.service.item;

import com.example.webpos.dto.item.ItemRes;
import com.example.webpos.dto.item.ItemSaveReq;

import java.util.List;

public interface ItemService {
    List<ItemRes> list();

    ItemRes get(Long itemId);

    ItemRes save(Long memberId, ItemSaveReq req);

    Boolean delete(Long itemId);
}

package com.example.webpos.item.controller;

import com.example.webpos.item.dto.ItemRes;
import com.example.webpos.item.dto.ItemSaveReq;
import com.example.webpos.item.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/item")
public class ItemController {
    private final ItemService itemService;

    @PostMapping("/{memberId}")
    public ItemRes itemRes(@PathVariable Long memberId, @RequestBody ItemSaveReq req) {
        return itemService.save(memberId, req);
    }
}

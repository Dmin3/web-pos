package com.example.webpos.controller;

import com.example.webpos.dto.item.ItemRes;
import com.example.webpos.dto.item.ItemSaveReq;
import com.example.webpos.service.item.ItemService;
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

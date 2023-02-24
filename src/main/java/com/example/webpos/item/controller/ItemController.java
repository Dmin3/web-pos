package com.example.webpos.item.controller;

import com.example.webpos.item.dto.ItemRes;
import com.example.webpos.item.dto.ItemSaveReq;
import com.example.webpos.item.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/item")
public class ItemController {
    private final ItemService itemService;

    @GetMapping
    public List<ItemRes> list() {
        return itemService.list();
    }

    @GetMapping("/{itemId}")
    public ItemRes get(@PathVariable Long itemId) {
        return itemService.get(itemId);
    }

    @PostMapping("/{memberId}")
    public ItemRes create(@PathVariable Long memberId, @RequestBody ItemSaveReq req) {
        return itemService.save(memberId, req);
    }

    @PatchMapping("/{itemId}")
    public ItemRes modify(@PathVariable Long itemId){
        return null;
    }
}

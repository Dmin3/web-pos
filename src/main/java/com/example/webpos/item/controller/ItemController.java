package com.example.webpos.item.controller;

import com.example.webpos.auth.SecurityUtil;
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
        Long currentMemberId = SecurityUtil.getCurrentMemberId();
        return itemService.list(currentMemberId);
    }

    @GetMapping("/{itemId}")
    public ItemRes get(@PathVariable Long itemId) {
        return itemService.get(itemId);
    }

    @PostMapping()
    public ItemRes create(@RequestBody ItemSaveReq req) {
        Long currentMemberId = SecurityUtil.getCurrentMemberId();
        return itemService.save(currentMemberId, req);
    }

    @PatchMapping("/{itemId}")
    public ItemRes modify(@PathVariable Long itemId) {
        return null;
    }

    @DeleteMapping("/{itemId}")
    public Boolean remove(@PathVariable Long itemId) {
        return itemService.delete(itemId);
    }
}

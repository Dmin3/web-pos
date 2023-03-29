package com.example.webpos.item.controller;

import com.example.webpos.auth.SecurityUtil;
import com.example.webpos.item.dto.ItemRes;
import com.example.webpos.item.dto.ItemSaveReq;
import com.example.webpos.item.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/item")
public class ItemController {
    private final ItemService itemService;

    @GetMapping
    public ResponseEntity<List<ItemRes>> list() {
        Long currentMemberId = SecurityUtil.getCurrentMemberId();
        return ResponseEntity.ok(itemService.list(currentMemberId));
    }

    @GetMapping("/{itemId}")
    public ResponseEntity<ItemRes> get(@PathVariable Long itemId) {
        return ResponseEntity.ok(itemService.get(itemId));
    }

    @PostMapping()
    public ResponseEntity<ItemRes> create(@RequestBody ItemSaveReq req) {
        Long currentMemberId = SecurityUtil.getCurrentMemberId();
        return ResponseEntity.status(HttpStatus.CREATED).body(itemService.save(currentMemberId, req));
    }

    @PatchMapping("/{itemId}")
    public ResponseEntity<ItemRes> modify(@PathVariable Long itemId) {
        return ResponseEntity.ok(null);
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<Boolean> remove(@PathVariable Long itemId) {
        return ResponseEntity.ok(itemService.delete(itemId));
    }
}

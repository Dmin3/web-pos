package com.example.webpos.item.service;

import com.example.webpos.common.error.exception.ItemNotFoundException;
import com.example.webpos.common.error.exception.MemberNotFoundException;
import com.example.webpos.item.domain.Item;
import com.example.webpos.item.dto.ItemRes;
import com.example.webpos.item.dto.ItemSaveReq;
import com.example.webpos.item.repository.ItemRepository;
import com.example.webpos.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;

    @Override
    public List<ItemRes> list() {
        return itemRepository.findItemList().stream().map(ItemRes::of).toList();
    }

    @Override
    public ItemRes get(Long itemId) {
        return ItemRes.of(itemRepository.findById(itemId).orElseThrow(ItemNotFoundException::new));
    }

    @Override
    @Transactional
    public ItemRes save(Long memberId, ItemSaveReq req) {
        memberRepository.findById(memberId).orElseThrow(MemberNotFoundException::new);

        return ItemRes.of(itemRepository.save(req.toEntity()));
    }

    @Override
    @Transactional
    public Boolean delete(Long itemId) {
        Item item = itemRepository.findById(itemId).orElseThrow(ItemNotFoundException::new);
        itemRepository.delete(item);
        return true;
    }
}

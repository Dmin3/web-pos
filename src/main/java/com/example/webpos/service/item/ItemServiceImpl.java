package com.example.webpos.service.item;

import com.example.webpos.common.error.exception.ItemNotFoundException;
import com.example.webpos.common.error.exception.MemberNotFoundException;
import com.example.webpos.dto.item.ItemRes;
import com.example.webpos.dto.item.ItemSaveReq;
import com.example.webpos.repository.item.ItemRepository;
import com.example.webpos.repository.member.MemberRepository;
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
        return itemRepository.findItemList().stream().map(i -> i.toResult()).toList();
    }

    @Override
    public ItemRes get(Long itemId) {
        return itemRepository.findById(itemId).orElseThrow(ItemNotFoundException::new).toResult();
    }

    @Override
    public ItemRes save(Long memberId, ItemSaveReq req) {
        memberRepository.findById(memberId).orElseThrow(MemberNotFoundException::new);

        return itemRepository.save(req.toEntity()).toResult();
    }
}

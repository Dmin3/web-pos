package com.example.webpos.item.service;

import com.example.webpos.event.ItemEventDto;
import com.example.webpos.item.domain.Item;
import com.example.webpos.item.dto.ItemRes;
import com.example.webpos.item.dto.ItemSaveReq;
import com.example.webpos.item.repository.ItemRepository;
import com.example.webpos.member.domain.Member;
import com.example.webpos.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;

    @Override
    public List<ItemRes> list(Long memberId) {
        return itemRepository.findItemList(memberId).stream().map(ItemRes::of).toList();
    }

    @Override
    public ItemRes get(Long itemId) {
        return ItemRes.of(itemRepository.getById(itemId));
    }

    @Override
    @Transactional
    public ItemRes save(Long memberId, ItemSaveReq req) {
        Member member = memberRepository.getById(memberId);

        return ItemRes.of(itemRepository.save(req.toEntity(member.getId())));
    }

    @Override
    @Transactional
    public Boolean delete(Long itemId) {
        Item item = itemRepository.getById(itemId);
        itemRepository.delete(item);
        return true;
    }
}

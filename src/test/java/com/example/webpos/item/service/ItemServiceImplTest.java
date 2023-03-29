package com.example.webpos.item.service;

import com.example.webpos.item.exception.ItemNotFoundException;
import com.example.webpos.item.domain.Item;
import com.example.webpos.item.dto.ItemRes;
import com.example.webpos.item.dto.ItemSaveReq;
import com.example.webpos.item.repository.ItemRepository;
import com.example.webpos.member.domain.Member;
import com.example.webpos.member.domain.MemberType;
import com.example.webpos.member.repository.MemberRepository;
import com.example.webpos.support.RepositoryTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

@RepositoryTest
class ItemServiceImplTest {
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private MemberRepository memberRepository;

    private ItemService itemService;

    private Member member;

    @BeforeEach
    void setUp() {
        itemService = new ItemServiceImpl(itemRepository, memberRepository);

        member = Member.builder().name("").email("").phone("").password("").memberType(MemberType.ROLE_NORMAL).build();
        memberRepository.save(member);
    }

    @Test
    void 유저가_등록한_상품만_전체조회() {
        //given
        List<Item> items = List.of(
                new Item("피자", 2000, member.getId()),
                new Item("햄버거", 1000, member.getId()),
                new Item("치킨", 3000, member.getId()),

                new Item("피자", 2000, 2L),
                new Item("햄버거", 1000, 2L),
                new Item("치킨", 3000, 2L)
        );

        itemRepository.saveAll(items);

        //when
        List<ItemRes> list = itemService.list(member.getId());

        //then
        assertThat(list.size()).isEqualTo(3);
    }

    @Test
    void ㄱㅐ별_상품_조회() {
        //given
        Item item = itemRepository.save(new Item("피자", 2000, member.getId()));

        //when
        ItemRes itemRes = itemService.get(item.getId());

        //then
        assertAll(
                () -> assertThat(item.getId()).isEqualTo(itemRes.getId()),
                () -> assertThat(item.getName()).isEqualTo(itemRes.getName()),
                () -> assertThat(item.getPrice()).isEqualTo(itemRes.getPrice())
        );
    }

    @Test
    void 상품_조회_예외() {
        //given
        Long itemId = 10L;

        //when

        //then
        assertThatThrownBy(
                () -> itemService.get(itemId)
        ).isInstanceOf(ItemNotFoundException.class);
    }

    @Test
    void 상품_저장() {
        //given
        ItemSaveReq req = new ItemSaveReq("상품1", 1000);

        //when
        ItemRes itemRes = itemService.save(member.getId(), req);

        //then
        assertAll(
                () -> assertThat(itemRes.getName()).isEqualTo(req.getName()),
                () -> assertThat(itemRes.getPrice()).isEqualTo(req.getPrice())
        );
    }

    @Test
    void 상품_삭제() {
        //given
        Item item = itemRepository.save(new Item("상품1", 1000, 1L));

        //when
        itemService.delete(item.getId());
        itemRepository.flush();

        //then
        assertThat(itemRepository.findById(item.getId())).isEqualTo(Optional.empty());
    }


}
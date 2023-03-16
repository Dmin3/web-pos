package com.example.webpos.order.service;

import com.example.webpos.common.error.exception.ItemInfoEmptyException;
import com.example.webpos.item.domain.Item;
import com.example.webpos.item.repository.ItemRepository;
import com.example.webpos.member.domain.Member;
import com.example.webpos.member.domain.MemberType;
import com.example.webpos.member.repository.MemberRepository;
import com.example.webpos.order.dto.OrdersCreateReq;
import com.example.webpos.order.dto.OrdersInfo;
import com.example.webpos.order.dto.OrdersRes;
import com.example.webpos.order.repository.OrderItemRepository;
import com.example.webpos.order.repository.OrdersRepository;
import com.example.webpos.support.RepositoryTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

@RepositoryTest
class OrderServiceImplTest {
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private OrdersRepository ordersRepository;

    private OrderService orderService;

    private Member member;

    private List<OrdersCreateReq.ItemInfo> itemInfos;

    @BeforeEach
    void setUp() {
        orderService = new OrderServiceImpl(memberRepository, orderItemRepository, ordersRepository, itemRepository);

        member = memberRepository.save(new Member("", "", "", "", MemberType.ROLE_NORMAL));

        List<Item> saveItems = itemRepository.saveAll(List.of(
                new Item("피자", 2000, member.getId()),
                new Item("햄버거", 1000, member.getId()),
                new Item("치킨", 3000, member.getId())
        ));

        Item item1 = saveItems.get(0);
        Item item2 = saveItems.get(1);
        Item item3 = saveItems.get(2);

        //when
        itemInfos = List.of(
                new OrdersCreateReq.ItemInfo(item1.getId(), item1.getPrice()),
                new OrdersCreateReq.ItemInfo(item2.getId(), item2.getPrice()),
                new OrdersCreateReq.ItemInfo(item3.getId(), item3.getPrice())
        );
    }

    @Test
    void 주문_성공() {
        //given
        OrdersCreateReq ordersCreateReq = new OrdersCreateReq(itemInfos, 3);

        List<OrdersRes> ordersRes = orderService.order(member.getId(), ordersCreateReq);

        //then
        assertAll(
                () -> assertThat(ordersRes.size()).isEqualTo(itemInfos.size())
        );
    }

    @Test
    void 주문_예외(){
        //given
        itemInfos = Collections.emptyList();

        //when

        //then
        assertThatThrownBy(
                () -> orderService.order(member.getId(), new OrdersCreateReq(itemInfos, 3))
        ).isInstanceOf(ItemInfoEmptyException.class);
    }

    @Test
    void 주문내역_전체조회(){
        //given
        orderService.order(member.getId(), new OrdersCreateReq(itemInfos, 3));
        orderService.order(member.getId(), new OrdersCreateReq(itemInfos, 3));
        orderService.order(member.getId(), new OrdersCreateReq(itemInfos, 3));

        //when
        List<OrdersInfo> list = orderService.list();

        //then
        assertThat(list.size()).isEqualTo(3);
    }

    @Test
    void 주문내역_조회() {
        //given
        List<OrdersRes> ordersRes = orderService.order(member.getId(), new OrdersCreateReq(itemInfos, 3));
        Long orderId = ordersRes.get(0).getOrdersId();

        //when
        OrdersInfo ordersInfo = orderService.get(orderId);

        //then
        assertAll(
                () -> assertThat(ordersInfo.getOrderId()).isEqualTo(orderId),
                () -> assertThat(ordersInfo.getPeopleCount()).isEqualTo(3)
        );

    }
}
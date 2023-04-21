package com.example.webpos.order;

import com.example.webpos.member.domain.Member;
import com.example.webpos.member.domain.MemberType;
import com.example.webpos.member.repository.MemberRepository;
import com.example.webpos.order.domain.OrderStatus;
import com.example.webpos.order.domain.Orders;
import com.example.webpos.order.repository.OrdersRepository;
import com.example.webpos.support.RepositoryTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.util.List;

@RepositoryTest
@Slf4j
public class OrdersTest {
    @Autowired
    EntityManager em;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private OrdersRepository ordersRepository;

    @Test
    void 주문_완료한_회원들만_꺼내오기(){
        Member user1 = memberRepository.saveAndFlush(new Member("user1", "asd@ada", "", "001-11-11", MemberType.ROLE_NORMAL));
        Member user2 = memberRepository.saveAndFlush(new Member("user2", "mw@ms", "", "010-11-122", MemberType.ROLE_NORMAL));

        List<Orders> orders = List.of(
                new Orders(user1, OrderStatus.START, 2),
                new Orders(user1, OrderStatus.FINISH, 3),
                new Orders(user2, OrderStatus.FINISH, 4),
                new Orders(user2, OrderStatus.START, 5),
                new Orders(user2, OrderStatus.FINISH, 12)
        );

        ordersRepository.saveAll(orders);

        em.flush();
        em.clear();

        List<Orders> all = ordersRepository.findAllFetchJoin();

        System.out.println("user1 :" + user1);
        System.out.println("user2 :" + user2);

        for (Orders orders1 : all) {
            System.out.println("orders1 = " + orders1 + " member.getClass : " + orders1.getMember().getClass());
            System.out.println("member : " + orders1.getMember());
            System.out.println("서로 같은 객체인지?" + (orders1.getMember() == user2));

        }

        List<Long> orders3 = all.stream()
                .filter(orders1 -> orders1.getOrderStatus().equals(OrderStatus.FINISH)).map(orders1 -> orders1.getId()).toList();


        for (Long aLong : orders3) {
            System.out.println("ID : " + aLong);
        }
    }

}

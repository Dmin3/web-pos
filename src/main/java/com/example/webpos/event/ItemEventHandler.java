package com.example.webpos.event;

import com.example.webpos.item.domain.Item;
import com.example.webpos.item.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
@Slf4j
public class ItemEventHandler {
    private final ItemRepository itemRepository;

    @TransactionalEventListener
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void event(ItemEventDto eventDto) {
        /**
         * 예외가 터지면 실제로 값이 저장되지는 않으나 예외 로그가 따로 출력되지 않음 예외 로그를 출력하기 위해서 try-catch문 사용해야함.
         */

        try {
            log.info("하이", eventDto.toString());
            Item item = itemRepository.save(new Item(eventDto.getName(), eventDto.getPrice(), eventDto.getMemberId()));

            log.info("이벤트 저장완료 = {}", item.toString());

            throw new IllegalArgumentException("예외 안터지나? ㅎㅎ");
        } catch (Exception e) {
            log.error("예외 발생", e);
            throw e;
        }
    }
}

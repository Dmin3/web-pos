package com.example.webpos;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class EnumConvertTest {
    enum ItemInfo {
        NAVER_PAY(1L, 5000),
        EDIYA_COFFEE(0L, 3200);

        private final Long id;
        private final int price;

        ItemInfo(Long id, int price) {
            this.id = id;
            this.price = price;
        }

        public static ItemInfo convert(Long id) {
            return Arrays.stream(values()).filter(v -> v.id == id).findFirst().orElseThrow(NoSuchElementException::new);
        }

        public Long getId() {
            return id;
        }

        public int getPrice() {
            return price;
        }
    }

    @Test
    void ENUM_컨버터() {
        Long id = 1L;

        ItemInfo itemInfo = ItemInfo.convert(id);

        assertThat(itemInfo.id).isEqualTo(ItemInfo.NAVER_PAY.id);
        assertThat(itemInfo.price).isEqualTo(ItemInfo.NAVER_PAY.price);
    }

    @Test
    void ENUM_찾지못하면_NoSuchElementException() {
        Long id = 3L;


        assertThatThrownBy(() -> ItemInfo.convert(id));
    }

    @Test
    void Getter() {
        ItemInfo convert = ItemInfo.convert(1L);

        System.out.println(convert.getId());
        System.out.println(convert.getPrice());
        System.out.println(convert.name());
    }
}

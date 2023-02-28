package com.example.webpos.common.error;

import lombok.Getter;

@Getter
public enum ErrorCode {
    // MEMBER
    MEMBER_NOT_FOUND(404, "M01", "존재하지 않는 유저입니다."),

    // ITEM
    ITEM_NOT_FOUND(404, "I01", "존재하지 않는 아이템 입니다."),
    ITEM_EMPTY(400,"I02", "상품정보를 선택해주세요.")


    ;


    private final int status;
    private final String code;
    private final String reason;

    ErrorCode(int status, String code, String reason) {
        this.status = status;
        this.code = code;
        this.reason = reason;
    }
}

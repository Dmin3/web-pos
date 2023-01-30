package com.example.webpos.common.error.exception;

import com.example.webpos.common.error.ErrorCode;

public class MemberNotFoundException extends CommonException {
    public MemberNotFoundException() {
        super(ErrorCode.MEMBER_NOT_FOUND);
    }
}

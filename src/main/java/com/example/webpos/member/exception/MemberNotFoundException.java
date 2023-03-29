package com.example.webpos.member.exception;

import com.example.webpos.common.error.ErrorCode;
import com.example.webpos.common.error.exception.CommonException;

public class MemberNotFoundException extends CommonException {
    public MemberNotFoundException() {
        super(ErrorCode.MEMBER_NOT_FOUND);
    }
}

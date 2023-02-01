package com.example.webpos.common.error.exception;

import com.example.webpos.common.error.ErrorCode;
import lombok.Getter;

@Getter
public class CommonException extends RuntimeException{
    private ErrorCode errorCode;

    public CommonException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}

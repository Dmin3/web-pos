package com.example.webpos.common.error;

import lombok.Getter;

@Getter
public class ErrorResponse {
    private int status;
    private String code;
    private String reason;

    public ErrorResponse(ErrorCode errorCode) {
        this.status = errorCode.getStatus();
        this.code = errorCode.getCode();
        this.reason = errorCode.getReason();
    }

    public static ErrorResponse of(ErrorCode errorCode) {
        return new ErrorResponse(errorCode);
    }
}

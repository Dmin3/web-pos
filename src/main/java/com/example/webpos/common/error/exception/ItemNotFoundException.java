package com.example.webpos.common.error.exception;

import com.example.webpos.common.error.ErrorCode;

public class ItemNotFoundException extends CommonException{
    public ItemNotFoundException() {
        super(ErrorCode.ITEM_NOT_FOUND);
    }
}

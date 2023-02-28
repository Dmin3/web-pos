package com.example.webpos.common.error.exception;

import com.example.webpos.common.error.ErrorCode;

public class ItemInfoEmptyException extends CommonException{
    public ItemInfoEmptyException( ) {
        super(ErrorCode.ITEM_EMPTY);
    }
}

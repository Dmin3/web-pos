package com.example.webpos.item.exception;

import com.example.webpos.common.error.ErrorCode;
import com.example.webpos.common.error.exception.CommonException;

public class ItemInfoEmptyException extends CommonException {
    public ItemInfoEmptyException( ) {
        super(ErrorCode.ITEM_EMPTY);
    }
}

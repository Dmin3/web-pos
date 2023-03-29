package com.example.webpos.item.exception;

import com.example.webpos.common.error.ErrorCode;
import com.example.webpos.common.error.exception.CommonException;

public class ItemNotFoundException extends CommonException {
    public ItemNotFoundException() {
        super(ErrorCode.ITEM_NOT_FOUND);
    }
}

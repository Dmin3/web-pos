package com.example.webpos.order.exception;

import com.example.webpos.common.error.ErrorCode;
import com.example.webpos.common.error.exception.CommonException;

public class OrdersNotFoundException extends CommonException {
    public OrdersNotFoundException() {
        super(ErrorCode.ORDERS_NOT_FOUND);
    }
}

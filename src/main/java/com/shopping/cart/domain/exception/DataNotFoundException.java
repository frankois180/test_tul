package com.shopping.cart.domain.exception;

import com.shopping.cart.infrastructure.config.ShoppingCardMessage;

public class DataNotFoundException extends ShoppingCardException {

    public DataNotFoundException(ShoppingCardNotificationCode notificationCode, Object ... param) {
        super(ShoppingCardMessage.msg(notificationCode.getMessage(),param),notificationCode);
    }
}
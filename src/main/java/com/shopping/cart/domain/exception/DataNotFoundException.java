package com.shopping.cart.domain.exception;

import com.shopping.cart.infrastructure.config.ShoppingCartMessage;

public class DataNotFoundException extends ShoppingCartException {

    public DataNotFoundException(ShoppingCartNotificationCode notificationCode, Object ... param) {
        super(ShoppingCartMessage.msg(notificationCode.getMessage(),param),notificationCode);
    }
}
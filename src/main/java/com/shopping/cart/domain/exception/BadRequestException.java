package com.shopping.cart.domain.exception;

import com.shopping.cart.infrastructure.config.ShoppingCartMessage;

public class BadRequestException extends ShoppingCartException{

    public BadRequestException(ShoppingCartNotificationCode notificationCode, Object... param) {
        super(ShoppingCartMessage.msg(notificationCode.getMessage(), param), notificationCode);
    }
}

package com.shopping.cart.domain.exception;

import lombok.Getter;

public class ShoppingCartException extends RuntimeException {

    @Getter
    private final ShoppingCartNotificationCode notificationCode;

    public ShoppingCartException(String message, ShoppingCartNotificationCode notificationCode) {

        super(message);
        this.notificationCode = notificationCode;

    }

    public ShoppingCartException(String message, ShoppingCartNotificationCode notificationCode, Throwable cause) {

        super(message, cause);
        this.notificationCode = notificationCode;

    }

}
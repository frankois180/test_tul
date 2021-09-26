package com.shopping.cart.domain.exception;

import lombok.Getter;

public class ShoppingCardException extends RuntimeException {

    @Getter
    private final ShoppingCardNotificationCode notificationCode;

    public ShoppingCardException(String message, ShoppingCardNotificationCode notificationCode) {

        super(message);
        this.notificationCode = notificationCode;

    }

    public ShoppingCardException(String message, ShoppingCardNotificationCode notificationCode, Throwable cause) {

        super(message, cause);
        this.notificationCode = notificationCode;

    }

}
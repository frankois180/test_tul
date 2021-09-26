package com.shopping.cart.domain.exception;

import com.shopping.cart.infrastructure.config.ShoppingCartMessage;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum ShoppingCartNotificationCode {

    DATA_NOT_FOUND(ShoppingCartMessage.MsgName.DATA_NOT_FOUND, "IN_ERR_NF"),
    ACCESS_DENIED(ShoppingCartMessage.MsgName.ACCESS_DENIED, "IN_ACC_DND"),
    PRODUCT_TYPE_NOT_FOUND(ShoppingCartMessage.MsgName.PRODUCT_TYPE_NOT_FOUND, "IN_PT_NF");

    private ShoppingCartMessage.MsgName message;
    private String apiCode;
}
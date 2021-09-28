package com.shopping.cart.domain.exception;

import com.shopping.cart.infrastructure.config.ShoppingCartMessage;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum ShoppingCartNotificationCode {

    DATA_NOT_FOUND(ShoppingCartMessage.MsgName.DATA_NOT_FOUND, "IN_ERR_NF"),
    PRODUCT_TYPE_NOT_FOUND(ShoppingCartMessage.MsgName.PRODUCT_TYPE_NOT_FOUND, "IN_PT_NF"),
    BAD_REQUEST(ShoppingCartMessage.MsgName.BAD_REQUEST, "IN_BR"),
    BAD_REQUEST_STATE_PURCHASE(ShoppingCartMessage.MsgName.BAD_REQUEST_STATE_PURCHASE, "IN_BR_ST_CM"),
    BAD_REQUEST_EMPTY(ShoppingCartMessage.MsgName.BAD_REQUEST_EMPTY, "IN_BR_EMP");

    private ShoppingCartMessage.MsgName message;
    private String apiCode;
}

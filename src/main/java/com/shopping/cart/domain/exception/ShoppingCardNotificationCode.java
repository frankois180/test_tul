package com.shopping.cart.domain.exception;

import com.shopping.cart.infrastructure.config.ShoppingCardMessage;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum ShoppingCardNotificationCode {

    DATA_NOT_FOUND(ShoppingCardMessage.MsgName.DATA_NOT_FOUND, "IN_ERR_NF"),
    ACCESS_DENIED(ShoppingCardMessage.MsgName.ACCESS_DENIED, "IN_ACC_DND"),
    PRODUCT_TYPE_NOT_FOUND(ShoppingCardMessage.MsgName.PRODUCT_TYPE_NOT_FOUND, "IN_PT_NF");

    private ShoppingCardMessage.MsgName message;
    private String apiCode;
}

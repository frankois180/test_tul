package com.shopping.cart.domain.model.type;

import com.shopping.cart.domain.exception.DataNotFoundException;
import com.shopping.cart.domain.exception.ShoppingCartNotificationCode;

public enum ProductType {

    DISCOUNT, SIMPLE;

    public static ProductType fromDesc(String desc) {
        try {
            return ProductType.valueOf(desc.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new DataNotFoundException(ShoppingCartNotificationCode.PRODUCT_TYPE_NOT_FOUND, desc);
        }
    }


}

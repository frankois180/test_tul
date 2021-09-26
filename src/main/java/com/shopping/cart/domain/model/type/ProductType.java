package com.shopping.cart.domain.model.type;

import com.shopping.cart.domain.exception.DataNotFoundException;

public enum ProductType {

    DISCOUNT, SIMPLE;

    public static ProductType fromDesc(String desc) {
        try {
            return ProductType.valueOf(desc.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new DataNotFoundException(String.format("product type not found with the entered parameter, %s",
                    desc));
        }
    }


}

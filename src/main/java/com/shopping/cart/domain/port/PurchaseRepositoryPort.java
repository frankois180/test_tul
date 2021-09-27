package com.shopping.cart.domain.port;

import com.shopping.cart.domain.model.Purchase;

import java.math.BigDecimal;

public interface PurchaseRepositoryPort {

    /**
     * Method to create a purchase
     *
     * @param sku: sku product
     * @param amount: amount product
     * @return Purchase: Purchase object
     */
    Purchase save(String sku, BigDecimal amount);
}

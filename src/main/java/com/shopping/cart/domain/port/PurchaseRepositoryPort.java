package com.shopping.cart.domain.port;

import com.shopping.cart.domain.model.Product;
import com.shopping.cart.domain.model.Purchase;
import com.shopping.cart.infrastructure.adapter.shared.PageAsk;

import java.math.BigDecimal;
import java.util.function.Supplier;
import java.util.stream.Stream;

public interface PurchaseRepositoryPort {

    /**
     * Method to create a purchase
     *
     * @param sku: sku product
     * @param amount: amount product
     * @return Purchase: Purchase object
     */
    Purchase save(String sku, BigDecimal amount);

    /**
     * Method to add new product to purchase
     *
     * @param sku: sku product
     * @param amount: amount product
     * @param amount: purchaseCode product
     * @return Purchase: Purchase object
     */
    Purchase addProductCart(String sku, BigDecimal amount,String purchaseCode);

    /**
     *  Method to search all purchases
     *
     * @param code: purchase code
     * @return Purchase: Purchase Object
     */

    Purchase findByCode(String code);
}

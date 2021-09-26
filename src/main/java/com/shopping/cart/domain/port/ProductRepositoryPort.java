package com.shopping.cart.domain.port;

import com.shopping.cart.domain.model.Product;

public interface ProductRepositoryPort {

    /**
     * Method that register a product
     * @param product: Product object
     * @return Product: Product object
     */
    Product save(Product product);

}

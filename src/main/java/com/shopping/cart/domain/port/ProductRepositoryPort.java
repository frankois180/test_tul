package com.shopping.cart.domain.port;

import com.shopping.cart.domain.model.Product;
import com.shopping.cart.infrastructure.adapter.shared.PageAsk;

import java.util.function.Supplier;
import java.util.stream.Stream;

public interface ProductRepositoryPort {

    /**
     * Method to search all products
     *
     * @param product: Product object
     * @return Product: Product object
     */
    Product save(Product product);

    /**
     * Method that find all product
     *
     * @param pageAsk: paging parameters
     * @return List<Product>: Product list
     */
    Supplier<Stream<Product>> findAll(PageAsk pageAsk);

}

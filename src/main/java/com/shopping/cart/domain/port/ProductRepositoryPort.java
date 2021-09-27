package com.shopping.cart.domain.port;

import com.shopping.cart.domain.model.Product;
import com.shopping.cart.infrastructure.adapter.shared.PageAsk;

import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Stream;

public interface ProductRepositoryPort {

    /**
     * Method to create a product
     *
     * @param product: Product object
     * @return Product: Product object
     */
    Product save(Product product);

    /**
     *  Method to search all products
     *
     * @param pageAsk: paging parameters
     * @return List<Product>: Product list
     */

    Supplier<Stream<Product>> findAll(PageAsk pageAsk);

    /**
     * Method to delete a product
     *
     * @param sku: sku product
     * @return Product: Product deleted object
     */
    Optional<Product> deleteById(String sku);

    /**
     * Method to update a product by sku
     *
     * @param sku: sku product
     * @return Product: Product updating object
     */

    Optional<Product> updateById(String sku,Product product);

}

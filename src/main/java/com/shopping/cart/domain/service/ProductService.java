package com.shopping.cart.domain.service;

import com.shopping.cart.domain.model.Product;
import com.shopping.cart.domain.port.ProductRepositoryPort;
import com.shopping.cart.infrastructure.adapter.shared.PageAsk;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepositoryPort productRepositoryPort;

    public Product save(Product product){
        return productRepositoryPort.save(product);
    }

   public Supplier<Stream<Product>> findAll(PageAsk pageAsk){
       return productRepositoryPort.findAll(pageAsk);
    }
}

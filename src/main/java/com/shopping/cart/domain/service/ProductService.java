package com.shopping.cart.domain.service;

import com.shopping.cart.domain.model.Product;
import com.shopping.cart.domain.port.ProductRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepositoryPort productRepositoryPort;

    public Product save(Product product){
        return productRepositoryPort.save(product);
    }
}

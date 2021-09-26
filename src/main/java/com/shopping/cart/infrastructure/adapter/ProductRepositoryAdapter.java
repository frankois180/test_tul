package com.shopping.cart.infrastructure.adapter;

import com.shopping.cart.domain.model.Product;
import com.shopping.cart.domain.port.ProductRepositoryPort;
import com.shopping.cart.infrastructure.adapter.repository.entity.ProductEntity;
import com.shopping.cart.infrastructure.adapter.repository.jpa.ProductJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ProductRepositoryAdapter implements ProductRepositoryPort {
    private final ProductJpaRepository productJpaRepository;

    @Override
    public Product save(Product product) {
        return fromDomain(productJpaRepository.save(fromEntity(product)));
    }

    private Product fromDomain(ProductEntity productEntity){
        Product product = new Product();
        product.setName(productEntity.getName());
        product.setPrice(productEntity.getPrice());
        product.setProductType(productEntity.getProductType());
        product.setDescription(productEntity.getDescription());
        product.setSku(productEntity.getSku());
        return  product;
    }

    private ProductEntity fromEntity(Product product){
        ProductEntity productEntity = new ProductEntity();
        productEntity.setName(product.getName());
        productEntity.setPrice(product.getPrice());
        productEntity.setProductType(product.getProductType());
        productEntity.setDescription(product.getDescription());
        productEntity.setSku(UUID.randomUUID().toString());
        return  productEntity;
    }

}

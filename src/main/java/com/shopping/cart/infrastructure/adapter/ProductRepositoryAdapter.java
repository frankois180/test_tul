package com.shopping.cart.infrastructure.adapter;

import com.shopping.cart.domain.model.Product;
import com.shopping.cart.domain.port.ProductRepositoryPort;
import com.shopping.cart.infrastructure.adapter.repository.entity.ProductEntity;
import com.shopping.cart.infrastructure.adapter.repository.jpa.ProductJpaRepository;
import com.shopping.cart.infrastructure.adapter.shared.PageAsk;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;
import java.util.function.Supplier;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class ProductRepositoryAdapter implements ProductRepositoryPort {
    private final ProductJpaRepository productJpaRepository;

    @Override
    public Product save(Product product) {
        return fromEntity(productJpaRepository.save(fromDomain(product)));
    }

    @Override
    public Supplier<Stream<Product>> findAll(PageAsk pageAsk) {
        return productJpaRepository.findAll(PageRequest.of(pageAsk.getPage(),
                pageAsk.getSize())).map(ProductRepositoryAdapter::fromEntity);
    }

    @Override
    public Optional<Product> delete(String sku) {
       return productJpaRepository.findById(sku).map(mapper -> {
            productJpaRepository.delete(mapper);
            return fromEntity(mapper);
        });
    }

    private static Product fromEntity(ProductEntity productEntity) {
        Product product = new Product();
        product.setName(productEntity.getName());
        product.setPrice(productEntity.getPrice());
        product.setProductType(productEntity.getProductType());
        product.setDescription(productEntity.getDescription());
        product.setSku(productEntity.getSku());
        return product;
    }

    private static ProductEntity fromDomain(Product product) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setName(product.getName());
        productEntity.setPrice(product.getPrice());
        productEntity.setProductType(product.getProductType());
        productEntity.setDescription(product.getDescription());
        productEntity.setSku(UUID.randomUUID().toString());
        return productEntity;
    }

}

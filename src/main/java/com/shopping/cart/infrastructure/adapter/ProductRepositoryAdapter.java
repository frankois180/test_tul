package com.shopping.cart.infrastructure.adapter;

import com.shopping.cart.domain.model.Product;
import com.shopping.cart.domain.port.ProductRepositoryPort;
import com.shopping.cart.infrastructure.adapter.repository.jpa.ProductJpaRepository;
import com.shopping.cart.infrastructure.adapter.repository.mapper.ProductMapper;
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
        String sku = UUID.randomUUID().toString();
        return ProductMapper.fromEntity(productJpaRepository.save(ProductMapper.fromDomain(product, sku)));
    }

    @Override
    public Supplier<Stream<Product>> findAll(PageAsk pageAsk) {
        return productJpaRepository.findAll(PageRequest.of(pageAsk.getPage(),
                pageAsk.getSize())).map(ProductMapper::fromEntity);
    }

    @Override
    public Optional<Product> deleteById(String sku) {
        return productJpaRepository.findById(sku).map(mapper -> {
            productJpaRepository.delete(mapper);
            return ProductMapper.fromEntity(mapper);
        });
    }

    @Override
    public Optional<Product> updateById(String sku, Product product) {
        return productJpaRepository.findById(sku)
                .map(mapper -> ProductMapper
                        .fromEntity(productJpaRepository.save(ProductMapper.fromDomain(product, mapper.getSku()))));

    }


}

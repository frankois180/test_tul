package com.shopping.cart.infrastructure.adapter.repository.jpa;

import com.shopping.cart.infrastructure.adapter.repository.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductJpaRepository extends JpaRepository<ProductEntity, String> {
}

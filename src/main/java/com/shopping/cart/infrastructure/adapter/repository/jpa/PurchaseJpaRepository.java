package com.shopping.cart.infrastructure.adapter.repository.jpa;

import com.shopping.cart.infrastructure.adapter.repository.entity.PurchaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseJpaRepository extends JpaRepository<PurchaseEntity,String> {
}

package com.shopping.cart.infrastructure.adapter.repository.entity;

import com.shopping.cart.domain.model.type.ProductType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@Entity(name = "product")
public class ProductEntity {
    @Id
    private String sku;

    private String name;

    private String description;

    private BigDecimal price;

    @Column(name = "product_ype")
    @Enumerated(EnumType.STRING)
    private ProductType productType;

    public ProductEntity() {
        this.sku = UUID.randomUUID().toString();
    }
}
package com.shopping.cart.domain.model;

import com.shopping.cart.domain.model.type.ProductType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
public class Product {

    private String sku;

    private String name;

    private String description;

    private BigDecimal price;

    @Column(name = "product_ype")
    @Enumerated(EnumType.STRING)
    private ProductType productType;

    public  Product() {
        this.price =  productType.equals(ProductType.SIMPLE) ? price : price.divide(BigDecimal.valueOf(2));
    }
}

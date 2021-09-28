package com.shopping.cart.infrastructure.adapter.repository.mapper;

import com.shopping.cart.domain.model.Product;
import com.shopping.cart.infrastructure.adapter.repository.entity.ProductEntity;

public class ProductMapper {

    public static Product fromEntity(ProductEntity productEntity) {
        Product product = new Product();
        product.setName(productEntity.getName());
        product.setPrice(productEntity.getPrice());
        product.setProductType(productEntity.getProductType());
        product.setDescription(productEntity.getDescription());
        product.setSku(productEntity.getSku());
        return product;
    }

    public static ProductEntity fromDomain(Product product, String sku) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setName(product.getName());
        productEntity.setPrice(product.getPrice());
        productEntity.setProductType(product.getProductType());
        productEntity.setDescription(product.getDescription());
        productEntity.setSku(sku);
        return productEntity;
    }
}

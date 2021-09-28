package com.shopping.cart.application.mapper;

import com.shopping.cart.domain.model.Product;
import com.shopping.cart.domain.model.type.ProductType;
import com.shopping.cart.infrastructure.controller.dto.ProductDTO;

public class ProductAppMapper {

    public static ProductDTO fromDto(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());
        productDTO.setSku(product.getSku());
        productDTO.setProductType(ProductType.fromDesc(product.getProductType().name()).name());

        return productDTO;
    }

    public static Product fromDomain(ProductDTO productDTO) {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setProductType(ProductType.fromDesc(productDTO.getProductType()));

        return product;
    }
}

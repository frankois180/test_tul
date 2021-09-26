package com.shopping.cart.application.service;

import com.shopping.cart.domain.model.Product;
import com.shopping.cart.domain.model.type.ProductType;
import com.shopping.cart.domain.service.ProductService;
import com.shopping.cart.infrastructure.controller.dto.ProductDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductAppService {

    private final ProductService productService;

    public ProductDTO save(ProductDTO productDTO) {
        return fromDto(productService.save(fromDomain(productDTO)));
    }

    private ProductDTO fromDto(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());
        productDTO.setProductType(ProductType.fromDesc(product.getProductType().name()).name());

        return productDTO;
    }

    private Product fromDomain(ProductDTO productDTO) {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setProductType(ProductType.fromDesc(productDTO.getProductType()));

        return product;
    }

}

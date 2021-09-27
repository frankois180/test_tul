package com.shopping.cart.application.service;

import com.shopping.cart.domain.model.Product;
import com.shopping.cart.domain.model.type.ProductType;
import com.shopping.cart.domain.service.ProductService;
import com.shopping.cart.infrastructure.adapter.shared.PageAsk;
import com.shopping.cart.infrastructure.controller.dto.ProductDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class ProductAppService {

    private final ProductService productService;

    public ProductDTO save(ProductDTO productDTO) {
        return fromDto(productService.save(fromDomain(productDTO)));
    }

    public Supplier<Stream<ProductDTO>> findAll(PageAsk pageAsk) {
        return ((Page<Product>) productService.findAll(pageAsk)).map(ProductAppService::fromDto);
    }

    public  ProductDTO deleteById(String sku){
      return  fromDto(productService.deleteById(sku));
    }

    public  ProductDTO updateById(String sku,ProductDTO productDTO){
        return  fromDto(productService.updateById(sku,fromDomain(productDTO)));
    }


    private static ProductDTO fromDto(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());
        productDTO.setSku(product.getSku());
        productDTO.setProductType(ProductType.fromDesc(product.getProductType().name()).name());

        return productDTO;
    }

    private static Product fromDomain(ProductDTO productDTO) {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setProductType(ProductType.fromDesc(productDTO.getProductType()));

        return product;
    }

}

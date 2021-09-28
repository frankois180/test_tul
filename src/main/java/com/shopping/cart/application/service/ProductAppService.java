package com.shopping.cart.application.service;

import com.shopping.cart.application.mapper.ProductAppMapper;
import com.shopping.cart.domain.model.Product;
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
        return ProductAppMapper.fromDto(productService.save(ProductAppMapper.fromDomain(productDTO)));
    }

    public Supplier<Stream<ProductDTO>> findAll(PageAsk pageAsk) {
        return ((Page<Product>) productService.findAll(pageAsk)).map(ProductAppMapper::fromDto);
    }

    public ProductDTO deleteById(String sku) {
        return ProductAppMapper.fromDto(productService.deleteById(sku));
    }

    public ProductDTO updateById(String sku, ProductDTO productDTO) {
        return ProductAppMapper.fromDto(productService.updateById(sku, ProductAppMapper.fromDomain(productDTO)));
    }


}

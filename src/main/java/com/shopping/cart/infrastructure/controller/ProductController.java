package com.shopping.cart.infrastructure.controller;

import com.shopping.cart.application.service.ProductAppService;
import com.shopping.cart.infrastructure.adapter.shared.PageAsk;
import com.shopping.cart.infrastructure.controller.dto.ApiResponseDto;
import com.shopping.cart.infrastructure.controller.dto.ProductDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductAppService productAppService;

    @PostMapping
    public ApiResponseDto save(@Validated @RequestBody ProductDTO productDTO) {
        return ApiResponseDto.builder()
                .data(productAppService.save(productDTO))
                .build();

    }

    @GetMapping
    public Page<ProductDTO> findAll(@RequestParam(defaultValue = "0") Integer pageNumParam,
                                    @RequestParam(defaultValue = "10") Integer pageSizeParam) {

        return ((Page<ProductDTO>) productAppService.findAll(PageAsk.create(pageNumParam, pageSizeParam)));
    }

    @DeleteMapping("/{sku}")
    public ApiResponseDto findById(@PathVariable String sku) {
        return ApiResponseDto.builder()
                .data(productAppService.delete(sku))
                .build();
    }

}

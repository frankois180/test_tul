package com.shopping.cart.infrastructure.controller;

import com.shopping.cart.application.service.ProductAppService;
import com.shopping.cart.infrastructure.controller.dto.ApiResponseDto;
import com.shopping.cart.infrastructure.controller.dto.ProductDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

}

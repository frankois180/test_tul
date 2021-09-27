package com.shopping.cart.infrastructure.controller;

import com.shopping.cart.application.service.PurchaseAppService;
import com.shopping.cart.infrastructure.controller.dto.ApiResponseDto;
import com.shopping.cart.infrastructure.controller.dto.PurchaseRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/purchase")
@RequiredArgsConstructor
public class PurchaseController {

    private final PurchaseAppService purchaseAppService;


    @PostMapping
    public ApiResponseDto save(@Validated @RequestBody PurchaseRequest purchaseRequest) {
        return ApiResponseDto.builder()
                .data(purchaseAppService.save(purchaseRequest))
                .build();
    }
}

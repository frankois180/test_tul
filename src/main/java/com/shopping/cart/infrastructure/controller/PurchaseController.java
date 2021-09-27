package com.shopping.cart.infrastructure.controller;

import com.shopping.cart.application.service.PurchaseAppService;
import com.shopping.cart.infrastructure.adapter.shared.PageAsk;
import com.shopping.cart.infrastructure.controller.dto.ApiResponseDto;
import com.shopping.cart.infrastructure.controller.dto.ProductDTO;
import com.shopping.cart.infrastructure.controller.dto.PurchaseDTO;
import com.shopping.cart.infrastructure.controller.dto.PurchaseRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("/{code}")
    public PurchaseDTO findByCode(@PathVariable String code) {
        return  purchaseAppService.findByCode(code);
    }
}

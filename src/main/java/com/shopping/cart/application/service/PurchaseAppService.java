package com.shopping.cart.application.service;

import com.shopping.cart.application.mapper.PurchaseAppMapper;
import com.shopping.cart.domain.service.PurchaseService;
import com.shopping.cart.infrastructure.controller.dto.PurchaseDTO;
import com.shopping.cart.infrastructure.controller.dto.PurchaseRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PurchaseAppService {

    private final PurchaseService purchaseService;

    public PurchaseDTO save(PurchaseRequest purchaseRequest) {
        return PurchaseAppMapper.fromDomain(purchaseService.save(purchaseRequest.getSkuProduct(),
                purchaseRequest.getAmount()));
    }

    public PurchaseDTO addNewProduct(PurchaseRequest purchaseRequest, String purchaseCode) {
        return PurchaseAppMapper.fromDomain(purchaseService.addNewProduct(purchaseRequest.getSkuProduct(),
                purchaseRequest.getAmount(), purchaseCode));
    }

    public PurchaseDTO findByCode(String code) {
        return PurchaseAppMapper.fromDomain(purchaseService.findByCode(code));
    }

    public  PurchaseDTO deleteByCodeAndProductSku(String code,String sku){
        return  PurchaseAppMapper.fromDomain(purchaseService.deleteByCodeAndSku(code,sku));
    }


}

package com.shopping.cart.application.service;

import com.shopping.cart.domain.model.Purchase;
import com.shopping.cart.domain.model.PurchaseDetail;
import com.shopping.cart.domain.service.PurchaseService;
import com.shopping.cart.infrastructure.controller.dto.PurchaseDTO;
import com.shopping.cart.infrastructure.controller.dto.PurchaseDetailDTO;
import com.shopping.cart.infrastructure.controller.dto.PurchaseRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PurchaseAppService {

    private final PurchaseService purchaseService;

    public PurchaseDTO save(PurchaseRequest purchaseRequest) {
        return fromDomain(purchaseService.save(purchaseRequest.getSkuProduct(), purchaseRequest.getAmount()));
    }

    private static PurchaseDTO fromDomain(Purchase purchase) {
        PurchaseDTO purchaseDTO = new PurchaseDTO();
        purchaseDTO.setCode(purchase.getCode());
        purchaseDTO.setCreationDate(purchase.getCreationDate());
        purchaseDTO.setStatus(purchase.getStatus());
        purchaseDTO.setTotalValue(purchase.getTotalValue());
        purchaseDTO.setDetails(purchase.getDetails().stream().map(PurchaseAppService::fromDomainDetail).collect(
                Collectors.toList()));
        return purchaseDTO;
    }

    private static PurchaseDetailDTO fromDomainDetail(PurchaseDetail purchaseDetail) {
        PurchaseDetailDTO purchaseDetailDTO = new PurchaseDetailDTO();
        purchaseDetailDTO.setTotalValue(purchaseDetail.getTotalValue());
        purchaseDetailDTO.setAmount(purchaseDetail.getAmount());
        purchaseDetailDTO.setProduct(ProductAppService.fromDto(purchaseDetail.getProduct()));
        purchaseDetailDTO.setProductValue(purchaseDetail.getProductValue());
        return purchaseDetailDTO;
    }
}

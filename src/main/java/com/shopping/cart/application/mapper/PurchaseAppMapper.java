package com.shopping.cart.application.mapper;

import com.shopping.cart.domain.model.Purchase;
import com.shopping.cart.domain.model.PurchaseDetail;
import com.shopping.cart.infrastructure.controller.dto.PurchaseDTO;
import com.shopping.cart.infrastructure.controller.dto.PurchaseDetailDTO;

import java.util.stream.Collectors;

public class PurchaseAppMapper {

    public static PurchaseDTO fromDomain(Purchase purchase) {
        PurchaseDTO purchaseDTO = new PurchaseDTO();
        purchaseDTO.setCode(purchase.getCode());
        purchaseDTO.setCreationDate(purchase.getCreationDate());
        purchaseDTO.setStatus(purchase.getStatus());
        purchaseDTO.setTotalValue(purchase.getTotalValue());
        purchaseDTO.setDetails(purchase.getDetails().stream().map(PurchaseAppMapper::fromDomainDetail).collect(
                Collectors.toList()));
        return purchaseDTO;
    }

    public static PurchaseDetailDTO fromDomainDetail(PurchaseDetail purchaseDetail) {
        PurchaseDetailDTO purchaseDetailDTO = new PurchaseDetailDTO();
        purchaseDetailDTO.setTotalValue(purchaseDetail.getTotalValue());
        purchaseDetailDTO.setAmount(purchaseDetail.getAmount());
        purchaseDetailDTO.setProduct(ProductAppMapper.fromDto(purchaseDetail.getProduct()));
        purchaseDetailDTO.setProductValue(purchaseDetail.getProductValue());
        return purchaseDetailDTO;
    }

}

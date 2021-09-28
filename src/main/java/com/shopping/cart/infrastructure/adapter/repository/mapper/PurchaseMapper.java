package com.shopping.cart.infrastructure.adapter.repository.mapper;

import com.shopping.cart.domain.model.Purchase;
import com.shopping.cart.domain.model.PurchaseDetail;
import com.shopping.cart.domain.model.type.PurchaseStatus;
import com.shopping.cart.infrastructure.adapter.repository.entity.PurchaseDetailEntity;
import com.shopping.cart.infrastructure.adapter.repository.entity.PurchaseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class PurchaseMapper {

    public static Purchase fromEntity(PurchaseEntity purchaseEntity) {
        Purchase purchase = new Purchase();
        purchase.setCode(purchaseEntity.getCode());
        purchase.setCreationDate(purchaseEntity.getCreationDate());
        purchase.setStatus(purchaseEntity.getStatus());
        purchase.setTotalValue(purchaseEntity.getTotalValue());
        purchase.setDetails(
                purchaseEntity.getDetails().stream().map(PurchaseMapper::fromEntityDetail).collect(
                        Collectors.toList()));
        return purchase;
    }

    public static PurchaseEntity fromDomain(BigDecimal totalValue,
                                     String code, List<PurchaseDetail> details) {

        PurchaseEntity purchaseEntity = new PurchaseEntity();
        purchaseEntity.setCode(code);
        purchaseEntity.setCreationDate(LocalDateTime.now());
        purchaseEntity.setStatus(PurchaseStatus.PENDING);
        purchaseEntity.setTotalValue(totalValue);
        purchaseEntity.setDetails(
                details.stream().map(PurchaseMapper::fromDomainDetail).collect(Collectors.toList()));

        return purchaseEntity;
    }

    public static PurchaseDetail fromEntityDetail(PurchaseDetailEntity purchaseDetailEntity) {
        PurchaseDetail purchaseDetail = new PurchaseDetail();
        purchaseDetail.setProduct(ProductMapper.fromEntity(purchaseDetailEntity.getProduct()));
        purchaseDetail.setProductValue(purchaseDetailEntity.getProductValue());
        purchaseDetail.setAmount(purchaseDetailEntity.getAmount());
        purchaseDetail.setTotalValue(purchaseDetailEntity.getTotalValue());
        return purchaseDetail;
    }

    public static PurchaseDetailEntity fromDomainDetail(PurchaseDetail purchaseDetail) {
        PurchaseDetailEntity purchaseDetailEntity = new PurchaseDetailEntity();
        purchaseDetailEntity.setProduct(ProductMapper.fromDomain(purchaseDetail.getProduct(),
                purchaseDetail.getProduct().getSku()));
        purchaseDetailEntity.setProductValue(purchaseDetail.getProductValue());
        purchaseDetailEntity.setAmount(purchaseDetail.getAmount());
        purchaseDetailEntity.setTotalValue(purchaseDetail.getTotalValue());
        return purchaseDetailEntity;
    }
}

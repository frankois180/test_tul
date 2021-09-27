package com.shopping.cart.infrastructure.adapter;

import com.shopping.cart.domain.exception.DataNotFoundException;
import com.shopping.cart.domain.exception.ShoppingCartNotificationCode;
import com.shopping.cart.domain.model.Product;
import com.shopping.cart.domain.model.Purchase;
import com.shopping.cart.domain.model.PurchaseDetail;
import com.shopping.cart.domain.model.type.PurchaseStatus;
import com.shopping.cart.domain.port.PurchaseRepositoryPort;
import com.shopping.cart.infrastructure.adapter.repository.entity.PurchaseDetailEntity;
import com.shopping.cart.infrastructure.adapter.repository.entity.PurchaseEntity;
import com.shopping.cart.infrastructure.adapter.repository.jpa.ProductJpaRepository;
import com.shopping.cart.infrastructure.adapter.repository.jpa.PurchaseJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PurchaseRepositoryAdapter implements PurchaseRepositoryPort {

    private final PurchaseJpaRepository purchaseJpaRepository;
    private final ProductJpaRepository productJpaRepository;

    @Override
    public Purchase save(String sku, BigDecimal amount) {
        Product product = validateProduct(sku);
        BigDecimal totalValue = calculateTotalValue(amount, product.getTotalValue());
        return fromEntity(purchaseJpaRepository.save(fromDomain(sku, amount, totalValue, product)));
    }

    private BigDecimal calculateTotalValue(BigDecimal amount, BigDecimal productValue) {
        return productValue.multiply(amount);
    }

    private Product validateProduct(String sku) {
        return ProductRepositoryAdapter
                .fromEntity(productJpaRepository.findById(sku).orElseThrow(() -> new DataNotFoundException(
                        ShoppingCartNotificationCode.DATA_NOT_FOUND)));
    }

    private PurchaseEntity fromDomain(String sku, BigDecimal amount, BigDecimal totalValue, Product product) {

        PurchaseEntity purchaseEntity = new PurchaseEntity();
        purchaseEntity.setCode(UUID.randomUUID().toString());
        purchaseEntity.setCreationDate(LocalDateTime.now());
        purchaseEntity.setStatus(PurchaseStatus.PENDING);
        purchaseEntity.setTotalValue(totalValue);

        PurchaseDetailEntity purchaseDetailEntity = new PurchaseDetailEntity();
        purchaseDetailEntity.setAmount(amount);
        purchaseDetailEntity.setTotalValue(totalValue);
        purchaseDetailEntity.setProductValue(product.getTotalValue());
        purchaseDetailEntity.setProduct(ProductRepositoryAdapter.fromDomain(product, sku));
        purchaseEntity.setDetails(Arrays.asList(purchaseDetailEntity));
        return purchaseEntity;
    }

    private static Purchase fromEntity(PurchaseEntity purchaseEntity) {
        Purchase purchase = new Purchase();
        purchase.setCode(purchaseEntity.getCode());
        purchase.setCreationDate(purchaseEntity.getCreationDate());
        purchase.setStatus(purchaseEntity.getStatus());
        purchase.setTotalValue(purchaseEntity.getTotalValue());
        purchase.setDetails(
                purchaseEntity.getDetails().stream().map(PurchaseRepositoryAdapter::fromEntityDetail).collect(
                        Collectors.toList()));
        return purchase;
    }


    private static PurchaseDetail fromEntityDetail(PurchaseDetailEntity purchaseDetailEntity) {
        PurchaseDetail purchaseDetail = new PurchaseDetail();
        purchaseDetail.setProduct(ProductRepositoryAdapter.fromEntity(purchaseDetailEntity.getProduct()));
        purchaseDetail.setProductValue(purchaseDetailEntity.getProductValue());
        purchaseDetail.setAmount(purchaseDetailEntity.getAmount());
        purchaseDetail.setTotalValue(purchaseDetailEntity.getTotalValue());
        return purchaseDetail;
    }

    private static PurchaseDetailEntity fromDomainDetail(PurchaseDetail purchaseDetail) {
        PurchaseDetailEntity purchaseDetailEntity = new PurchaseDetailEntity();
        purchaseDetailEntity.setProduct(ProductRepositoryAdapter.fromDomain(purchaseDetail.getProduct(),
                purchaseDetail.getProduct().getSku()));
        purchaseDetailEntity.setProductValue(purchaseDetailEntity.getProductValue());
        purchaseDetailEntity.setAmount(purchaseDetail.getAmount());
        purchaseDetailEntity.setTotalValue(purchaseDetail.getTotalValue());
        return purchaseDetailEntity;
    }
}

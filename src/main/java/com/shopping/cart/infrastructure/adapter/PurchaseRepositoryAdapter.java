package com.shopping.cart.infrastructure.adapter;

import com.shopping.cart.domain.exception.BadRequestException;
import com.shopping.cart.domain.exception.DataNotFoundException;
import com.shopping.cart.domain.exception.ShoppingCartNotificationCode;
import com.shopping.cart.domain.model.Product;
import com.shopping.cart.domain.model.Purchase;
import com.shopping.cart.domain.model.PurchaseDetail;
import com.shopping.cart.domain.port.PurchaseRepositoryPort;
import com.shopping.cart.infrastructure.adapter.repository.entity.PurchaseDetailEntity;
import com.shopping.cart.infrastructure.adapter.repository.jpa.ProductJpaRepository;
import com.shopping.cart.infrastructure.adapter.repository.jpa.PurchaseJpaRepository;
import com.shopping.cart.infrastructure.adapter.repository.mapper.ProductMapper;
import com.shopping.cart.infrastructure.adapter.repository.mapper.PurchaseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
        BigDecimal totalValue = calculateTotalValueProduct(amount, product.getTotalValue());
        return PurchaseMapper.fromEntity(purchaseJpaRepository.save(PurchaseMapper.fromDomain(totalValue,
                UUID.randomUUID().toString(),
                Arrays.asList(PurchaseMapper.fromEntityDetail(addDetail(sku, amount, totalValue, product))))));
    }

    @Override
    public Purchase addProductCart(String sku, BigDecimal amount, String purchaseCode) {
        Purchase purchase = findByCode(purchaseCode);
        Product product = validateProduct(sku);
        List<PurchaseDetail> details = new ArrayList<>();
        details.addAll(purchase.getDetails());
        validateProductExistence(details, sku);

        details.add(addNewDetail(amount, product));
        purchase.setTotalValue(calculateTotalValue(purchase.getDetails()));
        BigDecimal totalValueProduct = calculateTotalValueProduct(amount, product.getTotalValue());
        BigDecimal totalValue = calculateTotalValue(purchase.getDetails()).add(totalValueProduct);

        return PurchaseMapper.fromEntity(purchaseJpaRepository.save(PurchaseMapper.fromDomain(totalValue,
                purchaseCode,
                details)));
    }

    @Override
    public Purchase findByCode(String code) {
        return PurchaseMapper
                .fromEntity(purchaseJpaRepository.findById(code).orElseThrow(() -> new DataNotFoundException(
                        ShoppingCartNotificationCode.DATA_NOT_FOUND)));
    }

    @Override
    public Purchase deleteByCodeAndSku(String code, String sku) {
        Purchase purchase = findByCode(code);
        List<PurchaseDetail> details =
                purchase.getDetails().stream().filter(mapper -> !mapper.getProduct().getSku().equals(sku))
                        .collect(Collectors.toList());

        shoppingCartIsEmpty(details, code);

        return PurchaseMapper
                .fromEntity(purchaseJpaRepository.save(PurchaseMapper.fromDomain(calculateTotalValue(details), code,
                        details)));
    }

    @Override
    public Purchase updateByCodeAndProductSku(String code, String sku, BigDecimal amount) {
        Purchase purchase = findByCode(code);

        List<PurchaseDetail> details =
                purchase.getDetails().stream().filter(mapper -> !mapper.getProduct().getSku().equals(sku))
                        .collect(Collectors.toList());
        Product product = validateProduct(sku);
        details.add(addNewDetail(amount, product));

        BigDecimal totalValue = calculateTotalValue(details);
        return PurchaseMapper.fromEntity(purchaseJpaRepository.save(PurchaseMapper.fromDomain(totalValue,
                code,
                details)));
    }

    private void shoppingCartIsEmpty(List<PurchaseDetail> details, String code) {
        if (details.isEmpty()) {
            purchaseJpaRepository.deleteById(code);
            throw new BadRequestException(ShoppingCartNotificationCode.BAD_REQUEST_EMPTY);
        }
    }

    private void validateProductExistence(List<PurchaseDetail> details, String sku) {
        if (details.stream().anyMatch(mapper -> mapper.getProduct().getSku().equals(sku))) {
            throw new BadRequestException(ShoppingCartNotificationCode.BAD_REQUEST, sku);
        }
    }


    private BigDecimal calculateTotalValueProduct(BigDecimal amount, BigDecimal productValue) {
        return productValue.multiply(amount);
    }

    private BigDecimal calculateTotalValue(List<PurchaseDetail> details) {
        return details.stream().map(PurchaseDetail::getTotalValue).reduce(BigDecimal.ZERO,
                BigDecimal::add);
    }

    private Product validateProduct(String sku) {
        return ProductMapper
                .fromEntity(productJpaRepository.findById(sku).orElseThrow(() -> new DataNotFoundException(
                        ShoppingCartNotificationCode.DATA_NOT_FOUND)));
    }

    private PurchaseDetailEntity addDetail(String sku, BigDecimal amount, BigDecimal totalValueProduct,
                                           Product product) {
        PurchaseDetailEntity purchaseDetailEntity = new PurchaseDetailEntity();
        purchaseDetailEntity.setAmount(amount);
        purchaseDetailEntity.setTotalValue(totalValueProduct);
        purchaseDetailEntity.setProductValue(product.getTotalValue());
        purchaseDetailEntity.setProduct(ProductMapper.fromDomain(product, sku));
        return purchaseDetailEntity;
    }

    private PurchaseDetail addNewDetail(BigDecimal amount, Product product) {
        PurchaseDetail purchaseDetail = new PurchaseDetail();
        purchaseDetail.setAmount(amount);
        purchaseDetail.setProductValue(product.getTotalValue());
        purchaseDetail.setProduct(product);
        purchaseDetail.setTotalValue(calculateTotalValueProduct(amount, product.getTotalValue()));
        return purchaseDetail;
    }

}

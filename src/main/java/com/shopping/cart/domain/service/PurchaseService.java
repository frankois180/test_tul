package com.shopping.cart.domain.service;

import com.shopping.cart.domain.model.Purchase;
import com.shopping.cart.domain.port.PurchaseRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class PurchaseService {

    private final PurchaseRepositoryPort purchaseRepositoryPort;

    public Purchase save(String sku, BigDecimal amount) {
        return purchaseRepositoryPort.save(sku, amount);
    }

    public Purchase addNewProduct(String sku, BigDecimal amount, String purchaseCode) {
        return purchaseRepositoryPort.addProductCart(sku, amount, purchaseCode);
    }

    public Purchase findByCode(String code) {
        return purchaseRepositoryPort.findByCode(code);
    }

    public Purchase deleteByCodeAndSku(String code, String sku) {
      return purchaseRepositoryPort.deleteByCodeAndSku(code, sku);
    }

    public Purchase updateByCodeAndProductSku(String code, String sku,BigDecimal amount) {
        return purchaseRepositoryPort.updateByCodeAndProductSku(code, sku,amount);
    }

    public BigDecimal checkout(String code) {
        return purchaseRepositoryPort.checkout(code);
    }
}

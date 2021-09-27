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

    public Purchase findByCode(String code) {
        return purchaseRepositoryPort.findByCode(code);
    }
}

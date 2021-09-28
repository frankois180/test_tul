package com.shopping.cart.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseDetail {

    private BigDecimal productValue;
    private BigDecimal totalValue;
    private Product product;
    private BigDecimal amount;
}

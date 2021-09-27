package com.shopping.cart.infrastructure.controller.dto;

import com.shopping.cart.domain.model.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseDetailDTO {
    private BigDecimal productValue;
    private BigDecimal totalValue;
    private BigDecimal amount;
    private ProductDTO product;

}

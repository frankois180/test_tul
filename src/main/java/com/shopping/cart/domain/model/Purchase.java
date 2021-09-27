package com.shopping.cart.domain.model;

import com.shopping.cart.domain.model.type.PurchaseStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Purchase {
    private String code;
    private LocalDateTime creationDate;
    private PurchaseStatus status;
    private BigDecimal totalValue;
    private List<PurchaseDetail> details;
}

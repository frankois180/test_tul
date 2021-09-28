package com.shopping.cart.infrastructure.controller.dto;

import com.shopping.cart.domain.model.PurchaseDetail;
import com.shopping.cart.domain.model.type.PurchaseStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseDTO {

    private String code;
    private LocalDateTime creationDate;
    private LocalDateTime purchaseDate;
    private PurchaseStatus status;
    private BigDecimal totalValue;
    private List<PurchaseDetailDTO> details;

}

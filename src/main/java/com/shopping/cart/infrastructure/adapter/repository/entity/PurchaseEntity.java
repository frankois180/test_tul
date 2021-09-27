package com.shopping.cart.infrastructure.adapter.repository.entity;

import com.shopping.cart.domain.model.type.PurchaseStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "purchase")
public class PurchaseEntity {

    @Id
    private String code;
    @Column(name = "creation_date")
    private LocalDateTime creationDate;
    @Column(name = "product_ype")
    @Enumerated(EnumType.STRING)
    private PurchaseStatus status;
    private BigDecimal totalValue;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "purchaseCode")
    private List<PurchaseDetailEntity> details;

}

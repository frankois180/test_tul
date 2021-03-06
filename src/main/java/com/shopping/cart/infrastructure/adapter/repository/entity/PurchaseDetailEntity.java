package com.shopping.cart.infrastructure.adapter.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "purchase_detail")
public class PurchaseDetailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private BigDecimal productValue;
    @Column(nullable = false)
    private BigDecimal totalValue;
    @OneToOne
    @JoinColumn(name="productSku")
    private ProductEntity product;
    @Column(nullable = false)
    private BigDecimal amount;

}

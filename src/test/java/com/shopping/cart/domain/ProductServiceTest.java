package com.shopping.cart.domain;

import com.shopping.cart.domain.model.Product;
import com.shopping.cart.domain.model.type.ProductType;
import com.shopping.cart.domain.port.ProductRepositoryPort;
import com.shopping.cart.domain.service.ProductService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;

public class ProductServiceTest {

    private ProductService productService;

    private ProductRepositoryPort productRepositoryPort;

    private Product product;
    private  Product productDiscount;
    private  static  final BigDecimal PRICE = BigDecimal.valueOf(50000);
    private  static  final BigDecimal PRICE_DISCOUNT = BigDecimal.valueOf(25000);


    @BeforeEach
    public void init() {
        productRepositoryPort = Mockito.mock(ProductRepositoryPort.class);
        productService = new ProductService(productRepositoryPort);
        product = new Product("", "", "",
                PRICE, ProductType.SIMPLE);

        productDiscount = new Product("", "", "",
                PRICE, ProductType.DISCOUNT);
    }

    @Test
    public void when_saveProduct_withType_SIMPLE_then_return_the_object_notNull() {

        // arrange
        Mockito.when(productRepositoryPort.save(Mockito.any())).thenReturn(product);
        Product productResult = productService.save(product);
        // act - assert
        Assertions.assertThat(productResult).isNotNull();
    }

    @Test
    public void when_saveProduct_withType_SIMPLE_then_return_the_same_value() {

        // arrange
        Mockito.when(productRepositoryPort.save(Mockito.any())).thenReturn(product);
        Product productResult = productService.save(product);
        // act - assert
        Assertions.assertThat(productResult.getTotalValue()).isEqualTo(PRICE);
    }

    @Test
    public void when_saveProduct_withType_DISCOUNT_then_return_the_same_value_divide_for_two() {

        // arrange
        Mockito.when(productRepositoryPort.save(Mockito.any())).thenReturn(productDiscount);
        Product productResult = productService.save(productDiscount);
        // act - assert
        Assertions.assertThat(productResult.getTotalValue()).isEqualTo(PRICE_DISCOUNT);
    }

}

package com.demo.promotion.domain.promotions;


import com.demo.promotion.domain.CartProducts;
import com.demo.promotion.domain.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
public class SingleProductBundleTest {

    Product productA = new Product("A", 50.0);
    private List<Promotion> promotionList;

    @BeforeAll
    void setup() {
        promotionList = Arrays.asList(
                new SingleProductBundle(productA, 3, 130.0)
        );
    }

    @Test
    void whenBundleHasRightQuantity_applyBundle() {
        SingleProductBundle cut = new SingleProductBundle(productA, 3, 130.0);
        List<CartProducts> cartProducts = Arrays.asList(new CartProducts(productA, 3));

        Assertions.assertEquals(130.0, cut.apply(cartProducts));
    }

    @Test
    void whenBundleHasLessQuantity_bundleNotApplied() {
        SingleProductBundle cut = new SingleProductBundle(productA, 3, 130.0);
        List<CartProducts> cartProducts = Arrays.asList(new CartProducts(productA, 2));

        Assertions.assertEquals(100.0, cut.apply(cartProducts));
    }

    @Test
    void whenBundleHasMoreQuantity_bundleMultiple() {
        SingleProductBundle cut = new SingleProductBundle(productA, 3, 130.0);
        List<CartProducts> cartProducts = Arrays.asList(new CartProducts(productA, 6));

        Assertions.assertEquals(260.0, cut.apply(cartProducts));
    }

    @Test
    void whenBundleHasMoreQuantity_bundleMultipleWithRemaining() {
        SingleProductBundle cut = new SingleProductBundle(productA, 3, 130.0);
        List<CartProducts> cartProducts = Arrays.asList(new CartProducts(productA, 8));

        Assertions.assertEquals(360.0, cut.apply(cartProducts));
    }

}

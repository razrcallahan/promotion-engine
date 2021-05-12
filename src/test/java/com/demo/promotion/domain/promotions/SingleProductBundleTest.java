package com.demo.promotion.domain.promotions;


import com.demo.promotion.domain.CartProduct;
import com.demo.promotion.domain.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
public class SingleProductBundleTest {

    Product productA = new Product("A", 50.0);
    Product productB = new Product("B", 30.0);

    @Test
    void whenCartHasRightQuantity_applyBundle() {
        SingleProductBundle cut = new SingleProductBundle(productA, 3, 130.0);
        List<CartProduct> cartProducts = Arrays.asList(new CartProduct(productA, 3));

        Assertions.assertEquals(130.0, cut.apply(cartProducts));
    }

    @Test
    void whenCartHasLessQuantity_bundleNotApplied() {
        SingleProductBundle cut = new SingleProductBundle(productA, 3, 130.0);
        List<CartProduct> cartProducts = Arrays.asList(new CartProduct(productA, 2));

        Assertions.assertEquals(100.0, cut.apply(cartProducts));
    }

    @Test
    void whenCartHasMoreQuantity_bundleMultiple() {
        SingleProductBundle cut = new SingleProductBundle(productA, 3, 130.0);
        List<CartProduct> cartProducts = Arrays.asList(new CartProduct(productA, 6));

        Assertions.assertEquals(260.0, cut.apply(cartProducts));
    }

    @Test
    void whenCartHasMoreQuantity_bundleMultipleWithRemaining() {
        SingleProductBundle cut = new SingleProductBundle(productA, 3, 130.0);
        List<CartProduct> cartProducts = Arrays.asList(new CartProduct(productA, 8));

        Assertions.assertEquals(360.0, cut.apply(cartProducts));
    }


    @Test
    void whenCartHasDifferentProduct_bundleNotApplied() {
        SingleProductBundle cut = new SingleProductBundle(productA, 3, 130.0);
        List<CartProduct> cartProducts = Arrays.asList(new CartProduct(productB, 3));

        Assertions.assertEquals(0.0, cut.apply(cartProducts));
    }
}

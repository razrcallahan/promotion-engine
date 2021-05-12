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
public class MultiProductBundleTest {

    Product productA = new Product("A", 50.0);
    Product productB = new Product("B", 30.0);
    Product productC = new Product("C", 20.0);
    Product productD = new Product("D", 15.0);


    @Test
    void whenAllProductsAvailableInCart_applyBundle() {
        MultiProductBundles cut = new MultiProductBundles(Arrays.asList(productC, productD), 30.0);
        List<CartProduct> cartProducts = Arrays.asList(
                new CartProduct(productC, 3),
                new CartProduct(productD, 3)
        );

        Assertions.assertEquals(90.0, cut.apply(cartProducts));
    }

    @Test
    void whenAllProductsAvailableInCart_applyBundlePartiallyBasedOnQuantity() {
        MultiProductBundles cut = new MultiProductBundles(Arrays.asList(productC, productD), 30.0);
        List<CartProduct> cartProducts = Arrays.asList(
                new CartProduct(productC, 3),
                new CartProduct(productD, 2)
        );

        Assertions.assertEquals(80.0, cut.apply(cartProducts));
    }

    @Test
    void whenAllProductsNotAvailableInCart_bundleNotApplied() {
        MultiProductBundles cut = new MultiProductBundles(Arrays.asList(productC, productD), 30.0);
        List<CartProduct> cartProducts = Arrays.asList(
                new CartProduct(productC, 3)
        );

        Assertions.assertEquals(60.0, cut.apply(cartProducts));
    }

    @Test
    void whenAllProductsWithExtraProductsAvailableInCart_bundleAppliedToCorrectProducts() {
        MultiProductBundles cut = new MultiProductBundles(Arrays.asList(productC, productD), 30.0);
        List<CartProduct> cartProducts = Arrays.asList(
                new CartProduct(productA, 1),
                new CartProduct(productB, 1),
                new CartProduct(productC, 3),
                new CartProduct(productD, 3)
        );

        Assertions.assertEquals(170.0, cut.apply(cartProducts));
    }
}

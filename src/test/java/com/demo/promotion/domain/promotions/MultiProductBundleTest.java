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

    Product productC = new Product("B", 20.0);
    Product productD = new Product("C", 15.0);


    @Test
    void whenAllProductsAvailableInCart_applyBundle() {
        MultiProductBundles cut = new MultiProductBundles(Arrays.asList(productC, productD), 30.0);
        List<CartProduct> cartProducts = Arrays.asList(
                new CartProduct(productC, 3),
                new CartProduct(productD, 2)
        );

        Assertions.assertEquals(80.0, cut.apply(cartProducts));
    }

}

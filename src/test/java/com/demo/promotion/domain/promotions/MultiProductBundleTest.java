package com.demo.promotion.domain.promotions;


import com.demo.promotion.domain.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
public class MultiProductBundleTest {

    Product productC = new Product("B", 50.0);
    Product productD = new Product("C", 50.0);


    @Test
    void whenAllProductsAvailableInCart_applyBundle() {

    }

}

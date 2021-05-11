package com.demo.promotion.services;

import com.demo.promotion.domain.Product;
import com.demo.promotion.domain.promotions.MultiProductBundles;
import com.demo.promotion.domain.promotions.Promotion;
import com.demo.promotion.domain.promotions.SingleProductBundle;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class PromotionServiceTest {

    @Autowired
    PromotionService promotionService;

    private List<Promotion> promotionList;

    @BeforeAll
    void setup() {
        List<Product> productList = new ArrayList<>();

        Product productA = new Product("A", 50.0);
        Product productB = new Product("B", 30.0);
        Product productC = new Product("C", 20.0);
        Product productD = new Product("D", 15.0);

        productList.add(productA);
        productList.add(productB);
        productList.add(productC);
        productList.add(productD);

        promotionList = Arrays.asList(
                new SingleProductBundle(productA, 3, 130.0),
                new SingleProductBundle(productB, 2, 45.0),
                new MultiProductBundles(Arrays.asList(productC, productD), 30.0)
        );
    }

    @Test
    public void whenAppliedPromotion_CartPriceIsUpdated() {

    }
}

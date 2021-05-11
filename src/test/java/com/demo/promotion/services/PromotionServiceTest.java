package com.demo.promotion.services;

import com.demo.promotion.domain.Cart;
import com.demo.promotion.domain.CartProducts;
import com.demo.promotion.domain.Product;
import com.demo.promotion.domain.promotions.MultiProductBundles;
import com.demo.promotion.domain.promotions.Promotion;
import com.demo.promotion.domain.promotions.SingleProductBundle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
public class PromotionServiceTest {

    @Autowired
    PromotionService promotionService;

    private List<Promotion> promotionList;

    Product productA = new Product("A", 50.0);
    Product productB = new Product("B", 30.0);
    Product productC = new Product("C", 20.0);
    Product productD = new Product("D", 15.0);

    @BeforeAll
    void setup() {
        promotionList = Arrays.asList(
                new SingleProductBundle(productA, 3, 130.0),
                new SingleProductBundle(productB, 2, 45.0),
                new MultiProductBundles(Arrays.asList(productC, productD), 30.0)
        );
    }

    @Test
    public void whenAppliedPromotion_CartPriceIsUpdated() {
        List<CartProducts> cartProducts = Arrays.asList(new CartProducts(productA, 3));
        Cart cart = new Cart(cartProducts);
        cart.setNetPrice(productA.getPrice() * 3); //150

        promotionService.applyPromotions(cart, promotionList);

        Assertions.assertEquals(cart.getNetPrice(), 130.0);
    }
}

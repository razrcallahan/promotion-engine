package com.demo.promotion.services;

import com.demo.promotion.domain.Cart;
import com.demo.promotion.domain.CartProduct;
import com.demo.promotion.domain.Product;
import com.demo.promotion.domain.promotions.MultiProductBundles;
import com.demo.promotion.domain.promotions.Promotion;
import com.demo.promotion.domain.promotions.SingleProductBundle;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
public class PromotionServiceTest {

    @Autowired
    PromotionService promotionService;

    private List<Promotion> promotionList;

    Product productA;
    Product productB;
    Product productC;
    Product productD;

    @BeforeAll
    void setup() {
        productA = new Product("A", 50.0);
        productB = new Product("B", 30.0);
        productC = new Product("C", 20.0);
        productD = new Product("D", 15.0);

        promotionList = Arrays.asList(
                new SingleProductBundle(productA, 3, 130.0),
                new SingleProductBundle(productB, 2, 45.0),
                new MultiProductBundles(Arrays.asList(productC, productD), 30.0)
        );
    }

    @Test
    public void whenAppliedPromotion_CartPriceIsUpdated() {
        List<CartProduct> cartProducts = Collections.singletonList(new CartProduct(productA, 3));
        Cart cart = new Cart(cartProducts);
        cart.setNetPrice(productA.getPrice() * 3); //150

        promotionService.applyPromotions(cart, promotionList);

        Assertions.assertNotEquals( 150.0, cart.getNetPrice());
    }

    @Test
    public void whenAppliedSingleProductPromotion_CarPriceIsUpdated() {
        List<CartProduct> cartProducts = Collections.singletonList(new CartProduct(productA, 3));
        Cart cart = new Cart(cartProducts);
        cart.setNetPrice(productA.getPrice() * 3); //150

        promotionService.applyPromotions(cart, promotionList);

        Assertions.assertEquals(130.0, cart.getNetPrice());
    }

    @Test
    public void whenAppliedMultiProductPromotion_CarPriceIsUpdated() {
        List<CartProduct> cartProducts = Arrays.asList(
                new CartProduct(productC, 1),
                new CartProduct(productD, 1)
        );

        Cart cart = new Cart(cartProducts);
        cart.setNetPrice(productC.getPrice() + productD.getPrice()); //35

        promotionService.applyPromotions(cart, promotionList);

        Assertions.assertEquals(30.0, cart.getNetPrice());
    }

    @Test
    public void whenSingleBundlePromotionIsFirst_OnlySingleBundleIsApplied() {
        List<Promotion> updatedPromotionList =  Arrays.asList(
                new SingleProductBundle(productA, 3, 130.0),
                new MultiProductBundles(Arrays.asList(productA, productC), 60.0)
        );

        List<CartProduct> cartProducts = Arrays.asList(
                new CartProduct(productA, 3),
                new CartProduct(productC, 1)
        );

        Cart cart = new Cart(cartProducts);
        cart.setNetPrice(170.0); //170

        promotionService.applyPromotions(cart, updatedPromotionList);

        Assertions.assertEquals(150.0, cart.getNetPrice());
    }

    @Test
    public void whenMultiBundlePromotionIsFirst_OnlyMultiBundleIsApplied() {
        List<Promotion> updatedPromotionList = Arrays.asList(
                new MultiProductBundles(Arrays.asList(productA, productC), 60.0),
                new SingleProductBundle(productA, 3, 130.0)
        );

        List<CartProduct> cartProducts = Arrays.asList(
                new CartProduct(productA, 3),
                new CartProduct(productC, 1)
        );

        Cart cart = new Cart(cartProducts);
        cart.setNetPrice(170.0); //170

        promotionService.applyPromotions(cart, updatedPromotionList);

        Assertions.assertEquals(160.0, cart.getNetPrice());
    }

    /**
     * Tests specified in the requirement document
     */

    @Test
    public void scenarioA() {
        List<CartProduct> cartProducts = Arrays.asList(
                new CartProduct(productA, 1),
                new CartProduct(productB, 1),
                new CartProduct(productC, 1)
        );

        Cart cart = new Cart(cartProducts);
        cart.setNetPrice(50.0 + 30.0 + 20.0); //100

        promotionService.applyPromotions(cart, promotionList);

        Assertions.assertEquals(100.0, cart.getNetPrice());
    }

    @Test
    public void scenarioB() {

        List<CartProduct> cartProducts = Arrays.asList(
                new CartProduct(productA, 5),
                new CartProduct(productB, 5),
                new CartProduct(productC, 1)
        );

        Cart cart = new Cart(cartProducts);
        cart.setNetPrice(250.0 + 225.0 + 20); //503

        promotionService.applyPromotions(cart, promotionList);


        Assertions.assertEquals(370.0, cart.getNetPrice());
    }

    @Test
    public void scenarioC() {
        List<CartProduct> cartProducts = Arrays.asList(
                new CartProduct(productA, 3),
                new CartProduct(productB, 5),
                new CartProduct(productC, 1),
                new CartProduct(productD, 1)
        );

        Cart cart = new Cart(cartProducts);
        cart.setNetPrice(150.0 + 225.0 + 0.0 + 30.0); //405

        promotionService.applyPromotions(cart, promotionList);

        // there is a calculation error in the document, the total has been calculated incorrect
        Assertions.assertEquals(280.0, cart.getNetPrice());
    }
}

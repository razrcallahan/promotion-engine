package com.demo.promotion.services;

import com.demo.promotion.domain.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CheckoutService {

    @Autowired
    PromotionService promotionService;

    private void checkout(Cart cart) {

        // .. applying promotions
        promotionService.applyPromotion(cart);

        // .. shipping
        // .. payment
    }
}

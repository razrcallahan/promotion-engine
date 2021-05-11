package com.demo.promotion.services;

import com.demo.promotion.domain.Cart;
import com.demo.promotion.domain.promotions.Promotion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CheckoutService {

    @Autowired
    PromotionService promotionService;

    private void checkout(Cart cart) {

        // .. applying promotions
        promotionService.applyPromotions(cart, new ArrayList<Promotion>());

        // .. shipping
        // .. payment
    }
}

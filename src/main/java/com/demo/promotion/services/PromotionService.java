package com.demo.promotion.services;

import com.demo.promotion.domain.Cart;
import com.demo.promotion.domain.promotions.Promotion;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromotionService {

    /**
     * Applies supplied promotion to supplied cart
     *
     * @param cart Cart object
     * @param applicablePromotions List of promotions that are application/active
     */
    public void applyPromotions(Cart cart, List<Promotion> applicablePromotions) {
        cart.setNetPrice(applicablePromotions.stream()
                .mapToDouble(p -> p.apply(cart.getProductList()))
                .sum());
    }
}

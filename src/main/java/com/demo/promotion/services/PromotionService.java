package com.demo.promotion.services;

import com.demo.promotion.domain.Cart;
import com.demo.promotion.domain.promotions.Promotion;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromotionService {
    public void applyPromotions(Cart cart, List<Promotion> applicablePromotions) {
        cart.setNetPrice(applicablePromotions.stream().mapToDouble(Promotion::apply).sum());
    }
}

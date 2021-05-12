package com.demo.promotion.domain.promotions;

import com.demo.promotion.domain.CartProduct;

import java.util.List;

/**
 * Generic promotion interface
 * Implement the interface to create more promotion types
 */
public interface Promotion {
    Double apply(List<CartProduct> cartProducts);
}

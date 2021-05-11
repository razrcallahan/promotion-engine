package com.demo.promotion.domain.promotions;

import com.demo.promotion.domain.CartProducts;

import java.util.List;

public interface Promotion {
    Double apply(List<CartProducts> cartProducts);
}

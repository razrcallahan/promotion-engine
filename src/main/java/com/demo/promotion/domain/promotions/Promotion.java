package com.demo.promotion.domain.promotions;

import com.demo.promotion.domain.CartProduct;

import java.util.List;

public interface Promotion {
    Double apply(List<CartProduct> cartProducts);
}

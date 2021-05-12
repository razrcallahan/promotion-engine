package com.demo.promotion.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class Cart {
    private final List<CartProduct> productList;
    private Double netPrice;
}

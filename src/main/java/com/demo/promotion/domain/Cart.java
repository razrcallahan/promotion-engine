package com.demo.promotion.domain;

import lombok.Data;

import java.util.List;

@Data
public class Cart {
    private final List<CartProducts> productList;
    private Double netPrice;
}

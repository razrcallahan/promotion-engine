package com.demo.promotion.domain;

import lombok.Data;

import java.util.List;

@Data
public class Cart {
    private final List<Product> productList;
    private Double netPrice;
}

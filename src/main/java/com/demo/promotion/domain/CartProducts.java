package com.demo.promotion.domain;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CartProducts {
    private Product product;
    private Integer quantity;
}

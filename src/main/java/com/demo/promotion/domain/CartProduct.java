package com.demo.promotion.domain;


import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CartProduct {

    @NonNull
    private Product product;

    @NonNull
    private Integer quantity;

    private Boolean promotionApplied = Boolean.FALSE;
}

package com.demo.promotion.domain;


import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@Data
@RequiredArgsConstructor
public class CartProducts {

    @NonNull
    private Product product;

    @NonNull
    private Integer quantity;

    private Boolean promotionApplied = Boolean.FALSE;
}

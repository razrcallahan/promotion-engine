package com.demo.promotion.domain;


import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@RequiredArgsConstructor
public class CartProduct {

    @NonNull
    private Product product;

    @NonNull
    private Integer quantity;

    private Boolean promotionApplied = Boolean.FALSE;
}

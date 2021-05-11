package com.demo.promotion.domain.promotions;

import com.demo.promotion.domain.Product;
import lombok.Data;

@Data
public class SingleProductBundle implements Promotion {

    Float price;
    Product product;
    Integer bundleQuantity;

    public SingleProductBundle(Product product, Integer bundleQuantity, Float price) {
        this.product = product;
        this.bundleQuantity = bundleQuantity;
        this.price = price;
    }

    @Override
    public Double apply() {
        return 0.0;
    }
}

package com.demo.promotion.domain.promotions;

import com.demo.promotion.domain.CartProducts;
import com.demo.promotion.domain.Product;

import java.util.List;

public class MultiProductBundles implements Promotion {

    List<Product> productList;
    Double price;

    public MultiProductBundles(List<Product> productList, Double price) {
        this.productList = productList;
        this.price = price;
    }

    @Override
    public Double apply(List<CartProducts> cartProducts) {
        return productList.stream()
                .mapToDouble(p -> {
                    return p.getPrice();
                }).sum();
    }
}

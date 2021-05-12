package com.demo.promotion.domain.promotions;

import com.demo.promotion.domain.CartProduct;
import com.demo.promotion.domain.Product;
import lombok.Data;

import java.util.*;
import java.util.stream.Collectors;

@Data
public class MultiProductBundles implements Promotion {

    List<Product> productList;
    Double price;

    public MultiProductBundles(List<Product> productList, Double price) {
        this.productList = productList;
        this.price = price;
    }

    @Override
    public Double apply(List<CartProduct> cartProducts) {
        Integer numberOfBundles = 0;
        List<CartProduct> prod = cartProducts.stream()
                .filter(cp -> productList.contains(cp.getProduct()))
                .sorted(Comparator.comparing(CartProduct::getQuantity))
                .collect(Collectors.toList());

        if (prod.size() == productList.size()) {
            numberOfBundles = prod.get(0).getQuantity();
        }

        Integer finalNumberOfBundles = numberOfBundles;
        double nonBundledPrice = cartProducts.stream()
                .mapToDouble(cp -> {
                    int remainingQuantity = cp.getQuantity() - finalNumberOfBundles;
                    return remainingQuantity * cp.getProduct().getPrice();
                })
                .sum();

        return (numberOfBundles * this.price) + nonBundledPrice;
    }
}

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

        // filter and sort cart products based on the bundle products
        List<CartProduct> prod = cartProducts.stream()
                .filter(cp -> cp.getPromotionApplied() == Boolean.FALSE)
                .filter(cp -> productList.contains(cp.getProduct()))
                .sorted(Comparator.comparing(CartProduct::getQuantity))
                .collect(Collectors.toList());

        // apply bundle only if all products are available
        if (prod.size() == productList.size()) {
            numberOfBundles = prod.get(0).getQuantity();
        }

        Integer finalNumberOfBundles = numberOfBundles;

        // calculating prices for products not bundled
        double nonBundledPrice = cartProducts.stream()
                .mapToDouble(cp -> {
                    int remainingQuantity = cp.getQuantity() - finalNumberOfBundles;

                    // remaining quantity is negative for products not available in bundle
                    if(remainingQuantity >= 0) {
                        cp.setPromotionApplied(Boolean.TRUE);
                        return remainingQuantity * cp.getProduct().getPrice();
                    }
                    return cp.getQuantity() * cp.getProduct().getPrice();
                })
                .sum();

        // sum prices of all bundles and non bundled products
        return (numberOfBundles * this.price) + nonBundledPrice;
    }
}

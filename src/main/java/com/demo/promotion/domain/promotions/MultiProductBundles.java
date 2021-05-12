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

    /**
     * Constructor to create a multi product bundle promotion
     * @param productList List of products included in the bundle
     * @param price price of the whole bundle
     */
    public MultiProductBundles(List<Product> productList, Double price) {
        this.productList = productList;
        this.price = price;
    }

    /**
     * Implementation of the promotion for multi product bundles
     * @param cartProducts list of cartProducts
     * @return total sum after applying the promotion
     */
    @Override
    public Double apply(List<CartProduct> cartProducts) {
        Integer numberOfBundles = 0;

        // filter and sort cart products based on the bundle products
        List<CartProduct> prod = filterList(cartProducts);

        // apply bundle only if all products are available
        if (prod.size() == productList.size()) {
            numberOfBundles = prod.get(0).getQuantity();
        }

        // calculating prices for products not bundled
        Double nonBundledPrice = getNonBundledPrices(cartProducts, numberOfBundles);

        // sum prices of all bundles and non bundled products
        return (numberOfBundles * this.price) + nonBundledPrice;
    }

    /**
     * Filters list for products that are part of the bundle and where promotion is not already applied
     * Descending Sorts the list based on quantity in the cart
     * @param cartProducts list of cartProducts
     * @return Filtered and sorted list of cart products
     */
    private List<CartProduct> filterList(List<CartProduct> cartProducts) {
        return cartProducts.stream()
                .filter(cp -> cp.getPromotionApplied() == Boolean.FALSE)
                .filter(cp -> productList.contains(cp.getProduct()))
                .sorted(Comparator.comparing(CartProduct::getQuantity))
                .collect(Collectors.toList());
    }

    /**
     * Calculates sum of prices of all the products not part of the bundles.
     *
     * @param cartProducts list of cartProducts
     * @param numberOfBundles number of bundles created
     * @return returns sum of prices for all products that were not part of the bundle
     */
    private Double getNonBundledPrices(List<CartProduct> cartProducts, Integer numberOfBundles) {
        return cartProducts.stream()
                .filter(cp -> cp.getPromotionApplied() == Boolean.FALSE)
                .mapToDouble(cp -> {
                    int remainingQuantity = cp.getQuantity() - numberOfBundles;

                    // remaining quantity is negative for products not available in bundle
                    if (remainingQuantity >= 0) {
                        cp.setPromotionApplied(Boolean.TRUE);
                        return remainingQuantity * cp.getProduct().getPrice();
                    }
                    return cp.getQuantity() * cp.getProduct().getPrice();
                })
                .sum();
    }
}

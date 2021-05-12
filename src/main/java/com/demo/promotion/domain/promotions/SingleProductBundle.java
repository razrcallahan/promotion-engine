package com.demo.promotion.domain.promotions;

import com.demo.promotion.domain.CartProduct;
import com.demo.promotion.domain.Product;
import lombok.Data;

import java.util.List;

@Data
public class SingleProductBundle implements Promotion {

    Double price;
    Product product;
    Integer bundleQuantity;

    /**
     * Constructor to create a single product bundle promotion
     * @param product product object
     * @param bundleQuantity minimum number of products needed to make a bundle
     * @param price price for the bundle
     */
    public SingleProductBundle(Product product, Integer bundleQuantity, Double price) {
        this.product = product;
        this.bundleQuantity = bundleQuantity;
        this.price = price;
    }

    /**
     * Implementation of the promotion for single product bundles
     *
     * @param cartProducts list of cartProducts
     * @return total sum after applying the promotion
     */
    @Override
    public Double apply(List<CartProduct> cartProducts) {
        // filter products that are available in bundle
        // filter products that hasn't been included in another promotion already
        return cartProducts.stream()
                .filter(p -> p.getProduct().equals(product))
                .filter(p -> !p.getPromotionApplied())
                .mapToDouble(p -> {
                    // get absolute bundle count
                    int totalBundles = (int) p.getQuantity() / bundleQuantity;

                    // get products left out of the bundle
                    int remaining = p.getQuantity() - (totalBundles * bundleQuantity);

                    // setting promotion applied to true
                    p.setPromotionApplied(Boolean.TRUE);

                    // sum of total bundle prices and products not included in the bundle
                    return  (totalBundles * this.getPrice()) + (remaining * product.getPrice());
                })
                .sum();
    }
}

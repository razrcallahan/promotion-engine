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
     * @param cartProducts cart product object
     * @return total sum after applying the promotion
     */
    @Override
    public Double apply(List<CartProduct> cartProducts) {
        return cartProducts.stream()
                .filter(p -> p.getProduct().equals(product))
                .filter(p -> !p.getPromotionApplied())
                .mapToDouble(p -> {
                    int totalBundles = (int) p.getQuantity() / bundleQuantity;
                    int remaining = p.getQuantity() - (totalBundles * bundleQuantity);

                    p.setPromotionApplied(Boolean.TRUE);

                    return  (totalBundles * this.getPrice()) + (remaining * product.getPrice());
                })
                .sum();
    }
}

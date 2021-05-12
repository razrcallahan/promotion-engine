package com.demo.promotion.domain.promotions;

import com.demo.promotion.domain.CartProducts;
import com.demo.promotion.domain.Product;
import lombok.Data;

import java.util.List;

@Data
public class SingleProductBundle implements Promotion {

    Double price;
    Product product;
    Integer bundleQuantity;

    public SingleProductBundle(Product product, Integer bundleQuantity, Double price) {
        this.product = product;
        this.bundleQuantity = bundleQuantity;
        this.price = price;
    }

    /**
     *
     * @param cartProducts
     * @return total sum
     */
    @Override
    public Double apply(List<CartProducts> cartProducts) {
        return cartProducts.stream()
                .filter(p -> p.getProduct().equals(product))
                .filter(p -> !p.getPromotionApplied())
                .mapToDouble(p -> {
                    int totalBundles = (int) p.getQuantity() / bundleQuantity;
                    int remaining = p.getQuantity() - (totalBundles * bundleQuantity);

                    return  (totalBundles * this.getPrice()) + (remaining * product.getPrice());
                })
                .sum();
    }
}

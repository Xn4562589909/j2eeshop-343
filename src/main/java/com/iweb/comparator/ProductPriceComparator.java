package com.iweb.comparator;

import com.iweb.entity.Product;

import java.util.Comparator;

/**
 * @author Yang
 * @date 2023/4/12 0:49
 */
public class ProductPriceComparator implements Comparator<Product> {
    @Override
    public int compare(Product o1, Product o2) {
        return o1.getPromotePrice().compareTo(o2.getPromotePrice());
    }
}

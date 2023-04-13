package com.iweb.comparator;

import com.iweb.entity.Product;

import java.util.Comparator;

/**
 * @author Yang
 * @date 2023/4/12 0:57
 */
public class ProductReviewComparator implements Comparator<Product> {
    @Override
    public int compare(Product o1, Product o2) {
        if (o1.getReviewCount()>o2.getReviewCount()){
            return -1;
        }else if (o1.getReviewCount()==o2.getReviewCount()){
            return 0;
        }else {
            return 1;
        }
    }
}

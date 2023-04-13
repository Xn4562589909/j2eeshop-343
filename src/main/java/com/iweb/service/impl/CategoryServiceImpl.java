package com.iweb.service.impl;

import com.iweb.DAO.*;
import com.iweb.DAO.impl.*;
import com.iweb.entity.Category;
import com.iweb.entity.Product;
import com.iweb.entity.ProductImage;
import com.iweb.service.CategoryService;

import java.util.List;

/**
 * @author Yang
 * @date 2023/4/3 17:36
 */
public class CategoryServiceImpl implements CategoryService {
    CategoryDAO categoryDAO = new CategoryDAOImpl();
    ProductDAO productDAO = new ProductDAOImpl();
    ProductImageDAO productImageDAO = new ProductImageDAOImpl();
    ReviewDAO reviewDAO = new ReviewDAOImpl();
    OrderItemDAO orderItemDAO = new OrderItemDAOImpl();
    @Override
    public List<Category> list() {
        List<Category> list = categoryDAO.list();
        for (Category c:list) {
            List<Product> products = productDAO.list(c.getId());
            for (Product p:products) {
                List<ProductImage> pis = productImageDAO.list(p.getId());
                int sale = orderItemDAO.getSaleCount(p.getId());
                int reviewCount = reviewDAO.getTotal(p.getId());
                p.setImages(pis);
                p.setSaleCount(sale);
                p.setReviewCount(reviewCount);
            }
            c.setProducts(products);
        }
        // 调用DAO方法获取数据
        return list;
    }

    @Override
    public void add(Category category) {
        categoryDAO.add(category);
    }

    @Override
    public Category edit(int id) {
        return  categoryDAO.get(id);
    }

    @Override
    public void update(Category category) {
        categoryDAO.update(category);
    }

    @Override
    public void delete(int id) {
        List<Product> productList = productDAO.list(id);
        for (Product p:productList) {
            productDAO.delete(p.getId());
        }
        categoryDAO.delete(id);
    }

    @Override
    public Category get(int id) {
        Category category = categoryDAO.get(id);
        List<Product> products = productDAO.list(id);
        for (Product p:products) {
            List<ProductImage> pis = productImageDAO.list(p.getId());
            int reviewCount = reviewDAO.getTotal(p.getId());
            int saleCount = orderItemDAO.getSaleCount(p.getId());
            p.setSaleCount(saleCount);
            p.setReviewCount(reviewCount);
            p.setImages(pis);
        }
        category.setProducts(products);
        return category;
    }
}

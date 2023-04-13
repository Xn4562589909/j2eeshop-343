package com.iweb.service.impl;

import com.iweb.DAO.OrderItemDAO;
import com.iweb.DAO.ProductDAO;
import com.iweb.DAO.ProductImageDAO;
import com.iweb.DAO.ReviewDAO;
import com.iweb.DAO.impl.OrderItemDAOImpl;
import com.iweb.DAO.impl.ProductDAOImpl;
import com.iweb.DAO.impl.ProductImageDAOImpl;
import com.iweb.DAO.impl.ReviewDAOImpl;
import com.iweb.entity.Product;
import com.iweb.entity.ProductImage;
import com.iweb.service.ProductService;

import java.util.List;

/**
 * @author Yang
 * @date 2023/4/3 23:32
 */
public class ProductServiceImpl implements ProductService {
    ProductDAO productDAO = new ProductDAOImpl();
    ProductImageDAO productImageDAO = new ProductImageDAOImpl();
    OrderItemDAO orderItemDAO = new OrderItemDAOImpl();
    ReviewDAO reviewDAO = new ReviewDAOImpl();
    @Override
    public List<Product> list(int cid) {
        List<Product> list = productDAO.list(cid);
        for (Product p:list) {
            List<ProductImage> pis = productImageDAO.list(p.getId());
            p.setImages(pis);
        }
        return list;
    }

    @Override
    public void add(Product product) {
        productDAO.add(product);
    }

    @Override
    public void delete(int id) {
        productDAO.delete(id);
    }

    @Override
    public Product get(int id) {
        Product product = productDAO.get(id);
        List<ProductImage> productImages = productImageDAO.list(product.getId());
        int saleCount = orderItemDAO.getSaleCount(product.getId());
        product.setSaleCount(saleCount);
        product.setImages(productImages);
        return product;
    }

    @Override
    public void update(Product product) {
        productDAO.update(product);
    }

    @Override
    public List<Product> list() {
        return productDAO.list();
    }

    @Override
    public List<Product> list(String name) {
        List<Product> products = productDAO.list(name);
        for (Product product:products) {
            int sale = orderItemDAO.getSaleCount(product.getId());
            int reviewCount = reviewDAO.getTotal(product.getId());
            List<ProductImage> pis = productImageDAO.list(product.getId());
            product.setImages(pis);
            product.setSaleCount(sale);
            product.setReviewCount(reviewCount);
        }
        return products;
    }
}

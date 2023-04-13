package com.iweb.service.impl;

import com.iweb.DAO.ProductImageDAO;
import com.iweb.DAO.impl.ProductImageDAOImpl;
import com.iweb.entity.ProductImage;
import com.iweb.service.ProductImageService;

import java.util.List;

/**
 * @author Yang
 * @date 2023/4/7 19:43
 */
public class ProductImageServiceImpl implements ProductImageService {
    ProductImageDAO productImageDAO = new ProductImageDAOImpl();
    @Override
    public List<ProductImage> list(int pid) {
        return productImageDAO.list(pid);
    }

    @Override
    public void add(ProductImage pi) {
        productImageDAO.add(pi);
    }

    @Override
    public void delete(int id) {
        productImageDAO.delete(id);
    }
}

package com.iweb.service.impl;

import com.iweb.entity.Product;
import com.iweb.entity.ProductImage;
import com.iweb.entity.PropertyValue;
import com.iweb.mapper.*;
import com.iweb.service.ProductService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author Yang
 * @date 2023/4/3 23:32
 */
public class ProductServiceImpl implements ProductService {
    // 定义配置文件路径

    String resource = "mybatis-config.xml";
    InputStream inputStream;
    SqlSessionFactory sqlSessionFactory;
    SqlSession session;
    ProductMapper productMapper;
    ProductImageMapper productImageMapper;
    OrderItemMapper orderItemMapper;
    ReviewMapper reviewMapper;
    PropertyValueMapper propertyValueMapper;

    public void init() throws IOException {
        // 建立输入流读取配置文件
        inputStream = Resources.getResourceAsStream(resource);
        // 实例化mybatis一级缓存
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        // 基于一级缓存实例化二级缓存
        session = sqlSessionFactory.openSession();
        productMapper = session.getMapper(ProductMapper.class);
        productImageMapper = session.getMapper(ProductImageMapper.class);
        orderItemMapper = session.getMapper(OrderItemMapper.class);
        reviewMapper = session.getMapper(ReviewMapper.class);
        propertyValueMapper = session.getMapper(PropertyValueMapper.class);
    }


    @Override
    public List<Product> list(int cid) {
        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Product> list = productMapper.listByCid(cid);
        for (Product p:list) {
            List<ProductImage> pis = productImageMapper.listByPid(p.getId());
            p.setImages(pis);
        }
        return list;
    }

    @Override
    public void add(Product product) {
        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }
        productMapper.add(product);
        session.commit();
    }

    @Override
    public void delete(int id) {
        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<ProductImage> pis = productImageMapper.listByPid(id);
        for (ProductImage pi:pis) {
            productImageMapper.delete(pi.getId());
        }
        List<PropertyValue> pvs = propertyValueMapper.listByPid(id);
        for (PropertyValue pv:pvs) {
            propertyValueMapper.delete(pv.getId());
        }
        productMapper.delete(id);
        session.commit();
    }

    @Override
    public Product get(int id) {
        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Product product = productMapper.get(id);
        List<ProductImage> productImages = productImageMapper.listByPid(product.getId());
        int saleCount;
        if (null == orderItemMapper.getSaleCount(product.getId())){
            saleCount = 0;
        }else {
            saleCount = orderItemMapper.getSaleCount(product.getId());
        }
        product.setSaleCount(saleCount);
        product.setImages(productImages);
        return product;
    }

    @Override
    public void update(Product product) {
        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }
        productMapper.update(product);
        session.commit();
    }

    @Override
    public List<Product> list() {
        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Product> list = productMapper.list();
        return list;
    }

    @Override
    public List<Product> list(String name) {
        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Product> products = productMapper.listByName(name);
        for (Product product:products) {
//            int sale = orderItemMapper.getSaleCount(product.getId());
//            int reviewCount = reviewMapper.getTotalByPid(product.getId());
            int reviewCount;
            if (null==reviewMapper.getTotalByPid(product.getId())){
                reviewCount = 0;
            }else {
                reviewCount = reviewMapper.getTotalByPid(product.getId());
            }
            int saleCount;
            if (null==orderItemMapper.getSaleCount(product.getId())){
                saleCount = 0;
            }else {
                saleCount = orderItemMapper.getSaleCount(product.getId());
            }
            List<ProductImage> pis = productImageMapper.listByPid(product.getId());
            product.setImages(pis);
            product.setSaleCount(saleCount);
            product.setReviewCount(reviewCount);
        }
        return products;
    }
}

package com.iweb.service.impl;

import com.iweb.entity.*;
import com.iweb.mapper.*;
import com.iweb.service.CategoryService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author Yang
 * @date 2023/4/3 17:36
 */
public class CategoryServiceImpl implements CategoryService {

    // 定义配置文件路径

    String resource = "mybatis-config.xml";
    InputStream inputStream;
    SqlSessionFactory sqlSessionFactory;
    SqlSession session;
    CategoryMapper categoryMapper;
    ProductMapper productMapper;
    ProductImageMapper productImageMapper;
    OrderItemMapper orderItemMapper;
    ReviewMapper reviewMapper;
    PropertyValueMapper propertyValueMapper;
    PropertyMapper propertyMapper;

    public void init() throws IOException {
        // 建立输入流读取配置文件
        inputStream = Resources.getResourceAsStream(resource);
        // 实例化mybatis一级缓存
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        // 基于一级缓存实例化二级缓存
        session = sqlSessionFactory.openSession();
        categoryMapper = session.getMapper(CategoryMapper.class);
        productMapper = session.getMapper(ProductMapper.class);
        productImageMapper = session.getMapper(ProductImageMapper.class);
        orderItemMapper = session.getMapper(OrderItemMapper.class);
        reviewMapper = session.getMapper(ReviewMapper.class);
        propertyValueMapper = session.getMapper(PropertyValueMapper.class);
        propertyMapper = session.getMapper(PropertyMapper.class);
    }

    @Override
    public List<Category> list() {
        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Category> list = categoryMapper.list();
        for (Category c:list) {
            List<Product> products = productMapper.listByCid(c.getId());
            for (Product p:products) {
                List<ProductImage> pis = productImageMapper.listByPid(p.getId());
                int sale;
                if (null==orderItemMapper.getSaleCount(p.getId())){
                    sale = 0;
                }else {
                    sale = orderItemMapper.getSaleCount(p.getId());
                }
                int reviewCount = reviewMapper.getTotalByPid(p.getId());
                p.setImages(pis);
                p.setSaleCount(sale);
                p.setReviewCount(reviewCount);
            }
            c.setProducts(products);
        }
        return list;
    }

    @Override
    public void add(Category category) {
        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int flag = categoryMapper.add(category);
        if (flag == 1){
            session.commit();
        }
    }

    @Override
    public Category edit(int id) {
        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  categoryMapper.get(id);
    }

    @Override
    public void update(Category category) {
        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int flag = categoryMapper.update(category);
        if (flag == 1) {
            session.commit();
        }
    }

    @Override
    public void delete(int id) {
        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Product> productList = productMapper.listByCid(id);
        for (Product p:productList) {
            List<ProductImage> pis = productImageMapper.listByPid(p.getId());
            for (ProductImage pi:pis) {
                productImageMapper.delete(pi.getId());
            }
            productMapper.delete(p.getId());
        }
        List<Property> properties = propertyMapper.listByCid(id);
        for (Property pt:properties) {
            List<PropertyValue> pvs = propertyValueMapper.listByPtid(pt.getId());
            for (PropertyValue pv:pvs) {
                propertyValueMapper.delete(pv.getId());
            }
            propertyMapper.delete(pt.getId());
        }
        categoryMapper.delete(id);
        session.commit();
    }

    @Override
    public Category get(int id) {
        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Category category = categoryMapper.get(id);
        List<Product> products = productMapper.listByCid(id);
        for (Product p:products) {
            List<ProductImage> pis = productImageMapper.listByPid(p.getId());
            int reviewCount;
            if (null==reviewMapper.getTotalByPid(p.getId())){
                reviewCount = 0;
            }else {
                reviewCount = reviewMapper.getTotalByPid(p.getId());
            }
            int saleCount;
            if (null==orderItemMapper.getSaleCount(p.getId())){
                saleCount = 0;
            }else {
                saleCount = orderItemMapper.getSaleCount(p.getId());
            }
            p.setSaleCount(saleCount);
            p.setReviewCount(reviewCount);
            p.setImages(pis);
        }
        category.setProducts(products);
        return category;
    }
}

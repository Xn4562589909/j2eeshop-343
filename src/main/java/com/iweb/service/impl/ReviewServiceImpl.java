package com.iweb.service.impl;

import com.iweb.entity.ProductImage;
import com.iweb.entity.Review;
import com.iweb.mapper.ProductImageMapper;
import com.iweb.mapper.ProductMapper;
import com.iweb.mapper.ReviewMapper;
import com.iweb.mapper.UserMapper;
import com.iweb.service.ReviewService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author Yang
 * @date 2023/4/11 19:20
 */
public class ReviewServiceImpl implements ReviewService {
    String resource = "mybatis-config.xml";
    InputStream inputStream;
    SqlSessionFactory sqlSessionFactory;
    SqlSession session;
    ReviewMapper reviewMapper;
    ProductImageMapper productImageMapper;

    public void init() throws IOException {
        // 建立输入流读取配置文件
        inputStream = Resources.getResourceAsStream(resource);
        // 实例化mybatis一级缓存
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        // 基于一级缓存实例化二级缓存
        session = sqlSessionFactory.openSession();
        reviewMapper = session.getMapper(ReviewMapper.class);
        productImageMapper = session.getMapper(ProductImageMapper.class);
    }
    @Override
    public List<Review> list(int pid) {
        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reviewMapper.listByPid(pid);
    }

    @Override
    public void add(Review review) {
        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }
        reviewMapper.add(review);
        session.commit();
    }

    @Override
    public List<Review> listUserReviews(int uid) {
        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Review> reviews = reviewMapper.listByUid(uid);
        for (Review review:reviews) {
            List<ProductImage> pis = productImageMapper.listByPid(review.getProduct().getId());
            review.getProduct().setImages(pis);
        }
        return reviews;
    }

    @Override
    public Review get(int id) {
        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reviewMapper.get(id);
    }

    @Override
    public void delete(int id) {
        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }
        reviewMapper.delete(id);
        session.commit();
    }

    @Override
    public void update(Review review) {
        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }
        reviewMapper.update(review);
        session.commit();
    }
}

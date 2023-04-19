package com.iweb.service.impl;

import com.iweb.entity.ProductImage;
import com.iweb.mapper.ProductImageMapper;
import com.iweb.mapper.ProductMapper;
import com.iweb.service.ProductImageService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author Yang
 * @date 2023/4/7 19:43
 */
public class ProductImageServiceImpl implements ProductImageService {
    // 定义配置文件路径

    String resource = "mybatis-config.xml";
    InputStream inputStream;
    SqlSessionFactory sqlSessionFactory;
    SqlSession session;
    ProductImageMapper productImageMapper;

    public void init() throws IOException {
        // 建立输入流读取配置文件
        inputStream = Resources.getResourceAsStream(resource);
        // 实例化mybatis一级缓存
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        // 基于一级缓存实例化二级缓存
        session = sqlSessionFactory.openSession();
        productImageMapper = session.getMapper(ProductImageMapper.class);
    }

    @Override
    public List<ProductImage> list(int pid) {
        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return productImageMapper.listByPid(pid);
    }

    @Override
    public void add(ProductImage pi) {
        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }
        productImageMapper.add(pi);
        session.commit();
    }

    @Override
    public void delete(int id) {
        productImageMapper.delete(id);
        session.commit();
    }
}

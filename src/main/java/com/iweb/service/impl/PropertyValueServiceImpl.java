package com.iweb.service.impl;

import com.iweb.entity.Property;
import com.iweb.entity.PropertyValue;
import com.iweb.mapper.ProductMapper;
import com.iweb.mapper.PropertyMapper;
import com.iweb.mapper.PropertyValueMapper;
import com.iweb.service.PropertyValueService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Yang
 * @date 2023/4/4 2:28
 */
public class PropertyValueServiceImpl implements PropertyValueService {
    String resource = "mybatis-config.xml";
    InputStream inputStream;
    SqlSessionFactory sqlSessionFactory;
    SqlSession session;
    PropertyMapper propertyMapper;
    PropertyValueMapper propertyValueMapper;
    ProductMapper productMapper;

    public void init() throws IOException {
        // 建立输入流读取配置文件
        inputStream = Resources.getResourceAsStream(resource);
        // 实例化mybatis一级缓存
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        // 基于一级缓存实例化二级缓存
        session = sqlSessionFactory.openSession();
        propertyMapper = session.getMapper(PropertyMapper.class);
        propertyValueMapper = session.getMapper(PropertyValueMapper.class);
        productMapper = session.getMapper(ProductMapper.class);
    }
    @Override
    public List<PropertyValue> list(int pid) {
        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<PropertyValue> pvs = propertyValueMapper.listByPid(pid);
        for (PropertyValue pv:pvs) {
            pv.setProduct(productMapper.get(pv.getPid()));
            pv.setProperty(propertyMapper.get(pv.getPtid()));
        }
        return pvs;
    }

    @Override
    public PropertyValue get(int id) {
        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }
        PropertyValue pv = propertyValueMapper.get(id);
        pv.setProperty(propertyMapper.get(pv.getPtid()));
        pv.setProduct(productMapper.get(pv.getPid()));
        return pv;
    }

    @Override
    public void update(PropertyValue pv) {
        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }
        pv.setPid(pv.getProduct().getId());
        pv.setPtid(pv.getProperty().getId());
        propertyValueMapper.update(pv);
        session.commit();
    }

    @Override
    public void add(PropertyValue pv) {
        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }
        pv.setPid(pv.getProduct().getId());
        pv.setPtid(pv.getProperty().getId());
        propertyValueMapper.add(pv);
        session.commit();
    }

    @Override
    public List<Property> listNotAddPt(List<Property> properties, List<PropertyValue> propertyValues) {
        List<Property> properties1 = new ArrayList<>();
        for (PropertyValue pv:propertyValues) {
            properties1.add(pv.getProperty());
        }
        properties.removeAll(properties1);
        return properties;
    }

    @Override
    public void delete(int id) {
        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }
        propertyValueMapper.delete(id);
        session.commit();;
    }
}

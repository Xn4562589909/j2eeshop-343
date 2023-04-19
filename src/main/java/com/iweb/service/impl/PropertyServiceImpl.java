package com.iweb.service.impl;

import com.iweb.entity.Property;
import com.iweb.entity.PropertyValue;
import com.iweb.mapper.CategoryMapper;
import com.iweb.mapper.PropertyMapper;
import com.iweb.mapper.PropertyValueMapper;
import com.iweb.service.PropertyService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author Yang
 * @date 2023/4/3 21:41
 */
public class PropertyServiceImpl implements PropertyService {
    String resource = "mybatis-config.xml";
    InputStream inputStream;
    SqlSessionFactory sqlSessionFactory;
    SqlSession session;
    PropertyMapper propertyMapper;
    PropertyValueMapper propertyValueMapper;

    public void init() throws IOException {
        // 建立输入流读取配置文件
        inputStream = Resources.getResourceAsStream(resource);
        // 实例化mybatis一级缓存
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        // 基于一级缓存实例化二级缓存
        session = sqlSessionFactory.openSession();
        propertyMapper = session.getMapper(PropertyMapper.class);
        propertyValueMapper = session.getMapper(PropertyValueMapper.class);
    }
    @Override
    public List<Property> list(int cid) {
        //调用DAO方法获取数据
        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Property> list = propertyMapper.listByCid(cid);
        return list;
    }

    @Override
    public void add(Property property) {
        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }
        propertyMapper.add(property);
        session.commit();
    }

    @Override
    public Property edit(int id) {
        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  propertyMapper.get(id);
    }

    @Override
    public void update(Property property) {
        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }
        propertyMapper.update(property);
        session.commit();
    }

    @Override
    public void delete(int id) {
        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<PropertyValue> pvs = propertyValueMapper.listByPtid(id);
        for (PropertyValue pv:pvs) {
            propertyValueMapper.delete(pv.getId());
        }
        propertyMapper.delete(id);
        session.commit();
    }

    @Override
    public List<Property> list() {
        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return propertyMapper.list();
    }

    @Override
    public Property get(int id) {
        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return propertyMapper.get(id);
    }
}

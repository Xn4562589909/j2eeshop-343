package com.iweb.service.impl;

import com.iweb.entity.User;
import com.iweb.mapper.UserMapper;
import com.iweb.service.UserService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Yang
 * @date 2023/4/3 17:04
 */
public class UserServletImpl implements UserService {
    String resource = "mybatis-config.xml";
    InputStream inputStream;
    SqlSessionFactory sqlSessionFactory;
    SqlSession session;
    UserMapper userMapper;

    public void init() throws IOException {
        // 建立输入流读取配置文件
        inputStream = Resources.getResourceAsStream(resource);
        // 实例化mybatis一级缓存
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        // 基于一级缓存实例化二级缓存
        session = sqlSessionFactory.openSession();
        userMapper = session.getMapper(UserMapper.class);
    }
    @Override
    public boolean login(User user) {
        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (user.getName()==null||user.getPassword()==null){
            return false;
        }
        User confirmUser = userMapper.getUser(user);
        return confirmUser != null;
    }

    @Override
    public boolean register(User user) {
        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (user.getName()==null||user.getPassword()==null){
            return false;
        }
        if (null!=userMapper.getByUsername(user.getName())){
            return false;
        }
        userMapper.add(user);
        session.commit();
        return userMapper.getUser(user)!=null;
    }

    @Override
    public User get(User user) {
        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userMapper.getUser(user);
    }

    @Override
    public void update(User user) {
        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }
        userMapper.update(user);
        session.commit();
    }
}

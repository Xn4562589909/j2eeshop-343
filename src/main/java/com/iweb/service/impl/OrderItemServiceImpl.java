package com.iweb.service.impl;

import com.iweb.entity.OrderItem;
import com.iweb.entity.ProductImage;
import com.iweb.mapper.*;
import com.iweb.service.OrderItemService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author Yang
 * @date 2023/4/10 4:49
 */
public class OrderItemServiceImpl implements OrderItemService {
//    OrderItemDAO orderItemDAO = new OrderItemDAOImpl();
//    ProductImageDAO productImageDAO = new ProductImageDAOImpl();

    // 定义配置文件路径

    String resource = "mybatis-config.xml";
    InputStream inputStream;
    SqlSessionFactory sqlSessionFactory;
    SqlSession session;
    OrderItemMapper orderItemMapper;
    ProductImageMapper productImageMapper;

    public void init() throws IOException {
        // 建立输入流读取配置文件
        inputStream = Resources.getResourceAsStream(resource);
        // 实例化mybatis一级缓存
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        // 基于一级缓存实例化二级缓存
        session = sqlSessionFactory.openSession();
        orderItemMapper = session.getMapper(OrderItemMapper.class);
        productImageMapper = session.getMapper(ProductImageMapper.class);
    }

    @Override
    public List<OrderItem> list(int uid) {
        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<OrderItem> orderItems = orderItemMapper.listByUid(uid);
        return orderItems;
    }

    @Override
    public int getTotal(int uid) {
        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return orderItemMapper.getTotalByUid(uid);
    }

    @Override
    public int getShoppingCartsNum(int uid) {
        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int carNum;
        if (null==orderItemMapper.getCarCount(uid)){
            carNum = 0;
        }else {
            carNum = orderItemMapper.getCarCount(uid);
        }
        return carNum;
    }

    @Override
    public void add(OrderItem orderItem) {
        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int flag = orderItemMapper.add(orderItem);
        if (flag == 1){
            session.commit();
        }
    }

    @Override
    public OrderItem getCarOrderItemByPid(int pid) {
        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return orderItemMapper.getCarOrderItemByPid(pid);
    }

    @Override
    public void update(OrderItem orderItem) {
        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int flag = orderItemMapper.update(orderItem);
        if (flag == 1){
            session.commit();
        }
    }

    @Override
    public List<OrderItem> listCar(int uid) {
        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<OrderItem> orderItems = orderItemMapper.listCar(uid);
        for (OrderItem oi:orderItems) {
            List<ProductImage> pis = productImageMapper.listByPid(oi.getProduct().getId());
            oi.getProduct().setImages(pis);
        }
        return orderItems;
    }

    @Override
    public OrderItem get(int id) {
        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return orderItemMapper.get(id);
    }

    @Override
    public void delete(int id) {
        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }
        orderItemMapper.delete(id);
        session.commit();
    }
}

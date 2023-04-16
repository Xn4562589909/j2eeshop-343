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
    ProductMapper productMapper;
    OrderItemMapper orderItemMapper;
    UserMapper userMapper;
    OrderMapper orderMapper;
    ProductImageMapper productImageMapper;

    public void init() throws IOException {
        // 建立输入流读取配置文件
        inputStream = Resources.getResourceAsStream(resource);
        // 实例化mybatis一级缓存
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        // 基于一级缓存实例化二级缓存
        session = sqlSessionFactory.openSession();
        productMapper = session.getMapper(ProductMapper.class);
        orderItemMapper = session.getMapper(OrderItemMapper.class);
        userMapper = session.getMapper(UserMapper.class);
        orderMapper = session.getMapper(OrderMapper.class);
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
        for (OrderItem oi:orderItems) {
            oi.setProduct(productMapper.get(oi.getPid()));
            oi.setUser(userMapper.get(oi.getUid()));
            oi.setOrder(orderMapper.get(oi.getOid()));
        }
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
        orderItem.setPid(orderItem.getProduct().getId());
        if (null == orderItem.getOrder()){
            orderItem.setOid(-1);
        }else {
            orderItem.setOid(orderItem.getOrder().getId());
        }
        orderItem.setUid(orderItem.getUser().getId());
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
        OrderItem orderItem = orderItemMapper.getCarOrderItemByPid(pid);
        if (null != orderItem){
            orderItem.setProduct(productMapper.get(pid));
            orderItem.setOrder(orderMapper.get(orderItem.getOid()));
            orderItem.setUser(userMapper.get(orderItem.getUid()));
        }
        return orderItem;
    }

    @Override
    public void update(OrderItem orderItem) {
        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }
        orderItem.setUid(orderItem.getUser().getId());
        orderItem.setOid(orderItem.getOrder().getId());
        orderItem.setPid(orderItem.getProduct().getId());
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
            oi.setUser(userMapper.get(oi.getUid()));
            oi.setProduct(productMapper.get(oi.getPid()));
            oi.setOrder(orderMapper.get(oi.getOid()));
            List<ProductImage> pis = productImageMapper.listByPid(oi.getPid());
            for (ProductImage pi:pis) {
                pi.setP(productMapper.get(pi.getPid()));
            }
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
        OrderItem orderItem = orderItemMapper.get(id);
        orderItem.setUser(userMapper.get(orderItem.getUid()));
        orderItem.setProduct(productMapper.get(orderItem.getPid()));
        orderItem.setOrder(orderMapper.get(orderItem.getOid()));
        return orderItem;
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

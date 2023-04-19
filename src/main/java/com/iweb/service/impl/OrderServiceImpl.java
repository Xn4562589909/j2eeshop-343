package com.iweb.service.impl;

import com.iweb.entity.Order;
import com.iweb.entity.OrderItem;
import com.iweb.entity.Product;
import com.iweb.entity.ProductImage;
import com.iweb.mapper.*;
import com.iweb.service.OrderService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author Yang
 * @date 2023/4/12 21:22
 */
public class OrderServiceImpl implements OrderService {
    // 定义配置文件路径

    String resource = "mybatis-config.xml";
    InputStream inputStream;
    SqlSessionFactory sqlSessionFactory;
    SqlSession session;
    OrderItemMapper orderItemMapper;
    OrderMapper orderMapper;
    ProductImageMapper productImageMapper;

    public void init() throws IOException {
        // 建立输入流读取配置文件
        inputStream = Resources.getResourceAsStream(resource);
        // 实例化mybatis一级缓存
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        // 基于一级缓存实例化二级缓存
        session = sqlSessionFactory.openSession();
        orderItemMapper = session.getMapper(OrderItemMapper.class);
        orderMapper = session.getMapper(OrderMapper.class);
        productImageMapper = session.getMapper(ProductImageMapper.class);
    }

    @Override
    public void add(Order order) {
        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }
        orderMapper.add(order);
        session.commit();
    }

    @Override
    public Order get(String orderCode) {
        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Order order = orderMapper.getByOrderCode(orderCode);
        List<OrderItem> ois = orderItemMapper.listByOid(order.getId());
        order.setOrderItems(ois);
        int totalNumber = 0;
        BigDecimal total = new BigDecimal(0);
        for (OrderItem oi:ois) {
            Product product = oi.getProduct();
            List<ProductImage> pis = productImageMapper.listByPid(product.getId());
            product.setImages(pis);
            totalNumber += oi.getNumber();
            BigDecimal price = oi.getProduct().getPromotePrice().multiply(new BigDecimal(oi.getNumber()));
            total = total.add(price);
        }
        order.setTotal(total);
        order.setTotalNumber(totalNumber);
        order.getStatusDesc();
        return order;
    }

    @Override
    public Order get(int id){
        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Order order = orderMapper.get(id);
        List<OrderItem> ois = orderItemMapper.listByOid(order.getId());
        int totalNumber = 0;
        BigDecimal total = new BigDecimal(0);
        for (OrderItem oi:ois) {
            Product product = oi.getProduct();
            List<ProductImage> pis = productImageMapper.listByPid(product.getId());
            product.setImages(pis);
            totalNumber += oi.getNumber();
            BigDecimal price = product.getPromotePrice().multiply(new BigDecimal(oi.getNumber()));
            total = total.add(price);
        }
        order.setOrderItems(ois);
        order.setTotal(total);
        order.setTotalNumber(totalNumber);
        order.getStatusDesc();
        return order;
    }

    @Override
    public void update(Order order) {
        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }
        orderMapper.update(order);
        session.commit();
    }

    @Override
    public List<Order> list(int uid) {
        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Order> orders = orderMapper.listByUid(uid);
        for (Order order:orders) {
            List<OrderItem> ois = orderItemMapper.listByOid(order.getId());
            int totalNumber = 0;
            BigDecimal total = new BigDecimal(0);
            for (OrderItem oi:ois) {
                totalNumber += oi.getNumber();
                BigDecimal price = oi.getProduct().getPromotePrice().multiply(new BigDecimal(oi.getNumber()));
                total = total.add(price);
            }
            order.setOrderItems(ois);
            order.setTotal(total);
            order.setTotalNumber(totalNumber);
            order.getStatusDesc();
        }
        return orders;
    }

    @Override
    public void delete(int id) {
        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }
        orderMapper.delete(id);
        session.commit();
    }
}

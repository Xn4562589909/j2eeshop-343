package com.iweb.service.impl;

import com.iweb.DAO.OrderDAO;
import com.iweb.DAO.OrderItemDAO;
import com.iweb.DAO.ProductImageDAO;
import com.iweb.DAO.impl.OrderDAOImpl;
import com.iweb.DAO.impl.OrderItemDAOImpl;
import com.iweb.DAO.impl.ProductImageDAOImpl;
import com.iweb.entity.Order;
import com.iweb.entity.OrderItem;
import com.iweb.entity.Product;
import com.iweb.entity.ProductImage;
import com.iweb.service.OrderService;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Yang
 * @date 2023/4/12 21:22
 */
public class OrderServiceImpl implements OrderService {
    OrderDAO orderDAO = new OrderDAOImpl();
    OrderItemDAO orderItemDAO = new OrderItemDAOImpl();
    ProductImageDAO productImageDAO = new ProductImageDAOImpl();
    @Override
    public void add(Order order) {
        orderDAO.add(order);
    }

    @Override
    public Order get(String orderCode) {
        Order order = orderDAO.get(orderCode);
        List<OrderItem> ois = orderItemDAO.listOrderItem(order.getId());
        order.setOrderItems(ois);
        int totalNumber = 0;
        BigDecimal total = new BigDecimal(0);
        for (OrderItem oi:ois) {
            Product product = oi.getProduct();
            List<ProductImage> pis = productImageDAO.list(product.getId());
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
        Order order = orderDAO.get(id);
        List<OrderItem> ois = orderItemDAO.listOrderItem(order.getId());
        order.setOrderItems(ois);
        int totalNumber = 0;
        BigDecimal total = new BigDecimal(0);
        for (OrderItem oi:ois) {
            Product product = oi.getProduct();
            List<ProductImage> pis = productImageDAO.list(product.getId());
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
    public void update(Order order) {
        orderDAO.update(order);
    }

    @Override
    public List<Order> list(int uid) {
        List<Order> orders = orderDAO.list(uid);
        for (Order order:orders) {
            List<OrderItem> ois = orderItemDAO.listOrderItem(order.getId());
            order.setOrderItems(ois);
            int totalNumber = 0;
            BigDecimal total = new BigDecimal(0);
            for (OrderItem oi:ois) {
                totalNumber += oi.getNumber();
                BigDecimal price = oi.getProduct().getPromotePrice().multiply(new BigDecimal(oi.getNumber()));
                total = total.add(price);
            }
            order.setTotal(total);
            order.setTotalNumber(totalNumber);
            order.getStatusDesc();
        }
        return orders;
    }

    @Override
    public void delete(int id) {
        orderDAO.delete(id);
    }
}

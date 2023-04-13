package com.iweb.service.impl;

import com.iweb.DAO.OrderItemDAO;
import com.iweb.DAO.ProductImageDAO;
import com.iweb.DAO.impl.OrderItemDAOImpl;
import com.iweb.DAO.impl.ProductImageDAOImpl;
import com.iweb.entity.OrderItem;
import com.iweb.entity.ProductImage;
import com.iweb.service.OrderItemService;

import java.util.List;

/**
 * @author Yang
 * @date 2023/4/10 4:49
 */
public class OrderItemServiceImpl implements OrderItemService {
    OrderItemDAO orderItemDAO = new OrderItemDAOImpl();
    ProductImageDAO productImageDAO = new ProductImageDAOImpl();
    @Override
    public List<OrderItem> list(int uid) {
        return orderItemDAO.list(uid);
    }

    @Override
    public int getTotal(int uid) {
        return orderItemDAO.getTotal(uid);
    }

    @Override
    public int getShoppingCartsNum(int uid) {
        return orderItemDAO.getShoppingCartsNum(uid);
    }

    @Override
    public void add(OrderItem orderItem) {
        orderItemDAO.add(orderItem);
    }

    @Override
    public OrderItem get(int pid, int oid) {
        return orderItemDAO.get(pid,oid);
    }

    @Override
    public void update(OrderItem orderItem) {
        orderItemDAO.update(orderItem);
    }

    @Override
    public List<OrderItem> listCar(int uid) {
        List<OrderItem> orderItems = orderItemDAO.listCar(uid);
        for (OrderItem oi:orderItems) {
            List<ProductImage> pis = productImageDAO.list(oi.getProduct().getId());
            oi.getProduct().setImages(pis);
        }
        return orderItems;
    }

    @Override
    public OrderItem get(int id) {
        return orderItemDAO.get(id);
    }

    @Override
    public void delete(int id) {
        orderItemDAO.delete(id);
    }
}

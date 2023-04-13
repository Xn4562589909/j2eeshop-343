package com.iweb.service;

import com.iweb.entity.Order;

import java.util.List;

/**
 * @author Yang
 * @date 2023/4/12 21:22
 */
public interface OrderService {
    /** 添加订单的业务方法
     * @param order 订单对象
     */
    void add(Order order);

    /** 根据订单编号查找订单的业务方法
     * @param orderCode 订单编号
     * @return 订单对象
     */
    Order get(String orderCode);

    /** 根据订单id查找订单的业务方法
     * @param id 订单id
     * @return 订单对象
     */
    Order get(int id);

    /** 修改订单的业务方法
     * @param order 订单对象
     */
    void update(Order order);

    /** 根据用户id查找订单的业务方法
     * @param uid 用户id
     * @return 订单集合
     */
    List<Order> list(int uid);

    /** 删除订单的业务方法
     * @param id 订单id
     */
    void delete(int id);
}

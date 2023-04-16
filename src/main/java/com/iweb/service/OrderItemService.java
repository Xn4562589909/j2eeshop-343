package com.iweb.service;

import com.iweb.entity.OrderItem;
import com.iweb.entity.Product;
import com.iweb.entity.User;

import java.util.List;

/**
 * @author Yang
 * @date 2023/4/10 4:49
 */
public interface OrderItemService {

    /** 根据用户id查询订单详情的业务方法
     * @param uid 用户id
     * @return 订单详情集合
     */
    List<OrderItem> list(int uid);

    /** 根据用户id查询订单详情数量的业务方法
     * @param uid 用户id
     * @return 总数量
     */
    int getTotal(int uid);

    /** 根据用户id查询购物车数量的业务方法
     * @param uid 用户id
     * @return 数量
     */
    int getShoppingCartsNum(int uid);

    /** 添加一条订单详情的业务方法
     * @param orderItem 订单详情对象
     */
    void add(OrderItem orderItem);

    /** 查询购物车的订单详情业务方法
     * @param pid 商品id
     * @return 订单详情对象
     */
    OrderItem getCarOrderItemByPid(int pid);

    /** 修改订单详情的业务方法
     * @param orderItem 订单详情对象
     */
    void update(OrderItem orderItem);

    /** 购物车清单
     * @param uid 用户id
     * @return 订单详情集合
     */
    List<OrderItem> listCar(int uid);

    /** 查找订单详情的业务方法
     * @param id 订单详情id
     * @return 订单详情对象
     */
    OrderItem get(int id);

    /** 删除订单详情
     * @param id 订单详情id
     */
    void delete(int id);
}

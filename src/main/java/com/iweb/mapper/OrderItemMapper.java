package com.iweb.mapper;

import com.iweb.entity.OrderItem;

import java.util.List;

/**
 * @author Yang
 * @date 2023/4/15 22:37
 */
public interface OrderItemMapper {

    /** 添加订单详情
     * @param orderItem 订单详情对象
     * @return 插入多少条记录
     */
    int add(OrderItem orderItem);

    /** 删除订单详情
     * @param id 订单详情id
     * @return 删除了多少条记录
     */
    int delete(Integer id);

    /** 根据id查询一条订单详情
     * @param id 订单详情id
     * @return 订单详情对象
     */
    OrderItem get(int id);

    /** 修改订单详情
     * @param orderItem 订单详情对象
     * @return 修改了多少条记录
     */
    int update(OrderItem orderItem);

    /** 查询所有订单详情数据
     * @return 订单详情集合
     */
    List<OrderItem> list();

    /** 查询表中一共有多少条数据
     * @return 数量
     */
    Integer getTotal();

    /** 查询用户订单详情的数据
     * @param uid 用户id
     * @return 订单详情集合
     */
    List<OrderItem> listByUid(int uid);

    /** 查询用户有多少条订单
     * @param uid 用户id
     * @return 数量
     */
    Integer getTotalByUid(int uid);

    /** 查询用户购物车数量
     * @param uid 用户id
     * @return 购物车数量
     */
    Integer getCarCount(int uid);

    /** 查询商品销售数量
     * @param pid 商品id
     * @return 销售数量
     */
    Integer getSaleCount(int pid);

    /** 根据商品id查询用户购物车中的订单详情
     * @param pid 商品id
     * @return 订单详情对象
     */
    OrderItem getCarOrderItemByPid(int pid);

    /** 查询用户的购物车
     * @param uid 用户id
     * @return 订单详情集合
     */
    List<OrderItem> listCar(int uid);

    /** 查询订单上的订单详情数据
     * @param oid 订单id
     * @return 订单详情集合
     */
    List<OrderItem> listByOid(int oid);
}

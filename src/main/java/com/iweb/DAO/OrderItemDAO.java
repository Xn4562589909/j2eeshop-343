package com.iweb.DAO;

import com.iweb.entity.OrderItem;

import java.util.List;

/**
 * @author Yang
 * @date 2023/4/1 17:46
 */
public interface OrderItemDAO extends BaseDAO<OrderItem> {
    /** 根据用户id查找订单详情
     * @param uid 用户id
     * @return 订单详情集合
     */
    List<OrderItem> list(int uid);

    /** 根据用户id分页查找订单详情
     * @param uid 用户id
     * @param start 起始坐标
     * @param count 截取数量
     * @return 订单详情集合
     */
    List<OrderItem> list(int uid,int start,int count);

    /** 根据用户id查询订单详情数量
     * @param uid 用户id
     * @return 总数
     */
    int getTotal(int uid);

    /** 根据用户id查询购物车数量
     * @param uid 用户id
     * @return 数量
     */
    int getShoppingCartsNum(int uid);

    /** 根据订单详情查询商品的销量
     * @param pid 商品id
     * @return 销量
     */
    int getSaleCount(int pid);

    /** 根据商品id和订单id查询订单详情表
     * @param pid 商品id
     * @param oid 订单id
     * @return 订单详情
     */
    OrderItem get(int pid,int oid);

    /** 查询用户的购物车清单
     * @param uid 用户id
     * @return 订单详情集合
     */
    List<OrderItem> listCar(int uid);

    /** 根据订单id查找订单详情
     * @param oid 订单id
     * @return 订单详情集合
     */
    List<OrderItem> listOrderItem(int oid);
}

package com.iweb.DAO;

import com.iweb.entity.Order;

import java.util.List;

/**
 * @author Yang
 * @date 2023/4/1 1:10
 */
public interface OrderDAO extends BaseDAO<Order> {
    /** 根据订单编号找到订单
     * @param orderCode 订单编号
     * @return 订单对象
     */
    Order get(String orderCode);

    /** 根据用户id查找订单
     * @param uid 用户id
     * @return 订单集合
     */
    List<Order> list(int uid);
}

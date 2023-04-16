package com.iweb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 购物车中的数据和订单中的数据 绑定的都是订单详情 OrderItem
// 购物车中的订单详情 没有生成订单 可以默认将这些订单详情的oid设置为-1
// select * from orderitem where uid = 1 and oid = -1
//购物车进行结算的时候 相当于将这些关联oid为-1的订单详情 的oid统一设置为新创建的
//订单的id
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {
    private int number;
    private Product product;
    private int pid;
    private Order order;
    private int oid;
    private User user;
    private int uid;
    private int id;
    private String gmtCreate;
    private String gmtModified;
}

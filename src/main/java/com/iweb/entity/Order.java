package com.iweb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private String orderCode;
    private String address;
    private String post;
    private String receiver;
    private String mobile;
    private String userMessage;
    private String createDate;
    private Date payDate;
    private Date deliveryDate;
    private Date confirmDate;
    private User user;
    private int id;
    private String gmtModified;
    public void getStatusDesc(){
        String desc = "未知";
        //可以使用Enum枚举类来代替传统的静态常量
        OrderStatus[] oss = OrderStatus.values();
        for (OrderStatus os: oss) {
            if (status.equals(os.getCode())){
                status = os.getName();
                return;
            }
        }
        status=desc;
    }
    //每一个订单所对应的订单详情集合
    private List<OrderItem> orderItems;
    //订单的总计金额
    private BigDecimal total;
    //订单的商品总数量
    private int totalNumber;
    //订单状态
    private String status;
}

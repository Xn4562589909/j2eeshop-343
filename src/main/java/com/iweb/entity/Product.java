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
public class Product {
    private String name;
    private String subTitle;//
    private BigDecimal originalPrice;
    private BigDecimal promotePrice;
    private int stock;
    private String createDate;
    private Category category;
    private int id;
    private String gmtModified;
    // 评价数量属性和销量属性应该是基于你的订单信息获取的
    // select sum(num) from orderitem where pid = ? and oid != -1
    // select count(*) from review where pid = ?
    private int reviewCount;//评价数量
    private int saleCount;// 销量
    private List<ProductImage> images;
}

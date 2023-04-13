package com.iweb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    private String name;
    private int id;
    private String gmtCreate;
    private String gmtModified;
    //每一个分类所对应的商品集合
    List<Product> products;
}

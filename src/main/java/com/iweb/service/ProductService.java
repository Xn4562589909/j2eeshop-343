package com.iweb.service;

import com.iweb.entity.Product;

import java.util.List;

/**
 * @author Yang
 * @date 2023/4/3 23:31
 */
public interface ProductService {
    /** 根据分类id查询商品的业务方法
     * @param cid 分类id
     * @return 商品集合
     */
    List<Product> list(int cid);

    /** 添加一个商品的业务方法
     * @param product 商品对象
     */
    void add(Product product);

    /** 根据id删除商品对象
     * @param id 商品id
     */
    void delete(int id);

    /** 指定id查找商品的业务方法
     * @param id 商品id
     * @return 商品对象
     */
    Product get(int id);

    /** 修改商品的业务方法
     * @param product 商品对象
     */
    void update(Product product);

    /**查询所有商品的业务方法
     * @return 商品集合
     */
    List<Product> list();

    /** 模糊查询商品的业务方法
     * @param name 商品名称
     * @return 商品集合
     */
    List<Product> list(String name);
}

package com.iweb.mapper;

import com.iweb.entity.Product;

import java.util.List;

/**
 * @author Yang
 * @date 2023/4/15 18:03
 */
public interface ProductMapper {
    /** 添加商品
     * @param product 商品对象
     * @return 插入多少条记录
     */
    int add(Product product);

    /** 删除商品
     * @param id 商品id
     * @return 删除了多少条记录
     */
    int delete(Integer id);

    /** 根据id查询一条商品数据
     * @param id 商品id
     * @return 商品对象
     */
    Product get(int id);

    /** 修改商品
     * @param product 商品对象
     * @return 修改了多少条记录
     */
    int update(Product product);

    /** 查询所有商品数据
     * @return 商品集合
     */
    List<Product> list();

    /** 查询表中一共有多少条数据
     * @return 数量
     */
    int getTotal();

    /** 根据分类id查找商品
     * @param cid 分类id
     * @return 商品集合
     */
    List<Product> listByCid(int cid);

    /** 根据分类id查找有多少条商品数据
     * @param cid 分类id
     * @return 商品数量
     */
    int getTotalByCid(int cid);

    /** 模糊查询商品表
     * @param name 关键字
     * @return 商品集合
     */
    List<Product> listByName(String name);
}

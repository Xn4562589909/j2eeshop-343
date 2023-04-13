package com.iweb.DAO;

import com.iweb.entity.Product;

import java.util.List;

/**
 * @author Yang
 * @date 2023/4/1 18:21
 */
public interface ProductDAO extends BaseDAO<Product> {
    /** 根据分类id查找商品的业务方法
     * @param cid 分类id
     * @return 商品集合
     */
    List<Product> list(int cid);

    /** 根据分类id进行分页查询的业务方法
     * @param cid 分类id
     * @param start 起始坐标
     * @param count 截取数量
     * @return 商品集合
     */
    List<Product> list(int cid,int start,int count);

    /** 查询分类中有多少条商品数据
     * @param cid 分类id
     * @return 总数量
     */
    int getTotal(int cid);

    /** 根据名称模糊查询商品表
     * @param name 名称
     * @return 商品集合
     */
    List<Product> list(String name);
}

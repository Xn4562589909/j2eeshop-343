package com.iweb.DAO;

import com.iweb.entity.Property;

import java.util.List;

/**
 * @author Yang
 * @date 2023/4/1 20:02
 */
public interface PropertyDAO extends BaseDAO<Property> {
    /** 根据分类id查找属性
     * @param cid 分类id
     * @return 查找到属性对象集合
     */
    List<Property> list(int cid);

    /** 根据分类id 分页查询属性
     * @param cid 分类id
     * @param start 起始位置
     * @param count 截取数量
     * @return 查找到的属性对象集合
     */
    List<Property> list(int cid,int start,int count);

    /** 根据分类id查找有多少条属性
     * @param cid 分类id
     * @return 总数量
     */
    int getTotal(int cid);

}

package com.iweb.service;

import com.iweb.entity.Property;

import java.util.List;

/**
 * @author Yang
 * @date 2023/4/3 21:41
 */
public interface PropertyService {
    /** 根据分类id查找属性的业务方法
     * @param cid 分类id
     * @return 查找到的集合容器
     */
    List<Property> list(int cid);

    /** 添加一个属性的业务方法
     * @param category
     */
    void add(Property category);

    /** 根据id编辑属性的业务方法
     * @param id 属性id
     * @return 属性对象
     */
    Property edit(int id);

    /** 修改属性的业务方法
     * @param category 属性对象
     */
    void update(Property category);

    /** 删除属性的业务方法
     * @param id 属性id
     */
    void delete(int id);

    /** 查询所有属性的业务方法
     * @return 属性集合
     */
    List<Property> list();

    /** 查询指定id的属性值的业务方法
     * @param id 属性值id
     * @return 属性值对象
     */
    Property get(int id);

}

package com.iweb.service;

import com.iweb.entity.Property;
import com.iweb.entity.PropertyValue;

import java.util.List;

/**
 * @author Yang
 * @date 2023/4/4 2:28
 */
public interface PropertyValueService {

    /** 根据商品id查找属性值的业务方法
     * @param pid 商品id
     * @return 属性值集合
     */
    List<PropertyValue> list(int pid);

    /** 根据id查找属性值
     * @param id 属性值id
     * @return 属性值对象
     */
    PropertyValue get(int id);

    /** 修改属性值的业务方法
     * @param pv 属性值对象
     */
    void update(PropertyValue pv);

    /** 添加一条属性值的业务方法
     * @param pv 属性值对象
     */
    void add(PropertyValue pv);

    /** 找出商品下未添加的属性值
     * @param properties 该商品的分类下所有对应的属性集合
     * @param propertyValues 该商品已经添加了的属性值
     * @return 还未添加的属性值集合
     */
    List<Property> listNotAddPt(List<Property> properties,List<PropertyValue> propertyValues);

    /** 根据id删除属性值的业务方法
     * @param id 属性值id
     */
    void delete(int id);
}

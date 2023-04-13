package com.iweb.DAO;

import com.iweb.entity.PropertyValue;

import java.util.List;

/**
 * @author Yang
 * @date 2023/4/1 21:34
 */
public interface PropertyValueDAO extends BaseDAO<PropertyValue> {
    /** 根据商品id查找属性值
     * @param pid 商品id
     * @return 属性值集合
     */
    List<PropertyValue> list(int pid);

    /** 根据商品id 分页查找属性值
     * @param pid 商品id
     * @param start 起始坐标
     * @param count 截取数量
     * @return 属性值集合
     */
    List<PropertyValue> list(int pid,int start,int count);

    /** 查询指定商品id下有多少条属性值数据
     * @param pid 商品id
     * @return 总数量
     */
    int getTotal(int pid);
}

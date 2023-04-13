package com.iweb.DAO;

import java.util.List;

/**
 * @author Yang
 * @date 2023/3/31 23:29
 */
public interface BaseDAO<T> {
    /** 查询表中有多少条数据
     * @return 总数量
     */
    int getTotal();

    /** 添加一条数据
     * @param t 数据对象
     */
    void add(T t);

    /** 修改一条数据
     * @param t 数据对象
     */
    void update(T t);

    /** 删除一条数据
     * @param id 数据对象id
     */
    void delete(int id);

    /** 根据id查询数据
     * @param id 对象id
     * @return 数据对象
     */
    T get(int id);

    /** 查询所有数据
     * @return 查询到的数据集合
     */
    List<T> list();

    /** 分页查询
     * @param start 起始坐标
     * @param count 截取数量
     * @return 查询到的数据集合
     */
    List<T> list(int start, int count);

    /**
     * 更新修改时间
     * @param id 对象id
     */
    void updateTime(int id);
}

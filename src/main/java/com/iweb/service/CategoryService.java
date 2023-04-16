package com.iweb.service;

import com.iweb.entity.Category;

import java.io.IOException;
import java.util.List;

/**
 * @author Yang
 * @date 2023/4/3 17:36
 */
public interface CategoryService {
    /** 查询所有分类的业务方法
     * @return 存储分类的集合
     */
    List<Category> list();

    /** 添加分类的业务方法
     * @param category 分类
     */
    void add(Category category);

    /** 编辑分类的业务方法
     * @param id 分类id
     * @return 分类对象
     */
    Category edit(int id);

    /** 修改分类的业务方法
     * @param category 分类对象
     */
    void update(Category category);

    /** 删除分类的业务方法
     * @param id 分类id
     */
    void delete(int id);

    /** 查找指定id 分类
     * @param id 分类id
     * @return 分类对象
     */
    Category get(int id);
}

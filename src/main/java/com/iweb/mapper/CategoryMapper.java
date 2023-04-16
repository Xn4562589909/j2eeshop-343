package com.iweb.mapper;

import com.iweb.entity.Category;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author Yang
 * @date 2023/4/15 17:07
 */
public interface CategoryMapper {
    /** 添加分类
     * @param category 分类对象
     * @return 插入多少条记录
     */
    @Insert("insert into category(name) values (#{name})")
    int add(Category category);

    /** 删除分类
     * @param id 分类id
     * @return 删除了多少条记录
     */
    @Delete("delete from category where id = #{id}")
    int delete(Integer id);

    /** 根据id查询一条分类
     * @param id 分类id
     * @return 分类对象
     */
    @Select("select * from category where id = #{id}")
    Category get(int id);

    /** 修改分类
     * @param category 分类对象
     * @return 修改了多少条记录
     */
    @Update("update category set `name`=#{name},gmtModified=now() where id = #{id}")
    int update(Category category);

    /** 查询所有分类数据
     * @return 分类集合
     */
    @Select("select * from category")
    List<Category> list();


    /** 查询表中一共有多少条数据
     * @return 数量
     */
    @Select("select count(*) from category")
    int getTotal();

}

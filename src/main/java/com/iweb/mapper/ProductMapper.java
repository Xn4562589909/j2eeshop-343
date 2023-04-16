package com.iweb.mapper;

import com.iweb.entity.Product;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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
    @Insert("insert into product(`name`,subTitle,originalPrice,promotePrice,stock,cid) " +
            "values(#{name},#{subTitle},#{originalPrice},#{promotePrice},#{stock},#{cid})")
    int add(Product product);

    /** 删除商品
     * @param id 商品id
     * @return 删除了多少条记录
     */
    @Delete("delete from product where id = #{id}")
    int delete(Integer id);

    /** 根据id查询一条商品数据
     * @param id 商品id
     * @return 商品对象
     */
    @Select("select * from product where id = #{id}")
    Product get(int id);

    /** 修改商品
     * @param product 商品对象
     * @return 修改了多少条记录
     */
    @Update("update product set `name`=#{name},subTitle=#{subTitle},originalPrice=#{originalPrice}," +
            "promotePrice=#{promotePrice},stock=#{stock},cid=#{cid},gmtModified=now() where id=#{id}")
    int update(Product product);

    /** 查询所有商品数据
     * @return 商品集合
     */
    @Select("select * from product")
    List<Product> list();

    /** 查询表中一共有多少条数据
     * @return 数量
     */
    @Select("select count(*) from product")
    int getTotal();

    /** 根据分类id查找商品
     * @param cid 分类id
     * @return 商品集合
     */
    @Select("select * from product where cid=#{cid}")
    List<Product> listByCid(int cid);

    /** 根据分类id查找有多少条商品数据
     * @param cid 分类id
     * @return 商品数量
     */
    @Select("select count(*) from product where cid=#{cid}")
    int getTotalByCid(int cid);

    /** 模糊查询商品表
     * @param name 关键字
     * @return 商品集合
     */
    @Select("select * from product where `name` like concat('%',#{name},'%')")
    List<Product> listByName(String name);
}

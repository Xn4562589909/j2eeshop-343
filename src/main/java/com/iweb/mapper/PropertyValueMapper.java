package com.iweb.mapper;

import com.iweb.entity.PropertyValue;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author Yang
 * @date 2023/4/16 0:32
 */
public interface PropertyValueMapper {
    /** 添加商品属性值
     * @param propertyValue 商品属性值对象
     * @return 插入多少条记录
     */
    @Insert("insert into propertyvalue(pid,`ptid`,`value`) values(#{pid},#{ptid},#{value})")
    int add(PropertyValue propertyValue);

    /** 删除商品属性值
     * @param id 商品属性值id
     * @return 删除了多少条记录
     */
    @Delete("delete from propertyvalue where id = #{id}")
    int delete(Integer id);

    /** 根据id查询商品属性值
     * @param id 商品属性值id
     * @return 商品属性值对象
     */
    @Select("select * from propertyvalue where id = #{id}")
    PropertyValue get(int id);

    /** 修改商品属性值
     * @param propertyValue 商品属性值对象
     * @return 修改了多少条记录
     */
    @Update("update propertyvalue set pid=#{pid},ptid=#{ptid},`value`=#{value},gmtModified=now() where id=#{id}")
    int update(PropertyValue propertyValue);

    /** 查询所有商品属性数据
     * @return 商品属性集合
     */
    @Select("select * from propertyvalue")
    List<PropertyValue> list();

    /** 查询表中一共有多少条数据
     * @return 数量
     */
    @Select("select count(*) from propertyvalue")
    int getTotal();

    /** 查询商品下所有的属性值
     * @param pid 商品id
     * @return 属性值集合
     */
    @Select("select * from propertyvalue where pid=#{pid}")
    List<PropertyValue> listByPid(int pid);

}

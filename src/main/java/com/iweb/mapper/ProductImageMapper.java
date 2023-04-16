package com.iweb.mapper;

import com.iweb.entity.ProductImage;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author Yang
 * @date 2023/4/16 0:14
 */
public interface ProductImageMapper {
    /** 添加商品图片
     * @param productImage 商品图片对象
     * @return 插入多少条记录
     */
    @Insert("insert into img(url,pid) values(#{url},#{pid})")
    int add(ProductImage productImage);

    /** 删除商品图片
     * @param id 商品图片id
     * @return 删除了多少条记录
     */
    @Delete("delete from img where id = #{id}")
    int delete(Integer id);

    /** 根据id查询商品图片
     * @param id 商品图片id
     * @return 商品图片对象
     */
    @Select("select * from img where id = #{id}")
    ProductImage get(int id);

    /** 修改商品图片
     * @param productImage 商品图片对象
     * @return 修改了多少条记录
     */
    @Update("update img set url=#{url},pid=#{pid},gmtModified=now() where id=#{id}")
    int update(ProductImage productImage);

    /** 查询所有商品图片数据
     * @return 商品图片集合
     */
    @Select("select * from img")
    List<ProductImage> list();

    /** 查询表中一共有多少条数据
     * @return 数量
     */
    @Select("select count(*) from img")
    int getTotal();

    /** 查询商品下所有图片
     * @param pid 商品id
     * @return 图片集合
     */
    @Select("select * from img where pid = #{pid}")
    List<ProductImage> listByPid(int pid);
}

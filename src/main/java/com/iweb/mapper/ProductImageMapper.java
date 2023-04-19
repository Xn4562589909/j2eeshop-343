package com.iweb.mapper;

import com.iweb.entity.ProductImage;
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
    int add(ProductImage productImage);

    /** 删除商品图片
     * @param id 商品图片id
     * @return 删除了多少条记录
     */
    int delete(Integer id);

    /** 根据id查询商品图片
     * @param id 商品图片id
     * @return 商品图片对象
     */
    ProductImage get(int id);

    /** 修改商品图片
     * @param productImage 商品图片对象
     * @return 修改了多少条记录
     */
    int update(ProductImage productImage);

    /** 查询所有商品图片数据
     * @return 商品图片集合
     */
    List<ProductImage> list();

    /** 查询表中一共有多少条数据
     * @return 数量
     */
    int getTotal();

    /** 查询商品下所有图片
     * @param pid 商品id
     * @return 图片集合
     */
    List<ProductImage> listByPid(int pid);
}

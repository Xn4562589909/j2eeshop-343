package com.iweb.DAO;

import com.iweb.entity.ProductImage;

import java.util.List;

/**
 * @author Yang
 * @date 2023/4/1 19:43
 */
public interface ProductImageDAO extends BaseDAO<ProductImage> {
    /** 根据商品id查询商品图片
     * @param pid 商品id
     * @return 商品图片集合
     */
    List<ProductImage> list(int pid);

    /** 根据商品id分页查询商品图片
     * @param pid 商品id
     * @param start 起始坐标
     * @param count 截取数量
     * @return 商品图片集合
     */
    List<ProductImage> list(int pid,int start,int count);

    /** 根据商品id查询商品图片总数
     * @param pid 商品id
     * @return 总数
     */
    int getTotal(int pid);
}

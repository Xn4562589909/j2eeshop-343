package com.iweb.service;

import com.iweb.entity.ProductImage;

import java.util.List;

/**
 * @author Yang
 * @date 2023/4/7 19:43
 */
public interface ProductImageService {
    /** 根据商品id查询图片的业务方法
     * @param pid 商品id
     * @return 商品图片集合
     */
    List<ProductImage> list(int pid);

    /** 添加一个商品图片的业务方法
     * @param pi 商品图片对象
     */
    void add(ProductImage pi);

    /** 删除商品图片的业务方法
     * @param id 商品图片id
     */
    void delete(int id);
}

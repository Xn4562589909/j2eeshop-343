package com.iweb.service;

import com.iweb.entity.Review;

import java.util.List;

/**
 * @author Yang
 * @date 2023/4/11 19:20
 */
public interface ReviewService {
    /** 根据商品id查询评价表的业务方法
     * @param pid 商品id
     * @return 评价集合
     */
    List<Review> list(int pid);

    /** 添加一条评论
     * @param review 评论对象
     */
    void add(Review review);
}

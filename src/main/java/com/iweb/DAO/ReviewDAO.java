package com.iweb.DAO;

import com.iweb.entity.Review;

import java.util.List;

/**
 * @author Yang
 * @date 2023/4/1 22:00
 */
public interface ReviewDAO extends BaseDAO<Review> {
    /** 根据商品id查询评价表
     * @param pid 商品id
     * @return 评价集合
     */
    List<Review> list(int pid);

    /** 根据商品id分页查询评价表
     * @param pid 商品id
     * @param start 起始坐标
     * @param count 截取数量
     * @return 评价集合
     */
    List<Review> list(int pid,int start,int count);

    /** 根据商品id查询有多少条评论
     * @param pid 商品id
     * @return 总数
     */
    int getTotal(int pid);

    /** 根据用户id查询用户的所有评论
     * @param uid 用户id
     * @return 评论集合
     */
    List<Review> listUserReviews(int uid);
}

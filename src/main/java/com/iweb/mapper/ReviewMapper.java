package com.iweb.mapper;

import com.iweb.entity.Review;

import java.util.List;

/**
 * @author Yang
 * @date 2023/4/16 0:44
 */
public interface ReviewMapper {
    /** 添加评论
     * @param review 评论对象
     * @return 插入多少条记录
     */
    int add(Review review);

    /** 删除评论
     * @param id 评论id
     * @return 删除了多少条记录
     */
    int delete(Integer id);

    /** 根据id查询评论
     * @param id 评论id
     * @return 评论对象
     */
    Review get(int id);

    /** 修改评论
     * @param review 评论对象
     * @return 修改了多少条记录
     */
    int update(Review review);

    /** 查询所有评论数据
     * @return 评论集合
     */
    List<Review> list();

    /** 查询表中一共有多少条数据
     * @return 数量
     */
    int getTotal();

    /** 查询指定商品下所有评论
     * @param pid 商品id
     * @return 评论集合
     */
    List<Review> listByPid(int pid);

    /** 查询商品下所有的评论数量
     * @param pid 商品id
     * @return 评论数量
     */
    Integer getTotalByPid(int pid);

    /** 查询用户的所有评论
     * @param uid 用户id
     * @return 评论集合
     */
    List<Review> listByUid(int uid);
}

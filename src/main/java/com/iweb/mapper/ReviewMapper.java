package com.iweb.mapper;

import com.iweb.entity.Review;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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
    @Insert("insert into review(content,uid,pid,nickname) values(#{content},#{uid},#{pid},#{nickname})")
    int add(Review review);

    /** 删除评论
     * @param id 评论id
     * @return 删除了多少条记录
     */
    @Delete("delete from review where id = #{id}")
    int delete(Integer id);

    /** 根据id查询评论
     * @param id 评论id
     * @return 评论对象
     */
    @Select("select * from review where id = #{id}")
    Review get(int id);

    /** 修改评论
     * @param review 评论对象
     * @return 修改了多少条记录
     */
    @Update("update review set content=#{content},uid=#{uid},pid=#{pid},nickname=#{nickname},gmtModified=now() where id=#{id}")
    int update(Review review);

    /** 查询所有评论数据
     * @return 评论集合
     */
    @Select("select * from review")
    List<Review> list();

    /** 查询表中一共有多少条数据
     * @return 数量
     */
    @Select("select count(*) from review")
    int getTotal();

    /** 查询指定商品下所有评论
     * @param pid 商品id
     * @return 评论集合
     */
    @Select("select * from review where pid=#{pid}")
    List<Review> listByPid(int pid);

    /** 查询商品下所有的评论数量
     * @param pid 商品id
     * @return 评论数量
     */
    @Select("select count(*) from review where pid=#{pid}")
    Integer getTotalByPid(int pid);

    /** 查询用户的所有评论
     * @param uid 用户id
     * @return 评论集合
     */
    @Select("select * from review where uid=#{uid}")
    List<Review> listByUid(int uid);
}

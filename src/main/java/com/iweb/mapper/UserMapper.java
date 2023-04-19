package com.iweb.mapper;

import com.iweb.entity.User;

import java.util.List;

/**
 * @author Yang
 * @date 2023/4/16 0:56
 */
public interface UserMapper {
    /** 添加用户
     * @param user 用户对象
     * @return 插入多少条记录
     */
    int add(User user);

    /** 删除用户
     * @param id 用户id
     * @return 删除了多少条记录
     */
    int delete(Integer id);

    /** 根据id查询用户
     * @param id 用户id
     * @return 用户对象
     */
    User get(int id);

    /** 修改用户
     * @param user 用户对象
     * @return 修改了多少条记录
     */
    int update(User user);

    /** 查询所有评论数据
     * @return 评论集合
     */
    List<User> list();

    /** 查询表中一共有多少条数据
     * @return 数量
     */
    int getTotal();

    /** 根据用户名和密码查询用户
     * @param user 用户对象
     * @return 用户对象
     */
    User getUser(User user);

    /** 通过用户名查询用户信息
     * @param username 用户
     * @return 用户对象
     */
    User getByUsername(String username);
}

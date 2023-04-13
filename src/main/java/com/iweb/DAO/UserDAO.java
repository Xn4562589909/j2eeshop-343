package com.iweb.DAO;

import com.iweb.entity.User;

/**
 * @author Yang
 * @date 2023/4/1 1:46
 */
public interface UserDAO extends BaseDAO<User> {
    /** 根据用户名和密码查询用户
     * @param u 用户名和密码
     * @return User对象
     */
    User get(User u);
}

package com.iweb.service.impl;

import com.iweb.DAO.UserDAO;
import com.iweb.DAO.impl.UserDAOImpl;
import com.iweb.entity.User;
import com.iweb.service.UserService;

/**
 * @author Yang
 * @date 2023/4/3 17:04
 */
public class UserServletImpl implements UserService {
    UserDAO userDAO = new UserDAOImpl();
    @Override
    public boolean login(User user) {
        if (user.getName()==null||user.getPassword()==null){
            return false;
        }
        User confirmUser = userDAO.get(user);
        return confirmUser != null;
    }

    @Override
    public boolean register(User user) {
        if (user.getName()==null||user.getPassword()==null){
            return false;
        }
        userDAO.add(user);
        return userDAO.get(user)!=null;
    }

    @Override
    public User get(User user) {
        return userDAO.get(user);
    }

    @Override
    public void update(User user) {
        userDAO.update(user);
    }
}

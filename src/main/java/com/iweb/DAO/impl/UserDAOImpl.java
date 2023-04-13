package com.iweb.DAO.impl;

import com.iweb.DAO.UserDAO;
import com.iweb.entity.User;
import com.iweb.util.JdbcUtil;
import com.iweb.util.DateUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Yang
 * @date 2023/4/1 1:47
 */
public class UserDAOImpl implements UserDAO {
    @Override
    public int getTotal() {
        int count = 0;
        String sql = "select count(*) from `user`";
        try(Connection c = JdbcUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)){
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                count = rs.getInt(1);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public void add(User user) {
        String sql = "insert into `user`(`name`,password) values(?,?)";
        try(Connection c = JdbcUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)){
            ps.setString(1,user.getName());
            ps.setString(2,user.getPassword());
            ps.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void update(User user) {
        String sql = "update `user` set `name`=?,password=? where id=?";
        try(Connection c = JdbcUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)){
            ps.setString(1,user.getName());
            ps.setString(2,user.getPassword());
            ps.setInt(3,user.getId());
            ps.execute();
            updateTime(user.getId());
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "delete from review where id = ?";
        try(Connection c = JdbcUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1,id);
            ps.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public User get(int id) {
        String sql = "select * from `user` where id = ?";
        User user = null;
        try(Connection c = JdbcUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                user = new User();
                user.setId(id);
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                user.setGmtCreate(DateUtil.getTime(rs.getTimestamp("gmt_create")));
                user.setGmtModified(DateUtil.getTime(rs.getTimestamp("gmt_modified")));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> list() {
        return list(0,getTotal());
    }

    @Override
    public List<User> list(int start, int count) {
        String sql = "select * from `user` limit ?,?";
        List<User> list = new ArrayList<>();
        try(Connection c = JdbcUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1,start);
            ps.setInt(2,count);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                user.setGmtCreate(DateUtil.getTime(rs.getTimestamp("gmt_create")));
                user.setGmtModified(DateUtil.getTime(rs.getTimestamp("gmt_modified")));
                list.add(user);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void updateTime(int id) {
        String sql = "update `user` set gmt_modified = now() where id = ?";
        try(Connection c = JdbcUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1,id);
            ps.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public User get(User u) {
        String sql = "select * from `user` where `name` = ? and password = ?";
        User user = null;
        try(Connection c = JdbcUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1,u.getName());
            ps.setString(2,u.getPassword());
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                user = new User();
                user.setId(rs.getInt("id"));
                user.setName(u.getName());
                user.setPassword(u.getPassword());
                user.setGmtCreate(DateUtil.getTime(rs.getTimestamp("gmt_create")));
                user.setGmtModified(DateUtil.getTime(rs.getTimestamp("gmt_modified")));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return user;
    }
}


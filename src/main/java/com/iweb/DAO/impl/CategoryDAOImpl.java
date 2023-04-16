package com.iweb.DAO.impl;

import com.iweb.DAO.CategoryDAO;
import com.iweb.entity.Category;
import com.iweb.util.JdbcUtil;
import com.iweb.util.DateUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Yang
 * @date 2023/3/31 23:44
 */
public class CategoryDAOImpl implements CategoryDAO {
    @Override
    public int getTotal() {
        int count = 0;
        String sql = "select count(*) from category";
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
    public void add(Category category) {
        String sql = "insert into category(name) values(?)";
        try(Connection c = JdbcUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)){
            ps.setString(1,category.getName());
            ps.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void update(Category category) {
        String sql = "update category set name=? where id=?";
        try(Connection c = JdbcUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)){
            ps.setString(1,category.getName());
            ps.setInt(2,category.getId());
            ps.execute();
            updateTime(category.getId());
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "delete from category where id = ?";
        try(Connection c = JdbcUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1,id);
            ps.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public Category get(int id) {
        String sql = "select * from category where id = ?";
        Category category = null;
        try(Connection c = JdbcUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                category = new Category();
                category.setId(id);
                category.setName(rs.getString("name"));
//                category.setGmtCreate(DateUtil.getTime(rs.getTimestamp("gmt_create")));
//                category.setGmtModified(DateUtil.getTime(rs.getTimestamp("gmt_modified")));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return category;
    }

    @Override
    public List<Category> list() {
        return list(0,getTotal());
    }

    @Override
    public List<Category> list(int start, int count) {
        String sql = "select * from category limit ?,?";
        List<Category> categoryList = new ArrayList<>();
        try(Connection c = JdbcUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1,start);
            ps.setInt(2,count);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Category category = new Category();
                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));
//                category.setGmtCreate(DateUtil.getTime(rs.getTimestamp("gmt_create")));
//                category.setGmtModified(DateUtil.getTime(rs.getTimestamp("gmt_modified")));
                categoryList.add(category);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return categoryList;
    }

    @Override
    public void updateTime(int id) {
        String sql = "update category set gmt_modified = now() where id = ?";
        try(Connection c = JdbcUtil.getConnection();
        PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1,id);
            ps.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}

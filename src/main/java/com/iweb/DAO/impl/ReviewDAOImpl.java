package com.iweb.DAO.impl;

import com.iweb.DAO.ProductDAO;
import com.iweb.DAO.ReviewDAO;
import com.iweb.DAO.UserDAO;
import com.iweb.entity.Review;
import com.iweb.entity.User;
import com.iweb.util.JdbcUtil;
import com.iweb.util.DateUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Yang
 * @date 2023/4/1 22:00
 */
public class ReviewDAOImpl implements ReviewDAO {
    UserDAO userDAO = new UserDAOImpl();
    ProductDAO productDAO = new ProductDAOImpl();
    @Override
    public int getTotal() {
        int count = 0;
        String sql = "select count(*) from review";
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
    public void add(Review review) {
        String sql = "insert into review(content,uid,pid,nickname) values(?,?,?,?)";
        try(Connection c = JdbcUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)){
            ps.setString(1,review.getContent());
            ps.setInt(2,review.getUser().getId());
            ps.setInt(3,review.getProduct().getId());
            ps.setString(4,review.getUser().getName());
            ps.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void update(Review review) {
        String sql = "update review set content=?,uid=?,pid=?,nickname=? where id=?";
        try(Connection c = JdbcUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)){
            ps.setString(1,review.getContent());
            ps.setInt(2,review.getUser().getId());
            ps.setInt(3,review.getProduct().getId());
            ps.setString(4,review.getUser().getName());
            ps.execute();
            updateTime(review.getId());
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
    public Review get(int id) {
        String sql = "select * from review where id = ?";
        Review review = null;
        try(Connection c = JdbcUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                review = new Review();
                review.setId(id);
                review.setContent(rs.getString("content"));
                User user = new User();
                user.setName(rs.getString("nickname"));
                user.setId(rs.getInt("uid"));
                review.setUser(user);
                review.setProduct(productDAO.get(rs.getInt("pid")));
                review.setCreateDate(new Timestamp(rs.getTimestamp("createDate").getTime()));
                review.setGmtModified(DateUtil.getTime(rs.getTimestamp("gmt_modified")));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return review;
    }

    @Override
    public List<Review> list() {
        return list(0,getTotal());
    }

    @Override
    public List<Review> list(int start, int count) {
        String sql = "select * from review limit ?,?";
        List<Review> list = new ArrayList<>();
        try(Connection c = JdbcUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1,start);
            ps.setInt(2,count);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Review review = new Review();
                review.setId(rs.getInt("id"));
                review.setContent(rs.getString("content"));
                User user = new User();
                user.setName(rs.getString("nickname"));
                user.setId(rs.getInt("uid"));
                review.setUser(user);
                review.setProduct(productDAO.get(rs.getInt("pid")));
                review.setCreateDate(new Timestamp(rs.getTimestamp("createDate").getTime()));
                review.setGmtModified(DateUtil.getTime(rs.getTimestamp("gmt_modified")));
                list.add(review);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void updateTime(int id) {
        String sql = "update review set gmt_modified = now() where id = ?";
        try(Connection c = JdbcUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1,id);
            ps.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Review> list(int pid) {
        return list(pid,0,getTotal(pid));
    }

    @Override
    public List<Review> list(int pid, int start, int count) {
        String sql = "select * from review where pid=? limit ?,?";
        List<Review> list = new ArrayList<>();
        try(Connection c = JdbcUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(2,start);
            ps.setInt(3,count);
            ps.setInt(1,pid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Review review = new Review();
                review.setId(rs.getInt("id"));
                review.setContent(rs.getString("content"));
                User user = new User();
                user.setName(rs.getString("nickname"));
                user.setId(rs.getInt("uid"));
                review.setUser(user);
                review.setProduct(productDAO.get(pid));
                review.setCreateDate(new Timestamp(rs.getTimestamp("createDate").getTime()));
                review.setGmtModified(DateUtil.getTime(rs.getTimestamp("gmt_modified")));
                list.add(review);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int getTotal(int pid) {
        int count = 0;
        String sql = "select count(*) from review where pid="+pid;
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
}

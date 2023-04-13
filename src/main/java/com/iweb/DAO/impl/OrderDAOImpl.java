package com.iweb.DAO.impl;

import com.iweb.DAO.OrderDAO;
import com.iweb.DAO.UserDAO;
import com.iweb.entity.Order;
import com.iweb.util.JdbcUtil;
import com.iweb.util.DateUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Yang
 * @date 2023/4/1 1:11
 */
public class OrderDAOImpl implements OrderDAO {
    UserDAO userDAO = new UserDAOImpl();
    @Override
    public int getTotal() {
        int count = 0;
        String sql = "select count(*) from order_ where status != 'delete'";
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
    public void add(Order order) {
        String sql = "insert into order_(orderCode,address,post,receiver,mobile,userMessage,uid,status) values(?,?,?,?,?,?,?,?)";
        try(Connection c = JdbcUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)){
            ps.setString(1,order.getOrderCode());
            ps.setString(2,order.getAddress());
            ps.setString(3,order.getPost());
            ps.setString(4,order.getReceiver());
            ps.setString(5,order.getMobile());
            ps.setString(6,order.getUserMessage());
            ps.setInt(7,order.getUser().getId());
            ps.setString(8,order.getStatus());
            ps.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void update(Order order) {
        String sql = "update order_ set orderCode=?,address=?,post=?,receiver=?,mobile=?,userMessage=?," +
                "payDate=?,deliveryDate=?,confirmDate=?,uid=?,status=? where id=?";
        try(Connection c = JdbcUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)){
            ps.setString(1,order.getOrderCode());
            ps.setString(2,order.getAddress());
            ps.setString(3,order.getPost());
            ps.setString(4,order.getReceiver());
            ps.setString(5,order.getMobile());
            ps.setString(6,order.getUserMessage());
            if (null == order.getPayDate()){
                ps.setTimestamp(7,null);
            }else {
                ps.setTimestamp(7,new Timestamp(order.getPayDate().getTime()));
            }
            if (null == order.getDeliveryDate()){
                ps.setTimestamp(8,null);
            }else {
                ps.setTimestamp(8,new Timestamp(order.getDeliveryDate().getTime()));
            }
            if (null == order.getConfirmDate()){
                ps.setTimestamp(9,null);
            }else {
                ps.setTimestamp(9,new Timestamp(order.getConfirmDate().getTime()));
            }
            ps.setInt(10,order.getUser().getId());
            ps.setString(11,order.getStatus());
            ps.setInt(12,order.getId());
            ps.execute();
            updateTime(order.getId());
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "update order_ set status='delete' where id = ?";
        try(Connection c = JdbcUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1,id);
            ps.execute();
            updateTime(id);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public Order get(int id) {
        String sql = "select * from order_ where id = ? and status != 'delete'";
        Order order = null;
        try(Connection c = JdbcUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                order = new Order();
                order.setId(id);
                order.setOrderCode(rs.getString("orderCode"));
                order.setAddress(rs.getString("address"));
                order.setPost(rs.getString("post"));
                order.setReceiver(rs.getString("receiver"));
                order.setMobile(rs.getString("mobile"));
                order.setUserMessage(rs.getString("userMessage"));
                order.setCreateDate(DateUtil.getTime(rs.getTimestamp("createDate")));
                order.setPayDate(rs.getTimestamp("payDate"));
                order.setDeliveryDate(rs.getTimestamp("deliveryDate"));
                order.setConfirmDate(rs.getTimestamp("confirmDate"));
                order.setUser(userDAO.get(rs.getInt("uid")));
                order.setStatus(rs.getString("status"));
                order.setGmtModified(DateUtil.getTime(rs.getTimestamp("gmt_modified")));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return order;
    }

    @Override
    public List<Order> list() {
        return list(0,getTotal());
    }

    @Override
    public List<Order> list(int start, int count) {
        String sql = "select * from order_ where status != 'delete' limit ?,?";
        List<Order> orderList = new ArrayList<>();
        try(Connection c = JdbcUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)){
            ps.setInt(1,start);
            ps.setInt(2,count);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setOrderCode(rs.getString("orderCode"));
                order.setAddress(rs.getString("address"));
                order.setPost(rs.getString("post"));
                order.setReceiver(rs.getString("receiver"));
                order.setMobile(rs.getString("mobile"));
                order.setUserMessage(rs.getString("userMessage"));
                order.setCreateDate(DateUtil.getTime(rs.getTimestamp("createDate")));
                order.setPayDate(rs.getTimestamp("payDate"));
                order.setDeliveryDate(rs.getTimestamp("deliveryDate"));
                order.setConfirmDate(rs.getTimestamp("confirmDate"));
                order.setUser(userDAO.get(rs.getInt("uid")));
                order.setStatus(rs.getString("status"));
                order.setGmtModified(DateUtil.getTime(rs.getTimestamp("gmt_modified")));
                orderList.add(order);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return orderList;
    }

    @Override
    public void updateTime(int id) {
        String sql = "update order_ set gmt_modified = now() where id = ?";
        try(Connection c = JdbcUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1,id);
            ps.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public Order get(String orderCode) {
        String sql = "select * from order_ where orderCode = ? and status != 'delete'";
        Order order = null;
        try(Connection c = JdbcUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1,orderCode);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                order = new Order();
                order.setId(rs.getInt("id"));
                order.setOrderCode(orderCode);
                order.setAddress(rs.getString("address"));
                order.setPost(rs.getString("post"));
                order.setReceiver(rs.getString("receiver"));
                order.setMobile(rs.getString("mobile"));
                order.setUserMessage(rs.getString("userMessage"));
                order.setCreateDate(DateUtil.getTime(rs.getTimestamp("createDate")));
                order.setPayDate(rs.getTimestamp("payDate"));
                order.setDeliveryDate(rs.getTimestamp("deliveryDate"));
                order.setConfirmDate(rs.getTimestamp("confirmDate"));
                order.setUser(userDAO.get(rs.getInt("uid")));
                order.setStatus(rs.getString("status"));
                order.setGmtModified(DateUtil.getTime(rs.getTimestamp("gmt_modified")));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return order;
    }

    @Override
    public List<Order> list(int uid) {
        String sql = "select * from order_ where status != 'delete' and uid="+uid;
        List<Order> orderList = new ArrayList<>();
        try(Connection c = JdbcUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)){
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setOrderCode(rs.getString("orderCode"));
                order.setAddress(rs.getString("address"));
                order.setPost(rs.getString("post"));
                order.setReceiver(rs.getString("receiver"));
                order.setMobile(rs.getString("mobile"));
                order.setUserMessage(rs.getString("userMessage"));
                order.setCreateDate(DateUtil.getTime(rs.getTimestamp("createDate")));
                order.setPayDate(rs.getTimestamp("payDate"));
                order.setDeliveryDate(rs.getTimestamp("deliveryDate"));
                order.setConfirmDate(rs.getTimestamp("confirmDate"));
                order.setUser(userDAO.get(uid));
                order.setStatus(rs.getString("status"));
                order.setGmtModified(DateUtil.getTime(rs.getTimestamp("gmt_modified")));
                orderList.add(order);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return orderList;
    }
}

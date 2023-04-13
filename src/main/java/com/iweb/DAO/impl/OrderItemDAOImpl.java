package com.iweb.DAO.impl;

import com.iweb.DAO.OrderDAO;
import com.iweb.DAO.OrderItemDAO;
import com.iweb.DAO.ProductDAO;
import com.iweb.DAO.UserDAO;
import com.iweb.entity.OrderItem;
import com.iweb.util.JdbcUtil;
import com.iweb.util.DateUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Yang
 * @date 2023/4/1 17:47
 */
public class OrderItemDAOImpl implements OrderItemDAO {
    OrderDAO orderDAO = new OrderDAOImpl();
    UserDAO userDAO = new UserDAOImpl();
    ProductDAO productDAO = new ProductDAOImpl();
    @Override
    public int getTotal() {
        int count = 0;
        String sql = "select count(*) from orderitem";
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
    public void add(OrderItem orderItem) {
        String sql = "insert into orderitem(pid,oid,uid,`number`) values(?,?,?,?)";
        try(Connection c = JdbcUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)){
            ps.setInt(1,orderItem.getProduct().getId());
            if (null == orderItem.getOrder()){
                ps.setInt(2,-1);
            }else {
                ps.setInt(2,orderItem.getOrder().getId());
            }
            ps.setInt(3,orderItem.getUser().getId());
            ps.setInt(4,orderItem.getNumber());
            ps.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void update(OrderItem orderItem) {
        String sql = "update orderitem set pid=?,oid=?,uid=?,`number`=? where id=?";
        try(Connection c = JdbcUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)){
            ps.setInt(1,orderItem.getProduct().getId());
            if (orderItem.getOrder()==null){
                ps.setInt(2,-1);
            }else {
                ps.setInt(2,orderItem.getOrder().getId());
            }
            ps.setInt(3,orderItem.getUser().getId());
            ps.setInt(4,orderItem.getNumber());
            ps.setInt(5,orderItem.getId());
            ps.execute();
            updateTime(orderItem.getId());
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "delete from orderitem where id = ?";
        try(Connection c = JdbcUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1,id);
            ps.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public OrderItem get(int id) {
        String sql = "select * from orderitem where id = ?";
        OrderItem orderItem = null;
        try(Connection c = JdbcUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                orderItem = new OrderItem();
                orderItem.setId(id);
                orderItem.setProduct(productDAO.get(rs.getInt("pid")));
                orderItem.setOrder(orderDAO.get(rs.getInt("oid")));
                orderItem.setUser(userDAO.get(rs.getInt("uid")));
                orderItem.setNumber(rs.getInt("number"));
                orderItem.setGmtCreate(DateUtil.getTime(rs.getTimestamp("gmt_create")));
                orderItem.setGmtModified(DateUtil.getTime(rs.getTimestamp("gmt_modified")));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return orderItem;
    }

    @Override
    public List<OrderItem> list() {
        return list(0,getTotal());
    }

    @Override
    public List<OrderItem> list(int start, int count) {
        String sql = "select * from orderitem limit ?,?";
        List<OrderItem> list = new ArrayList<>();
        try(Connection c = JdbcUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1,start);
            ps.setInt(2,count);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                OrderItem orderItem = new OrderItem();
                orderItem.setId(rs.getInt("id"));
                orderItem.setProduct(productDAO.get(rs.getInt("pid")));
                orderItem.setOrder(orderDAO.get(rs.getInt("oid")));
                orderItem.setUser(userDAO.get(rs.getInt("uid")));
                orderItem.setNumber(rs.getInt("number"));
                orderItem.setGmtCreate(DateUtil.getTime(rs.getTimestamp("gmt_create")));
                orderItem.setGmtModified(DateUtil.getTime(rs.getTimestamp("gmt_modified")));
                list.add(orderItem);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void updateTime(int id) {
        String sql = "update orderitem set gmt_modified = now() where id = ?";
        try(Connection c = JdbcUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1,id);
            ps.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<OrderItem> list(int uid) {
        return list(uid,0,getTotal(uid));
    }

    @Override
    public List<OrderItem> list(int uid, int start, int count) {
        String sql = "select * from orderitem where oid!=-1 and uid=? limit ?,?";
        List<OrderItem> list = new ArrayList<>();
        try(Connection c = JdbcUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(2,start);
            ps.setInt(3,count);
            ps.setInt(1,uid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                OrderItem orderItem = new OrderItem();
                orderItem.setId(rs.getInt("id"));
                orderItem.setProduct(productDAO.get(rs.getInt("pid")));
                orderItem.setOrder(orderDAO.get(rs.getInt("oid")));
                orderItem.setUser(userDAO.get(uid));
                orderItem.setNumber(rs.getInt("number"));
                orderItem.setGmtCreate(DateUtil.getTime(rs.getTimestamp("gmt_create")));
                orderItem.setGmtModified(DateUtil.getTime(rs.getTimestamp("gmt_modified")));
                list.add(orderItem);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int getTotal(int uid) {
        int count = 0;
        String sql = "select count(*) from orderitem where oid!=-1 and uid="+uid;
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
    public int getShoppingCartsNum(int uid) {
        int count = 0;
        String sql = "select count(*) from orderitem where oid=-1 and uid="+uid;
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
    public int getSaleCount(int pid) {
        int sale = 0;
        String sql = "select sum(`number`) from orderitem where oid !=-1 and pid="+pid;
        try(Connection c = JdbcUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)){
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                sale = rs.getInt(1);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return sale;
    }

    @Override
    public OrderItem get(int pid, int oid) {
        String sql = "select * from orderitem where oid =-1 and pid="+pid;
        OrderItem orderItem = null;
        try(Connection c = JdbcUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)){
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                orderItem = new OrderItem();
                orderItem.setId(rs.getInt("id"));
                orderItem.setProduct(productDAO.get(pid));
                orderItem.setUser(userDAO.get(rs.getInt("uid")));
                orderItem.setNumber(rs.getInt("number"));
                orderItem.setGmtCreate(DateUtil.getTime(rs.getTimestamp("gmt_create")));
                orderItem.setGmtModified(DateUtil.getTime(rs.getTimestamp("gmt_modified")));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return orderItem;
    }

    @Override
    public List<OrderItem> listCar(int uid) {
        String sql = "select * from orderitem where oid=-1 and uid="+uid;
        List<OrderItem> list = new ArrayList<>();
        try(Connection c = JdbcUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                OrderItem orderItem = new OrderItem();
                orderItem.setId(rs.getInt("id"));
                orderItem.setProduct(productDAO.get(rs.getInt("pid")));
                orderItem.setOrder(orderDAO.get(rs.getInt("oid")));
                orderItem.setUser(userDAO.get(uid));
                orderItem.setNumber(rs.getInt("number"));
                orderItem.setGmtCreate(DateUtil.getTime(rs.getTimestamp("gmt_create")));
                orderItem.setGmtModified(DateUtil.getTime(rs.getTimestamp("gmt_modified")));
                list.add(orderItem);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<OrderItem> listOrderItem(int oid) {
        String sql = "select * from orderitem where oid="+oid;
        List<OrderItem> list = new ArrayList<>();
        try(Connection c = JdbcUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                OrderItem orderItem = new OrderItem();
                orderItem.setId(rs.getInt("id"));
                orderItem.setProduct(productDAO.get(rs.getInt("pid")));
                orderItem.setOrder(orderDAO.get(rs.getInt("oid")));
                orderItem.setUser(userDAO.get(rs.getInt("uid")));
                orderItem.setNumber(rs.getInt("number"));
                orderItem.setGmtCreate(DateUtil.getTime(rs.getTimestamp("gmt_create")));
                orderItem.setGmtModified(DateUtil.getTime(rs.getTimestamp("gmt_modified")));
                list.add(orderItem);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }
}

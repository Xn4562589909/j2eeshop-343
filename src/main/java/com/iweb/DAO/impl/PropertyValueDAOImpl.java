package com.iweb.DAO.impl;

import com.iweb.DAO.ProductDAO;
import com.iweb.DAO.PropertyDAO;
import com.iweb.DAO.PropertyValueDAO;
import com.iweb.entity.PropertyValue;
import com.iweb.util.JdbcUtil;
import com.iweb.util.DateUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Yang
 * @date 2023/4/1 21:35
 */
public class PropertyValueDAOImpl implements PropertyValueDAO {
    PropertyDAO propertyDAO = new PropertyDAOImpl();
    ProductDAO productDAO = new ProductDAOImpl();
    @Override
    public int getTotal() {
        int count = 0;
        String sql = "select count(*) from propertyvalue";
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
    public void add(PropertyValue propertyValue) {
        String sql = "insert into propertyvalue(pid,`ptid`,`value`) values(?,?,?)";
        try(Connection c = JdbcUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)){
            ps.setString(3,propertyValue.getValue());
            ps.setInt(1,propertyValue.getProduct().getId());
            ps.setInt(2,propertyValue.getProperty().getId());
            ps.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void update(PropertyValue propertyValue) {
        String sql = "update propertyvalue set pid=?,ptid=?,`value`=? where id=?";
        try(Connection c = JdbcUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)){
            ps.setInt(1,propertyValue.getProduct().getId());
            ps.setInt(2,propertyValue.getProperty().getId());
            ps.setString(3,propertyValue.getValue());
            ps.setInt(4,propertyValue.getId());
            ps.execute();
            updateTime(propertyValue.getId());
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "delete from propertyvalue where id = ?";
        try(Connection c = JdbcUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1,id);
            ps.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public PropertyValue get(int id) {
        String sql = "select * from propertyvalue where id = ?";
        PropertyValue propertyValue = null;
        try(Connection c = JdbcUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                propertyValue = new PropertyValue();
                propertyValue.setId(id);
                propertyValue.setProduct(productDAO.get(rs.getInt("pid")));
                propertyValue.setProperty(propertyDAO.get(rs.getInt("ptid")));
                propertyValue.setValue(rs.getString("value"));
                propertyValue.setGmtCreate(DateUtil.getTime(rs.getTimestamp("gmt_create")));
                propertyValue.setGmtModified(DateUtil.getTime(rs.getTimestamp("gmt_modified")));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return propertyValue;
    }

    @Override
    public List<PropertyValue> list() {
        return list(0,getTotal());
    }

    @Override
    public List<PropertyValue> list(int start, int count) {
        String sql = "select * from propertyvalue limit ?,?";
        List<PropertyValue> list = new ArrayList<>();
        try(Connection c = JdbcUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1,start);
            ps.setInt(2,count);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                PropertyValue propertyValue = new PropertyValue();
                propertyValue.setId(rs.getInt("id"));
                propertyValue.setProduct(productDAO.get(rs.getInt("pid")));
                propertyValue.setProperty(propertyDAO.get(rs.getInt("ptid")));
                propertyValue.setValue(rs.getString("value"));
                propertyValue.setGmtCreate(DateUtil.getTime(rs.getTimestamp("gmt_create")));
                propertyValue.setGmtModified(DateUtil.getTime(rs.getTimestamp("gmt_modified")));
                list.add(propertyValue);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void updateTime(int id) {
        String sql = "update propertyvalue set gmt_modified = now() where id = ?";
        try(Connection c = JdbcUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1,id);
            ps.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<PropertyValue> list(int pid) {
        return list(pid,0,getTotal(pid));
    }

    @Override
    public List<PropertyValue> list(int pid, int start, int count) {
        String sql = "select * from propertyvalue where pid=? limit ?,?";
        List<PropertyValue> list = new ArrayList<>();
        try(Connection c = JdbcUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(2,start);
            ps.setInt(3,count);
            ps.setInt(1,pid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                PropertyValue propertyValue = new PropertyValue();
                propertyValue.setId(rs.getInt("id"));
                propertyValue.setProduct(productDAO.get(pid));
                propertyValue.setProperty(propertyDAO.get(rs.getInt("ptid")));
                propertyValue.setValue(rs.getString("value"));
                propertyValue.setGmtCreate(DateUtil.getTime(rs.getTimestamp("gmt_create")));
                propertyValue.setGmtModified(DateUtil.getTime(rs.getTimestamp("gmt_modified")));
                list.add(propertyValue);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int getTotal(int pid) {
        int count = 0;
        String sql = "select count(*) from propertyvalue where pid=?";
        try(Connection c = JdbcUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)){
            ps.setInt(1,pid);
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

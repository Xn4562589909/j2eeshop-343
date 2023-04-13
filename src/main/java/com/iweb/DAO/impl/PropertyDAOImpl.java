package com.iweb.DAO.impl;

import com.iweb.DAO.CategoryDAO;
import com.iweb.DAO.PropertyDAO;
import com.iweb.entity.Property;
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
 * @date 2023/4/1 20:03
 */
public class PropertyDAOImpl implements PropertyDAO {
    CategoryDAO categoryDAO = new CategoryDAOImpl();
    @Override
    public int getTotal() {
        int count = 0;
        String sql = "select count(*) from property";
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
    public void add(Property property) {
        String sql = "insert into property(cid,`name`) values(?,?)";
        try(Connection c = JdbcUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)){
            ps.setString(2,property.getName());
            ps.setInt(1,property.getCategory().getId());
            ps.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void update(Property property) {
        String sql = "update property set cid=?,`name`=? where id=?";
        try(Connection c = JdbcUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)){
            ps.setInt(1,property.getCategory().getId());
            ps.setString(2,property.getName());
            ps.setInt(3,property.getId());
            ps.execute();
            updateTime(property.getId());
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "delete from property where id = ?";
        try(Connection c = JdbcUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1,id);
            ps.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public Property get(int id) {
        String sql = "select * from property where id = ?";
        Property property = null;
        try(Connection c = JdbcUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                property = new Property();
                property.setId(id);
                property.setCategory(categoryDAO.get(rs.getInt("cid")));
                property.setName(rs.getString("name"));
                property.setGmtCreate(DateUtil.getTime(rs.getTimestamp("gmt_create")));
                property.setGmtModified(DateUtil.getTime(rs.getTimestamp("gmt_modified")));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return property;
    }

    @Override
    public List<Property> list() {
        return list(0,getTotal());
    }

    @Override
    public List<Property> list(int start, int count) {
        String sql = "select * from property limit ?,?";
        List<Property> list = new ArrayList<>();
        try(Connection c = JdbcUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1,start);
            ps.setInt(2,count);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Property property = new Property();
                property.setId(rs.getInt("id"));
                property.setCategory(categoryDAO.get(rs.getInt("cid")));
                property.setName(rs.getString("name"));
                property.setGmtCreate(DateUtil.getTime(rs.getTimestamp("gmt_create")));
                property.setGmtModified(DateUtil.getTime(rs.getTimestamp("gmt_modified")));
                list.add(property);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void updateTime(int id) {
        String sql = "update property set gmt_modified = now() where id = ?";
        try(Connection c = JdbcUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1,id);
            ps.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Property> list(int cid) {
        return list(cid,0,getTotal(cid));
    }

    @Override
    public List<Property> list(int cid, int start, int count) {
        String sql = "select * from property where cid=? limit ?,?";
        List<Property> list = new ArrayList<>();
        try(Connection c = JdbcUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(2,start);
            ps.setInt(3,count);
            ps.setInt(1,cid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Property property = new Property();
                property.setId(rs.getInt("id"));
                property.setCategory(categoryDAO.get(cid));
                property.setName(rs.getString("name"));
                property.setGmtCreate(DateUtil.getTime(rs.getTimestamp("gmt_create")));
                property.setGmtModified(DateUtil.getTime(rs.getTimestamp("gmt_modified")));
                list.add(property);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int getTotal(int cid) {
        int count = 0;
        String sql = "select count(*) from property where cid = ?";
        try(Connection c = JdbcUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)){
            ps.setInt(1,cid);
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

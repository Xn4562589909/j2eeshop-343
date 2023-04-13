package com.iweb.DAO.impl;

import com.iweb.DAO.CategoryDAO;
import com.iweb.DAO.ProductDAO;
import com.iweb.entity.Product;
import com.iweb.util.JdbcUtil;
import com.iweb.util.DateUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Yang
 * @date 2023/4/1 18:21
 */
public class ProductDAOImpl implements ProductDAO {
    CategoryDAO categoryDAO = new CategoryDAOImpl();
    @Override
    public int getTotal() {
        int count = 0;
        String sql = "select count(*) from product";
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
    public void add(Product product) {
        String sql = "insert into product(`name`,subTitle,originalPrice,promotePrice,stock,cid,createDate) values(?,?,?,?,?,?,?)";
        try(Connection c = JdbcUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)){
            ps.setString(1,product.getName());
            ps.setString(2,product.getSubTitle());
            ps.setBigDecimal(3,product.getOriginalPrice());
            ps.setBigDecimal(4,product.getPromotePrice());
            ps.setInt(5,product.getStock());
            ps.setInt(6,product.getCategory().getId());
            ps.setTimestamp(7,new Timestamp(System.currentTimeMillis()));
            ps.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void update(Product product) {
        String sql = "update product set `name`=?,subTitle=?,originalPrice=?,promotePrice=?,stock=?,cid=? where id=?";
        try(Connection c = JdbcUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)){
            ps.setString(1,product.getName());
            ps.setString(2,product.getSubTitle());
            ps.setBigDecimal(3,product.getOriginalPrice());
            ps.setBigDecimal(4,product.getPromotePrice());
            ps.setInt(5,product.getStock());
            ps.setInt(6,product.getCategory().getId());
            ps.setInt(7,product.getId());
            ps.execute();
            updateTime(product.getId());
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "delete from product where id = ?";
        try(Connection c = JdbcUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1,id);
            ps.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public Product get(int id) {
        String sql = "select * from product where id = ?";
        Product product = null;
        try(Connection c = JdbcUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                product = new Product();
                product.setId(id);
                product.setName(rs.getString("name"));
                product.setSubTitle(rs.getString("subTitle"));
                product.setOriginalPrice(rs.getBigDecimal("originalPrice"));
                product.setPromotePrice(rs.getBigDecimal("promotePrice"));
                product.setStock(rs.getInt("stock"));
                product.setCategory(categoryDAO.get(rs.getInt("cid")));
                product.setCreateDate(DateUtil.getTime(rs.getTimestamp("createDate")));
                product.setGmtModified(DateUtil.getTime(rs.getTimestamp("gmt_modified")));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public List<Product> list() {
        return list(0,getTotal());
    }

    @Override
    public List<Product> list(int start, int count) {
        String sql = "select * from product limit ?,?";
        List<Product> list = new ArrayList<>();
        try(Connection c = JdbcUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1,start);
            ps.setInt(2,count);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setSubTitle(rs.getString("subTitle"));
                product.setOriginalPrice(rs.getBigDecimal("originalPrice"));
                product.setPromotePrice(rs.getBigDecimal("promotePrice"));
                product.setStock(rs.getInt("stock"));
                product.setCategory(categoryDAO.get(rs.getInt("cid")));
                product.setCreateDate(DateUtil.getTime(rs.getTimestamp("createDate")));
                product.setGmtModified(DateUtil.getTime(rs.getTimestamp("gmt_modified")));
                list.add(product);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void updateTime(int id) {
        String sql = "update product set gmt_modified = now() where id = ?";
        try(Connection c = JdbcUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1,id);
            ps.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Product> list(int cid) {
        return list(cid,0,getTotal(cid));
    }

    @Override
    public List<Product> list(int cid, int start, int count) {
        String sql = "select * from product where cid=? limit ?,?";
        List<Product> list = new ArrayList<>();
        try(Connection c = JdbcUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(2,start);
            ps.setInt(3,count);
            ps.setInt(1,cid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setSubTitle(rs.getString("subTitle"));
                product.setOriginalPrice(rs.getBigDecimal("originalPrice"));
                product.setPromotePrice(rs.getBigDecimal("promotePrice"));
                product.setStock(rs.getInt("stock"));
                product.setCategory(categoryDAO.get(cid));
                product.setCreateDate(DateUtil.getTime(rs.getTimestamp("createDate")));
                product.setGmtModified(DateUtil.getTime(rs.getTimestamp("gmt_modified")));
                list.add(product);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int getTotal(int cid) {
        int count = 0;
        String sql = "select count(*) from product where cid = ?";
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

    @Override
    public List<Product> list(String name) {
        String sql = "select * from product where `name` like concat('%',?,'%')";
        List<Product> list = new ArrayList<>();
        try(Connection c = JdbcUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1,name);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setSubTitle(rs.getString("subTitle"));
                product.setOriginalPrice(rs.getBigDecimal("originalPrice"));
                product.setPromotePrice(rs.getBigDecimal("promotePrice"));
                product.setStock(rs.getInt("stock"));
                product.setCategory(categoryDAO.get(rs.getInt("cid")));
                product.setCreateDate(DateUtil.getTime(rs.getTimestamp("createDate")));
                product.setGmtModified(DateUtil.getTime(rs.getTimestamp("gmt_modified")));
                list.add(product);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }
}

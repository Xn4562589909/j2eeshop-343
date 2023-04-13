package com.iweb.DAO.impl;

import com.iweb.DAO.ProductDAO;
import com.iweb.DAO.ProductImageDAO;
import com.iweb.entity.ProductImage;
import com.iweb.util.JdbcUtil;
import com.iweb.util.DateUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**5
 * @author Yang
 * @date 2023/4/1 19:48
 */
public class ProductImageDAOImpl implements ProductImageDAO {
    ProductDAO productDAO = new ProductDAOImpl();
    @Override
    public int getTotal() {
        int count = 0;
        String sql = "select count(*) from img";
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
    public void add(ProductImage productImage) {
        String sql = "insert into img(url,pid) values(?,?)";
        try(Connection c = JdbcUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)){
            ps.setString(1,productImage.getUrl());
            ps.setInt(2,productImage.getP().getId());
            ps.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void update(ProductImage productImage) {
        String sql = "update img set url=?,pid=? where id=?";
        try(Connection c = JdbcUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)){
            ps.setString(1,productImage.getUrl());
            ps.setInt(2,productImage.getP().getId());
            ps.setInt(3,productImage.getId());
            ps.execute();
            updateTime(productImage.getId());
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "delete from img where id = ?";
        try(Connection c = JdbcUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1,id);
            ps.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public ProductImage get(int id) {
        String sql = "select * from img where id = ?";
        ProductImage productImage = null;
        try(Connection c = JdbcUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                productImage = new ProductImage();
                productImage.setId(id);
                productImage.setUrl(rs.getString("url"));
                productImage.setP(productDAO.get(rs.getInt("pid")));
                productImage.setGmtCreate(DateUtil.getTime(rs.getTimestamp("gmt_create")));
                productImage.setGmtModified(DateUtil.getTime(rs.getTimestamp("gmt_modified")));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return productImage;
    }

    @Override
    public List<ProductImage> list() {
        return list(0,getTotal());
    }

    @Override
    public List<ProductImage> list(int start, int count) {
        String sql = "select * from img limit ?,?";
        List<ProductImage> list = new ArrayList<>();
        try(Connection c = JdbcUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1,start);
            ps.setInt(2,count);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                ProductImage productImage = new ProductImage();
                productImage.setId(rs.getInt("id"));
                productImage.setUrl(rs.getString("url"));
                productImage.setP(productDAO.get(rs.getInt("pid")));
                productImage.setGmtCreate(DateUtil.getTime(rs.getTimestamp("gmt_create")));
                productImage.setGmtModified(DateUtil.getTime(rs.getTimestamp("gmt_modified")));
                list.add(productImage);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void updateTime(int id) {
        String sql = "update img set gmt_modified = now() where id = ?";
        try(Connection c = JdbcUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1,id);
            ps.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<ProductImage> list(int pid) {
        return list(pid,0,getTotal(pid));
    }

    @Override
    public List<ProductImage> list(int pid, int start, int count) {
        String sql = "select * from img where pid = ? limit ?,?";
        List<ProductImage> list = new ArrayList<>();
        try(Connection c = JdbcUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(2,start);
            ps.setInt(3,count);
            ps.setInt(1,pid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                ProductImage productImage = new ProductImage();
                productImage.setId(rs.getInt("id"));
                productImage.setUrl(rs.getString("url"));
                productImage.setP(productDAO.get(pid));
                productImage.setGmtCreate(DateUtil.getTime(rs.getTimestamp("gmt_create")));
                productImage.setGmtModified(DateUtil.getTime(rs.getTimestamp("gmt_modified")));
                list.add(productImage);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int getTotal(int pid) {
        int count = 0;
        String sql = "select count(*) from img where pid="+pid;
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

package com.iweb.service.impl;

import com.iweb.DAO.PropertyDAO;
import com.iweb.DAO.PropertyValueDAO;
import com.iweb.DAO.impl.PropertyDAOImpl;
import com.iweb.DAO.impl.PropertyValueDAOImpl;
import com.iweb.entity.Property;
import com.iweb.entity.PropertyValue;
import com.iweb.service.PropertyService;

import java.util.List;

/**
 * @author Yang
 * @date 2023/4/3 21:41
 */
public class PropertyServiceImpl implements PropertyService {
    PropertyDAO propertyDAO = new PropertyDAOImpl();
    PropertyValueDAO propertyValueDAO = new PropertyValueDAOImpl();
    @Override
    public List<Property> list(int cid) {
        //调用DAO方法获取数据
        return propertyDAO.list(cid);
    }

    @Override
    public void add(Property property) {
        propertyDAO.add(property);
    }

    @Override
    public Property edit(int id) {
        return  propertyDAO.get(id);
    }

    @Override
    public void update(Property property) {
        propertyDAO.update(property);
    }

    @Override
    public void delete(int id) {
        propertyDAO.delete(id);
    }

    @Override
    public List<Property> list() {
        return propertyDAO.list();
    }

    @Override
    public Property get(int id) {
        return propertyDAO.get(id);
    }

}

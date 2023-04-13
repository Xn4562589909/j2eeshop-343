package com.iweb.service.impl;

import com.iweb.DAO.PropertyValueDAO;
import com.iweb.DAO.impl.PropertyValueDAOImpl;
import com.iweb.entity.Property;
import com.iweb.entity.PropertyValue;
import com.iweb.service.PropertyValueService;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Yang
 * @date 2023/4/4 2:28
 */
public class PropertyValueServiceImpl implements PropertyValueService {
    PropertyValueDAO propertyValueDAO = new PropertyValueDAOImpl();
    @Override
    public List<PropertyValue> list(int pid) {
        List<PropertyValue> pvs = propertyValueDAO.list(pid);
        return pvs;
    }

    @Override
    public PropertyValue get(int id) {
        return propertyValueDAO.get(id);
    }

    @Override
    public void update(PropertyValue pv) {
        propertyValueDAO.update(pv);
    }

    @Override
    public void add(PropertyValue pv) {
        propertyValueDAO.add(pv);
    }

    @Override
    public List<Property> listNotAddPt(List<Property> properties, List<PropertyValue> propertyValues) {
        List<Property> properties1 = new ArrayList<>();
        for (PropertyValue pv:propertyValues) {
            properties1.add(pv.getProperty());
        }
        properties.removeAll(properties1);
        return properties;
    }

    @Override
    public void delete(int id) {
        propertyValueDAO.delete(id);
    }
}

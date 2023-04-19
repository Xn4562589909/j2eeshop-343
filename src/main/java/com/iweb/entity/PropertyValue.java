package com.iweb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PropertyValue {
    private String value;
    private Product product;
    private Property property;
    private int id;
    private String gmtCreate;
    private String gmtModified;
}

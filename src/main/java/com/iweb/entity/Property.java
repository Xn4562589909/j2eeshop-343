package com.iweb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Property {
    private int id;
    private String name;
    private Category category;
    private int cid;
    private String gmtCreate;
    private String gmtModified;
}

package com.iweb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author GUAN
 * @date 2022/8/15 13:54
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductImage {
    private int id;
    private String url;
    private Product p;
    private int pid;
    private String gmtCreate;
    private String gmtModified;
}

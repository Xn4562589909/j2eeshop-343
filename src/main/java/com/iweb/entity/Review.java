package com.iweb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    private String content;
    private String createDate;
    private User user;
    private int uid;
    private Product product;
    private int pid;
    private int id;
    private String gmtModified;
}

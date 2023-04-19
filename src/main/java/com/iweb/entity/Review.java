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
    private String nickname;
    private Product product;
    private int id;
    private String gmtModified;
}

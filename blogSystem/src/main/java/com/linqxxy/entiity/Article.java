package com.linqxxy.entiity;

import lombok.Data;

import java.util.Date;

@Data
public class Article {
    private Integer id;
    private String title;
    private String content;
    private Integer User_id;
    private String userAccout;
    private Date createTime;

}

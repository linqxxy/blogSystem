package com.linqxxy;

import com.entiity.Article;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestJackson {
    //测试Json与Obj格式互转
    @Test
    public void testJackson(){
        List<Article> articles=new ArrayList<>();
        Article article=new Article();
        article.setId(1);
        article.setTitle("我的博客");
        article.setContent("第一篇博客");
        article.setCreate_time(new Date());
        articles.add(article);
        ObjectMapper objectMapper=new ObjectMapper();
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        try {
            String rs=objectMapper.writeValueAsString(articles);
            System.out.println(rs);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}

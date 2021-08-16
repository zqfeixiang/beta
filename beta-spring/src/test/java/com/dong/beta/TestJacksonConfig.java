package com.dong.beta;

import com.dong.beta.entity.Article;
import com.dong.beta.entity.Book;
import com.dong.beta.entity.DataListDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TestJacksonConfig {

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void testJackson(){
        Article book = new Article();
        try {
            String s = objectMapper.writeValueAsString(book);
            System.out.println("===s: " + s);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}

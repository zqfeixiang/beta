package com.dong.beta.config;

import com.dong.beta.entity.Article;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

import lombok.ToString;

@EnableConfigurationProperties
@Configuration
@ToString
public class ArticleConfig {

    private Integer id;
    private String author;
    private String title;
    private String content;

    private List<Article> articles;

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

package com.dong.beta.entity;

import io.searchbox.annotations.JestId;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Article {

    @JestId
    private Integer id;
    private String author;
    private String title;
    private String content;

}

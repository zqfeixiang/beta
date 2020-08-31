package com.dong.beta.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName="books")
public class Book {
    @Id
    private String id;

    @Field(type = FieldType.String)
    private String name;

    @Field(type = FieldType.String)
    private String summary;

    @Field(type = FieldType.Integer)
    private Integer price;

}
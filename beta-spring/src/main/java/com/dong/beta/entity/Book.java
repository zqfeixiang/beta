package com.dong.beta.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName="books")
@Builder
public class Book implements Serializable {
    private static final long serialVersionUID = 1901742110813577310L;
    @Id
    private String id;

    private String name;

    private String summary;

    private Integer price;

}
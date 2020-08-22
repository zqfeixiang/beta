package com.dong.beta.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataListDTO<T> {
    private List<T> dataList;
    private Integer currPage;
    private Integer size;

    public DataListDTO(List<T> dataList) {
        this.dataList = dataList;
    }
}

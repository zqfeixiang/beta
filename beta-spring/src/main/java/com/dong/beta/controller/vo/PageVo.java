package com.dong.beta.controller.vo;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PageVo<T> {
    private Integer currPage;

    private Integer pageSize;

    private Long total;

    private List<T> data;

    public PageVo(List<T> list, Integer currPage, Integer pageSize){
        if (CollectionUtils.isEmpty(list)){
            this.currPage = currPage;
            this.pageSize = pageSize;
            this.data = Lists.newArrayList();
            this.total = 0l;
        }
        this.total = Long.valueOf(list.size());
        int totalPageNum = (list.size() - 1 ) / pageSize + 1;
        if (currPage > totalPageNum){
            currPage = totalPageNum;
        }else if (currPage < 1){
            currPage = 1;
        }
        int fromIndex = (currPage - 1) * pageSize;
        int toIndex = (fromIndex + pageSize) > total.intValue() ? total.intValue() : (fromIndex + pageSize);
        this.data = list.subList(fromIndex, toIndex);
        this.currPage = currPage;
        this.pageSize = pageSize;
        this.total = total;
    }



}

package com.yc.learning.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

//分页的实体
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PageDomain<T> {
    private Integer page = 0;   //当前第几页
    private Integer pageSize = 2;  //每页多少条
    private Long total = 0L;  //总记录数
    private Integer totalPages;

    private List<T> data;   //当前查询出来的数据集


}

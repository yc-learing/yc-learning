package com.yc.learning.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CourseDomain extends PageDomain{
    private Integer cid;
    private String coursename;
    private String describe;
    private String pic;
    private Integer status;


}

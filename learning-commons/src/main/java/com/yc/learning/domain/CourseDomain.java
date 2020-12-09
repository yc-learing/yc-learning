package com.yc.learning.domain;

import lombok.Data;

@Data
public class CourseDomain extends PageDomain{
    private Integer cid;
    private String coursename;
    private String describe;
    private String pic;
    private Integer status;

    public CourseDomain(Integer cid, String coursename, String describe, String pic, Integer status) {
        this.cid = cid;
        this.coursename = coursename;
        this.describe = describe;
        this.pic = pic;
        this.status = status;
    }

    public CourseDomain() {
    }
}

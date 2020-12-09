package com.yc.learning.domain;

import lombok.Data;

import java.util.Date;

@Data
public class ExamDomain extends PageDomain{
    private Integer exid;
    private String ename;
    private String eids;
    private Date createtime;
    private Integer examtime;
    private Integer classes;
    private String aname;
    private Integer status;
    private String temp;

    public ExamDomain(Integer exid, String ename, String eids, Date createtime, Integer examtime, Integer classes, String aname, Integer status) {
        this.exid = exid;
        this.ename = ename;
        this.eids = eids;
        this.createtime = createtime;
        this.examtime = examtime;
        this.classes = classes;
        this.aname = aname;
        this.status = status;
    }

    public ExamDomain() {
    }
}

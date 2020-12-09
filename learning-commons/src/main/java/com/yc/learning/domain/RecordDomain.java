package com.yc.learning.domain;

import lombok.Data;

@Data
public class RecordDomain extends PageDomain{
    private Integer rid;
    private Integer exid;
    private Integer uid;
    private String useranswer;
    private Double grade;

    public RecordDomain(Integer rid, Integer exid, Integer uid, String useranswer, Double grade) {
        this.rid = rid;
        this.exid = exid;
        this.uid = uid;
        this.useranswer = useranswer;
        this.grade = grade;
    }

    public RecordDomain() {
    }
}

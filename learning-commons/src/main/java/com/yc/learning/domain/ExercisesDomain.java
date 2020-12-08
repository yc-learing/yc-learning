package com.yc.learning.domain;

import lombok.Data;

import java.util.Date;

@Data
public class ExercisesDomain extends PageDomain{
    private Integer eid;
    private Integer cid;
    private Integer type;
    private String content;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String answer;
    private String analysis;
    private Date inputtime;
    private String aname;

    public ExercisesDomain(Integer eid, Integer cid, Integer type, String content, String optionA, String optionB, String optionC, String optionD, String answer, String analysis, Date inputtime, String aname) {
        this.eid = eid;
        this.cid = cid;
        this.type = type;
        this.content = content;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.answer = answer;
        this.analysis = analysis;
        this.inputtime = inputtime;
        this.aname = aname;
    }

    public ExercisesDomain() {
    }
}

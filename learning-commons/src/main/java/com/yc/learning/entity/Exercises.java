package com.yc.learning.entity;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "exercises")
public class Exercises {
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
}

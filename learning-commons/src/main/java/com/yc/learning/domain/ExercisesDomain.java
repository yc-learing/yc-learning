package com.yc.learning.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Id;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class ExercisesDomain extends PageDomain{
    @Id
    private Integer eid;
    private Integer chid;
    private Integer type;
    private String content;
    private String optiona;
    private String optionb;
    private String optionc;
    private String optiond;
    private String answer;
    private String analysis;
    private Date inputtime;
    private String aname;
    private String temp;


}

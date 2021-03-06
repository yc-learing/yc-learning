package com.yc.learning.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Table(name = "exercises")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Exercises implements Serializable {
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

package com.yc.learning.view;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ExercisesVoChpaterCourse{
    @Id
    private Integer eid;//试题id
    private Integer chid;//章节id
    private Integer cid;//课程id
    private String coursename;//课程名
    private String cname;//章节名
    private Integer type;
    private String content;
    private String optiona;
    private String optionb;
    private String optionc;
    private String optiond;
    private String answer;
    private String analysis;
    private Integer status;
}

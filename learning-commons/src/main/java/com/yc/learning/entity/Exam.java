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
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Table(name = "exam")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Exam implements Serializable {
    @Id
    private Integer exid;
    private String ename;
    private String eids;
    private Date createtime;
    private Integer examtime;
    private Integer classes;
    private String aname;
    private Integer status;
    private String temp;
    private List<Exercises> exercises;//一套试卷包含多个试题

}

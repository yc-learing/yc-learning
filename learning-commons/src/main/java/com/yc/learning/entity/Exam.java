package com.yc.learning.entity;

import lombok.Data;

import javax.persistence.Table;
import java.util.Date;
import javax.persistence.Id;

@Data
@Table(name = "exam")
public class Exam {
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

}

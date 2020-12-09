package com.yc.learning.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
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

package com.yc.learning.entity;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "course")
public class Course {
    @Id
    private Integer cid;
    private String coursename;
    private String describe;
    private String pic;
    private Integer status;
}

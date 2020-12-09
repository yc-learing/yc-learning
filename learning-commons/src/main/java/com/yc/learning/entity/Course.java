package com.yc.learning.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
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

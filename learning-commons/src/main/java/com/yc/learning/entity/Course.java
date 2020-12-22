package com.yc.learning.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Table(name = "course")
public class Course implements Serializable {
    @Id
    private Integer cid;
    private String coursename;
    private String descr;
    private String pic;
    private Integer status;
    private List<Chapter> chapters;
}

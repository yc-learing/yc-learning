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
@Table(name = "chapter")
public class Chapter implements Serializable {
    @Id
    private Integer chid;
    private String cname;
    private Integer cid;
    private String temp;
    private Course course;//关联课程
    private List<Exercises> exercises;//关联试题
}

package com.yc.learning.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "chapter")
public class Chapter {
    @Id
    private Integer chid;
    private String cname;
    private Integer pchid;
    private Integer cid;
    private String temp;
}

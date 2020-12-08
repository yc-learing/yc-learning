package com.yc.learning.entity;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "chapter")
public class Chapter {
    @Id
    private Integer chid;
    private String cname;
    private Integer pchid;
    private Integer cid;
}

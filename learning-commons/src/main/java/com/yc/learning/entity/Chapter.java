package com.yc.learning.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Table(name = "chapter")
@JsonIgnoreProperties(ignoreUnknown = true)

public class Chapter implements Serializable {
    @Id
    private Integer chid;
    private String cname;
    private Integer cid;
    private String temp;
    private Course course;//关联课程
}

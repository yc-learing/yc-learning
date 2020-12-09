package com.yc.learning.entity;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "record")
public class Record {
    @Id
    private Integer rid;
    private Integer exid;
    private Integer uid;
    private String useranswer;
    private Double grade;
    private String temp;

}

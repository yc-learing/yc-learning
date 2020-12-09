package com.yc.learning.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RecordDomain extends PageDomain{
    private Integer rid;
    private Integer exid;
    private Integer uid;
    private String useranswer;
    private Double grade;
    private String temp;

}

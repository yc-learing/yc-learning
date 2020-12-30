package com.yc.learning.view;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RecordVoUserExam {
    @Id
    private Integer rid;
    private Integer uid;
    private String uname;
    private String useranswer;
    private Double grade;
    private Integer exid;
    private String ename;
    private String eids;
}

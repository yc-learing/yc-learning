package com.yc.learning.view.domain;

import com.yc.learning.domain.PageDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecordVoUserExamDomain extends PageDomain {
    private Integer rid;
    private Integer uid;
    private String uname;
    private String useranswer;
    private Double grade;
    private Integer exid;
    private String ename;
    private String eids;
    private Integer examtime;
    private String aname;
}

package com.yc.learning.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminDomain extends PageDomain{
    private Integer aid;
    private String aname;
    private String apwd;
    private Integer status;


}

package com.yc.learning.domain;

import lombok.Data;

@Data
public class AdminDomain extends PageDomain{
    private Integer aid;
    private String aname;
    private String apwd;
    private Integer status;

    public AdminDomain(Integer aid, String aname, String apwd, Integer status) {
        this.aid = aid;
        this.aname = aname;
        this.apwd = apwd;
        this.status = status;
    }

    public AdminDomain() {
    }
}

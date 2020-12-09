package com.yc.learning.domain;

import lombok.Data;

import java.util.Date;

@Data
public class UserDomain extends PageDomain{
    private Integer uid;
    private String uname;
    private String upwd;
    private String tel;
    private String email;
    private String qq;
    private String vx;
    private Integer classes;
    private Date registrytime;
    private Date endtime;
    private Integer status;

    public UserDomain(Integer uid, String uname, String upwd, String tel, String email, String qq, String vx, Integer classes, Date registrytime, Date endtime, Integer status) {
        this.uid = uid;
        this.uname = uname;
        this.upwd = upwd;
        this.tel = tel;
        this.email = email;
        this.qq = qq;
        this.vx = vx;
        this.classes = classes;
        this.registrytime = registrytime;
        this.endtime = endtime;
        this.status = status;
    }

    public UserDomain() {
    }
}

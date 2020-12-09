package com.yc.learning.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
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

}

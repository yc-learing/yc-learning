package com.yc.learning.entity;

import lombok.Data;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "user")
public class User {
    @Id
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

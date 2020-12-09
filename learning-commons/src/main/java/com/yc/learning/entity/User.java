package com.yc.learning.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
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

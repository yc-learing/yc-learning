package com.yc.learning.entity;

import lombok.Data;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Data
@Table(name = "user")
public class User {
    @Id
    private Integer uid;
    @NotEmpty(message="姓名不能为空!")
    private String uname;
    @NotEmpty(message="密码不能为空!")
    private String upwd;
    @NotEmpty(message="电话不能为空!")
    private String tel;
    @NotEmpty(message="邮箱不能为空!")
    @Email(message="邮箱格式错误")
    private String email;
    private String qq;
    private String vx;
    private Integer classes;
    private Date registrytime;
    private Date endtime;
    private Integer status;
}

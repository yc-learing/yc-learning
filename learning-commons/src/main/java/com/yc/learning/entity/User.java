package com.yc.learning.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Table(name = "user")
@JsonIgnoreProperties(ignoreUnknown = true)
public class User implements Serializable {
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

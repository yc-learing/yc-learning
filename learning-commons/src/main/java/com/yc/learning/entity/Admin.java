package com.yc.learning.entity;

import lombok.Data;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "admin")
public class Admin {
    @Id
    private Integer aid;
    private String aname;
    private String apwd;
    private Integer status;
}

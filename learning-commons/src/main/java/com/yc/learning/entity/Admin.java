package com.yc.learning.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "admin")
public class Admin implements Serializable {
    @Id
    private Integer aid;
    private String aname;
    private String apwd;
    private Integer status;
}

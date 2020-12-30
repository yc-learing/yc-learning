package com.yc.learning.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Table(name = "admin")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Admin implements Serializable {
    @Id
    private Integer aid;
    private String aname;
    private String apwd;
    private Integer status;
}

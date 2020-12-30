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
@Table(name = "record")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Record implements Serializable {
    @Id
    private Integer rid;
    private Integer exid;
    private Integer uid;
    private String useranswer;
    private Double grade;
    private String temp;

}

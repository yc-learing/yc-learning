package com.yc.learning.dao.impl;

import com.yc.learning.dao.MisBaseMapper;
import com.yc.learning.entity.Exam;
import com.yc.learning.entity.Exercises;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ExamMapper extends MisBaseMapper<Exam> {

    @Select("select * from exercises where eid in(${eids})")
    public List<Exercises> findByEids(@Param("eids") String eids);
}

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


    //查看试卷的试题
    @Select("select * from exercises where eid in(${eids})")
    public List<Exercises> findByEids(@Param("eids") String eids);

    //查看该班级的考卷
    @Select("select * from exam where classes = #{classes}")
    public List<Exam> findByClasses(@Param("classes")String classes);


}

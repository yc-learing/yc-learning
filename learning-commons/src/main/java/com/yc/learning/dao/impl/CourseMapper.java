package com.yc.learning.dao.impl;

import com.yc.learning.dao.MisBaseMapper;
import com.yc.learning.entity.Course;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

@Mapper
public interface CourseMapper extends MisBaseMapper<Course> {

    /**
     * 查询课程名并携带章节名
     * @return
     */
    @Select("select * from course")
    @Results(id = "courseMap",value = {
            @Result(id = true,column = "cid",property = "cid"),
            @Result(column = "coursename",property = "coursename"),
            @Result(property = "chapters",column = "cid",many = @Many(select = "com.yc.learning.dao.impl.ChapterMapper.findByCid",fetchType = FetchType.EAGER))
    })
    public List<Course> findCourseName();


    /**
     * 根据cid查询课程
     * @param cid
     * @return
     */
    @Select("select * from course where cid=#{cid}")
    public Course findByCid(Integer cid);
}

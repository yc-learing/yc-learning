package com.yc.learning.dao.impl;

import com.yc.learning.dao.MisBaseMapper;
import com.yc.learning.entity.Chapter;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

@Mapper
public interface ChapterMapper extends MisBaseMapper<Chapter> {

    /**
     * 查询所有章节，并携带课程信息
     * @return
     */
    @Select("select * from chapter")
    @Results(id = "chapterMap",value = {
        @Result(id=true,column = "chid",property = "chid"),
        @Result(column = "cid",property = "cid"),
        @Result(column = "cname",property = "cname"),
        @Result(property = "course",column = "cid",one=@One(select = "com.yc.learning.dao.impl.CourseMapper.findByCid",fetchType = FetchType.EAGER))
    })
    public List<Chapter> findAll();


    /**
     * 根据课程cid查询章节
     * @param cid
     * @return
     */
    @Select("select * from chapter where cid=#{cid}")
    public List<Chapter> findByCid(Integer cid);
}

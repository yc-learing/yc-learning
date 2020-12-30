package com.yc.learning.dao.impl;

import com.yc.learning.dao.MisBaseMapper;
import com.yc.learning.entity.Exercises;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ExercisesMapper extends MisBaseMapper<Exercises> {

    /**
     * 根据chid查询每个章节的题目
     * @param chid
     * @return
     */
    @Select("select * from exercises where chid=#{chid}")
    public List<Exercises> findByChid(Integer chid);

}

package com.yc.learning.dao.impl;

import com.yc.learning.dao.MisBaseMapper;
import com.yc.learning.entity.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface AdminMapper extends MisBaseMapper<Admin> {

    @Update("update  admin set ${field}=#{value} where aid=#{aid}")
    public int update(@Param("aid") Integer aid, @Param("value")Object value, @Param("field")String field);
}

package com.yc.learning.dao.impl;

import com.yc.learning.dao.MisBaseMapper;
import com.yc.learning.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper extends MisBaseMapper<User> {
    @Update("update  user set ${field}=#{value} where uid=#{uid}")
    public int update(@Param("uid") Integer uid, @Param("value")String value, @Param("field")String field);

}

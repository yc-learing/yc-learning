package com.yc.learning.dao;

import com.yc.learning.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("select * from user where username=#{username}")
    public User queryUserByUserName(String username);
}

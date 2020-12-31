package com.yc.learning.dao.impl;

import com.yc.learning.dao.MisBaseMapper;
import com.yc.learning.entity.Record;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface RecordMapper extends MisBaseMapper<Record> {

    //查看试卷的试题
    @Select("select count(*) from record  where exid=#{exid} and uid =#{uid}")
    public Integer countUidInExid(@Param("exid") Integer exid, @Param("uid") Integer uid);

    @Update("update record set useranswer=#{useranswer},grade=#{grade} where exid=#{exid} and uid =#{uid}")
    int updateRecordByExid_Uid(@Param("exid") Integer exid, @Param("uid") Integer uid,@Param("useranswer") String useranswer,@Param("grade")Double grade);
}

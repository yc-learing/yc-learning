package com.yc.learning.service;

import com.yc.learning.dao.impl.RecordMapper;
import com.yc.learning.entity.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class Learning_ExamModule_RecordService extends RecordServiceImpl{
    @Autowired(required = false)
    private RecordMapper recordMapper;

    //插入记录
    public int insert(Record record){
        return recordMapper.insert(record);
    }



    public int CountRecordByuid_exid(Record record){
        Integer exid = record.getExid();
        Integer uid = record.getUid();
        return recordMapper.countUidInExid(exid, uid);


    }

    public int updateRecordByExid_Uid(Record record) {
        Integer exid = record.getExid();
        Integer uid = record.getUid();
        String useranswer = record.getUseranswer();
        return  recordMapper.updateRecordByExid_Uid(exid,uid,useranswer);
    }
}

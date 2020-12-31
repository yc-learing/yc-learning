package com.yc.learning.service;

import com.yc.learning.dao.impl.ExamMapper;
import com.yc.learning.dao.impl.RecordMapper;
import com.yc.learning.entity.Exam;
import com.yc.learning.entity.Exercises;
import com.yc.learning.entity.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class Learning_ExamModule_RecordService extends RecordServiceImpl{
    @Autowired(required = false)
    private RecordMapper recordMapper;

    @Autowired(required = false)
    private ExamMapper examMapper;

    //插入记录
    public int insert(Record record){
        double grade=markExam(record);
        record.setGrade(grade);
        return recordMapper.insert(record);
    }

    //评卷并返回分数
    public double markExam(Record record){
        //根据exid获取当前考卷
        Exam exam=examMapper.selectByPrimaryKey(record.getExid());
        //根据考卷eids获取所有试题
        List<Exercises> list=examMapper.findByEids(exam.getEids());
        //[1.A,2.B,3.-1]
        String[] answer=record.getUseranswer().split(",");
        int len=list.size();
        int flag=0;
        for(int i=0;i<len;i++){
            String ans=list.get(i).getAnswer();
            String uans=answer[i].substring(answer[i].indexOf('.')+1);
            if(ans.equals(uans)){
                ++flag;
            }
        }
        double grade=(flag*1.0)/(len*1.0)*100;
        grade= Double.parseDouble(String.format("%.2f", grade));
        return grade;
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
        double grade=markExam(record);
        return  recordMapper.updateRecordByExid_Uid(exid,uid,useranswer,grade);
    }
}

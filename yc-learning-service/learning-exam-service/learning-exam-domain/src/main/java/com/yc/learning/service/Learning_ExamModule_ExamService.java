package com.yc.learning.service;

import com.yc.learning.annotaion.RedisAnnotation;
import com.yc.learning.dao.impl.ExamMapper;
import com.yc.learning.domain.ExamDomain;
import com.yc.learning.entity.Exam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class Learning_ExamModule_ExamService extends ExamServiceImpl{

    @Autowired(required = false)
    private ExamMapper examMapper;


    @Transactional(readOnly = true)
    public List<Exam> findByClasses(String classes){
        return  examMapper.findByClasses(classes);
    }

    @RedisAnnotation(useRedis = true)
    @Transactional(readOnly =  true)
    public List<Object> findExerciesByExam(Integer exid){
        ArrayList list =new ArrayList();

        ExamDomain examDomain =findOne(exid);
//        return examMapper.findByEids(examDomain.getEids());
        list.add(examDomain);
        list.add(examMapper.findByEids(examDomain.getEids()));
        return list;
    }


}

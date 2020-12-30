package com.yc.learning.service;

import com.yc.learning.dao.impl.ExamMapper;
import com.yc.learning.domain.ExamDomain;
import com.yc.learning.entity.Exam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ExamServiceImpl implements ExamService{

    @Autowired(required = false)
    private ExamMapper ExamMapper;

    @Transactional(readOnly = true)
    @Override
    public List<Exam> findAll() {
        List<Exam> list = ExamMapper.selectAll();

        return list;
    }



    @Override
    public int delete(Integer id) {
        return ExamMapper.deleteByPrimaryKey(id);
    }

    @Override
    public ExamDomain findOne(Integer id) {
        Exam Exam =this.ExamMapper.selectByPrimaryKey(id);
        ExamDomain ExamDomain = new ExamDomain(Exam.getExid(), Exam.getEname(), Exam.getEids(), Exam.getCreatetime()
        ,Exam.getExamtime(),Exam.getClasses(),Exam.getAname(),Exam.getStatus(),Exam.getTemp());
        return ExamDomain;
    }

    @Override
    public int insert(ExamDomain domain) {
        Exam exam = new Exam();
        exam.setAname(domain.getAname());
        exam.setClasses(domain.getClasses());
        exam.setCreatetime(domain.getCreatetime());
        exam.setEids(domain.getEids());
        exam.setExamtime(domain.getExamtime());
        exam.setEname(domain.getEname());
        exam.setStatus(0);
        exam.setTemp(domain.getTemp());
       return ExamMapper.insert(exam);
    }
}

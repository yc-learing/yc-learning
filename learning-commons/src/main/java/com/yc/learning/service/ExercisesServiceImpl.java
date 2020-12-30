package com.yc.learning.service;

import com.yc.learning.dao.impl.ExercisesMapper;
import com.yc.learning.domain.ExercisesDomain;
import com.yc.learning.entity.Exercises;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ExercisesServiceImpl implements  ExercisesService{

    @Autowired(required = false)
    private ExercisesMapper ExercisesMapper;

    @Transactional(readOnly = true)
    @Override
    public List<Exercises> findAll() {
        List<Exercises> list = ExercisesMapper.selectAll();
        return list;
    }



    @Override
    public int delete(Integer id) {
        return ExercisesMapper.deleteByPrimaryKey(id);
    }

    @Override
    public ExercisesDomain findOne(Integer id) {
        Exercises e =this.ExercisesMapper.selectByPrimaryKey(id);
        ExercisesDomain ExercisesDomain = new ExercisesDomain(
                e.getEid(), e.getChid(), e.getType(),
                e.getContent(),e.getOptiona(),e.getOptionb(),e.getOptionc()
                ,e.getOptiond(),e.getAnswer(),e.getAnalysis(),e.getInputtime()
                ,e.getAname(),e.getTemp());
        return ExercisesDomain;
    }

    @Override
    public int insert(ExercisesDomain domain) {
        Exercises exercises = new Exercises();
        exercises.setEid(domain.getEid());
       exercises.setChid(domain.getChid());
       exercises.setType(domain.getType());
       exercises.setContent(domain.getContent());
       exercises.setOptiona(domain.getOptiona());
       exercises.setOptionb(domain.getOptionb());
       exercises.setOptionc(domain.getOptionc());
       exercises.setOptiond(domain.getOptiond());
       exercises.setAnswer(domain.getAnswer());
       exercises.setAnalysis(domain.getAnalysis());
       exercises.setInputtime(new Date(System.currentTimeMillis()));
       exercises.setAname(domain.getAname());
       exercises.setTemp(domain.getTemp());
       return ExercisesMapper.insert(exercises);
    }
}

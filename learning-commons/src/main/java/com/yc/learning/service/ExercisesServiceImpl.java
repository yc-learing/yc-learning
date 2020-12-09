package com.yc.learning.service;

import com.github.pagehelper.PageHelper;
import com.yc.learning.domain.ExercisesDomain;
import com.yc.learning.domain.PageDomain;
import com.yc.learning.entity.Exercises;
import com.yc.learning.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import com.yc.learning.dao.impl.ExercisesMapper;
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

    @Transactional(readOnly = true)
    @Override
    public PageDomain<Exercises> listByPage(ExercisesDomain domain) {
        Example example =new Example(Exercises.class);  //条件
        //分页查询条件
        PageHelper.startPage(domain.getPage(), domain.getPageSize());
        //排序条件
        //Criteria：查询的规则
        Example.Criteria c = example.createCriteria();
        if (CommonUtils.isNotNull(domain.getContent())) {
            //条件创建  where 1= 1 and description like '%xxx%'

            c.andLike("description", "%" + domain.getContent() + "%");
        }
        return  null;
    }

    @Override
    public void delete(Integer id) {
        this.ExercisesMapper.deleteByPrimaryKey(id);
    }

    @Override
    public ExercisesDomain findOne(Integer id) {
        Exercises e =this.ExercisesMapper.selectByPrimaryKey(id);
        ExercisesDomain ExercisesDomain = new ExercisesDomain(
                e.getEid(), e.getCid(), e.getType(),
                e.getContent(),e.getOptionA(),e.getOptionB(),e.getOptionC()
                ,e.getOptionD(),e.getAnswer(),e.getAnalysis(),e.getInputtime()
                ,e.getAname(),e.getTemp());
        return ExercisesDomain;
    }

    @Override
    public void insert(ExercisesDomain domain) {
        Exercises exercises = new Exercises();
        exercises.setEid(domain.getEid());
       exercises.setCid(domain.getCid());
       exercises.setType(domain.getType());
       exercises.setContent(domain.getContent());
       exercises.setOptionA(domain.getOptionA());
       exercises.setOptionB(domain.getOptionB());
       exercises.setOptionC(domain.getOptionC());
       exercises.setOptionD(domain.getOptionD());
       exercises.setAnswer(domain.getAnswer());
       exercises.setAnalysis(domain.getAnalysis());
       exercises.setInputtime(domain.getInputtime());
       exercises.setAname(domain.getAname());
       exercises.setTemp(domain.getTemp());
        /**
         *TODO:uuid生成
         */
       this.ExercisesMapper.insert(exercises);
       domain.setEid(exercises.getEid());
    }
}

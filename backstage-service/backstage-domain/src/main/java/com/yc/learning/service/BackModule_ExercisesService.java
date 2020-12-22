package com.yc.learning.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yc.learning.annotaion.RedisAnnotation;
import com.yc.learning.dao.impl.ExercisesMapper;
import com.yc.learning.domain.ExercisesDomain;
import com.yc.learning.domain.PageDomain;
import com.yc.learning.entity.Exercises;
import com.yc.learning.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class BackModule_ExercisesService extends ExercisesServiceImpl{
    @Autowired(required = false)
    private ExercisesMapper exercisesMapper;

    @Transactional(readOnly = true)
    @RedisAnnotation(useRedis = true)
    public PageDomain<ExercisesDomain> findByPage(ExercisesDomain exercisesDomain,Integer page, Integer pageSize) {
        Example example = new Example(Exercises.class);   //条件
        //分页条件设置
        PageHelper.startPage(exercisesDomain.getPage(), exercisesDomain.getPageSize());
        //排序条件
        example.setOrderByClause("eid desc");
        //  Criteria: 查询的规则
        Example.Criteria c = example.createCriteria();
        if (CommonUtils.isNotNull(exercisesDomain.getContent())) {
            //条件创建    where 1=1 and description like '%xx%';
            c.andLike("content", "%" + exercisesDomain.getContent() + "%");
        }
        // PageInfo: 分页的结果   总记录数，第几页，每页多少条条，上一页，下一页， 总共多少页.
        PageInfo<Exercises> pageInfo = new PageInfo<Exercises>(exercisesMapper.selectByExample(example));
        PageDomain<ExercisesDomain> pageDomain = new PageDomain<ExercisesDomain>();
        pageDomain.setTotal(pageInfo.getTotal());
        pageDomain.setPage(pageInfo.getPageNum());
        pageDomain.setPageSize(exercisesDomain.getPageSize());
        pageDomain.setTotalPages(pageInfo.getPages());
        List<ExercisesDomain> r = new ArrayList<ExercisesDomain>();
        //从pageInfo中取记录数
        if (pageInfo.getList()!= null) {
            for (Exercises e : pageInfo.getList()) {
                ExercisesDomain ed=new ExercisesDomain(e.getEid(),e.getChid(),e.getType(),e.getContent(),e.getOptiona(),e.getOptionb(),
                        e.getOptionc(),e.getOptiond(),e.getAnswer(),e.getAnalysis(),e.getInputtime(),e.getAname(),e.getTemp());
                ed.setPage(exercisesDomain.getPage());
                ed.setPageSize(exercisesDomain.getPageSize());
                r.add(ed);
            }
        }
        pageDomain.setData(r);
        return pageDomain;
    }
}

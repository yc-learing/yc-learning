package com.yc.learning.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yc.learning.annotaion.RedisAnnotation;
import com.yc.learning.dao.impl.ExercisesMapper;
import com.yc.learning.dao.impl.ExercisesVoChpaterCourseMapper;
import com.yc.learning.domain.ExercisesDomain;
import com.yc.learning.domain.PageDomain;
import com.yc.learning.entity.Exercises;
import com.yc.learning.util.CommonUtils;
import com.yc.learning.view.ExercisesVoChpaterCourse;
import com.yc.learning.view.domain.ExercisesVoChpaterCourseDomain;
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

    @Autowired(required = false)
    private ExercisesVoChpaterCourseMapper vomapper;

    @Override
    @Transactional(readOnly = false)
    @RedisAnnotation(deleteRedis = true)
    public int insert(ExercisesDomain domain) {
        return super.insert(domain);
    }

    @RedisAnnotation(useRedis = true)
    @Transactional(readOnly = true)
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
            c.andLike("content", "%" + exercisesDomain.getContent().toLowerCase() + "%");
            c.orLike("content", "%" + exercisesDomain.getContent().toUpperCase() + "%");
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
                        e.getOptionc(),e.getOptiond(),e.getAnswer(),e.getAnalysis(),e.getInputtime(),e.getAname(),null);
                ed.setPage(exercisesDomain.getPage());
                ed.setPageSize(exercisesDomain.getPageSize());
                r.add(ed);
            }
        }
        pageDomain.setData(r);
        return pageDomain;
    }

    @RedisAnnotation(useRedis = true)
    @Transactional(readOnly = true)
    public PageDomain<ExercisesVoChpaterCourseDomain> findVoByPage(ExercisesVoChpaterCourseDomain vo, Integer page, Integer pageSize) {
        Example example = new Example(ExercisesVoChpaterCourse.class);   //条件
        //分页条件设置
        PageHelper.startPage(vo.getPage(), vo.getPageSize());
        //排序条件
        example.setOrderByClause("eid desc");
        //  Criteria: 查询的规则
        Example.Criteria c = example.createCriteria();
        if (CommonUtils.isNotNull(vo.getChid())) {
            //条件创建    where 1=1 and description like '%xx%';
            c.andEqualTo("chid",vo.getChid());
        }
        if (CommonUtils.isNotNull(vo.getCid())) {
            //条件创建    where 1=1 and description like '%xx%';
            c.andEqualTo("cid",vo.getCid());
        }
        // PageInfo: 分页的结果   总记录数，第几页，每页多少条条，上一页，下一页， 总共多少页.
        PageInfo<ExercisesVoChpaterCourse> pageInfo = new PageInfo<ExercisesVoChpaterCourse>(vomapper.selectByExample(example));
        PageDomain<ExercisesVoChpaterCourseDomain> pageDomain = new PageDomain<ExercisesVoChpaterCourseDomain>();
        pageDomain.setTotal(pageInfo.getTotal());
        pageDomain.setPage(pageInfo.getPageNum());
        pageDomain.setPageSize(vo.getPageSize());
        pageDomain.setTotalPages(pageInfo.getPages());
        List<ExercisesVoChpaterCourseDomain> r = new ArrayList<ExercisesVoChpaterCourseDomain>();
        //从pageInfo中取记录数
        if (pageInfo.getList()!= null) {
            for (ExercisesVoChpaterCourse e : pageInfo.getList()) {
                ExercisesVoChpaterCourseDomain ed=new ExercisesVoChpaterCourseDomain(
                        e.getEid(),e.getChid(),e.getCid(),e.getCoursename(),e.getCname(),e.getType(),e.getContent(),e.getOptiona()
                        ,e.getOptionb(),e.getOptionc(),e.getOptiond(),e.getAnswer(),e.getAnalysis(),e.getStatus()
                );
                r.add(ed);
            }
        }
        pageDomain.setData(r);
        return pageDomain;
    }
}

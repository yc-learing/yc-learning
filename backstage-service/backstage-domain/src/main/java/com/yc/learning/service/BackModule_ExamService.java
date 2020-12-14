package com.yc.learning.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yc.learning.annotaion.RedisAnnotation;
import com.yc.learning.dao.impl.ExamMapper;
import com.yc.learning.domain.ExamDomain;
import com.yc.learning.domain.PageDomain;
import com.yc.learning.entity.Exam;
import com.yc.learning.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liyan
 * @create 2020-12-2020/12/13-8:57
 */
@Service
@Transactional
public class BackModule_ExamService {

    @Autowired(required = false)
    private ExamMapper examMapper;

    @Transactional(readOnly = true)
    @RedisAnnotation(useRedis = true)
    public PageDomain<ExamDomain> findByPage(ExamDomain examDomain,Integer page, Integer pageSize) {
        Example example = new Example(Exam.class);   //条件
        //分页条件设置
        PageHelper.startPage(examDomain.getPage(),examDomain.getPageSize());
        //排序条件
        example.setOrderByClause("exid desc");
        //  Criteria: 查询的规则
        Example.Criteria c = example.createCriteria();
        if (CommonUtils.isNotNull(examDomain.getEname())) {
            //条件创建    where 1=1 and description like '%xx%';
            c.andLike("ename", "%" + examDomain.getEname() + "%");
        }
        // PageInfo: 分页的结果   总记录数，第几页，每页多少条条，上一页，下一页， 总共多少页.
        PageInfo<Exam> pageInfo = new PageInfo<Exam>(examMapper.selectByExample(example));
        PageDomain<ExamDomain> pageDomain = new PageDomain<ExamDomain>();
        pageDomain.setTotal(pageInfo.getTotal());
        pageDomain.setPage(pageInfo.getPageNum());
        pageDomain.setPageSize(examDomain.getPageSize());
        pageDomain.setTotalPages(pageInfo.getPages());
        //List<Pic> list = picMapper.selectByExample(example);
        List<ExamDomain> r = new ArrayList<ExamDomain>();
        //从pageInfo中取记录数
        if (pageInfo.getList()!= null) {
            for (Exam e : pageInfo.getList()) {
                ExamDomain ed = new ExamDomain(e.getExid(), e.getEname(),e.getEids(),e.getCreatetime(),e.getExamtime(),e.getClasses(),e.getAname(),e.getStatus(),e.getTemp());
                ed.setPage(examDomain.getPage());
                ed.setPageSize(examDomain.getPageSize());
                r.add(ed);
            }
        }
        pageDomain.setData(r);
        return pageDomain;
    }
}

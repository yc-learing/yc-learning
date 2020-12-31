package com.yc.learning.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yc.learning.annotaion.RedisAnnotation;
import com.yc.learning.dao.impl.RecordMapper;
import com.yc.learning.dao.impl.RecordVoUserExamMapper;
import com.yc.learning.domain.PageDomain;
import com.yc.learning.domain.RecordDomain;
import com.yc.learning.entity.Record;
import com.yc.learning.util.CommonUtils;
import com.yc.learning.view.RecordVoUserExam;
import com.yc.learning.view.domain.RecordVoUserExamDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

@Service
public class BackModule_RecordService extends RecordServiceImpl{
    @Autowired(required = false)
    private RecordMapper recordMapper;

    @Autowired(required = false)
    private RecordVoUserExamMapper recordVoUserExamMapper;

    @RedisAnnotation(useRedis = true)
    @Transactional(readOnly = true)
    public PageDomain<RecordDomain> findByPage(RecordDomain recordDomain,Integer page, Integer pageSize) {
        Example example = new Example(Record.class);   //条件
        //分页条件设置
        PageHelper.startPage(recordDomain.getPage(), recordDomain.getPageSize());
        //排序条件
        example.setOrderByClause("rid desc");
        //  Criteria: 查询的规则
        Example.Criteria c = example.createCriteria();
        // PageInfo: 分页的结果   总记录数，第几页，每页多少条条，上一页，下一页， 总共多少页.
        PageInfo<Record> pageInfo = new PageInfo<Record>(recordMapper.selectByExample(example));
        PageDomain<RecordDomain> pageDomain = new PageDomain<RecordDomain>();
        pageDomain.setTotal(pageInfo.getTotal());
        pageDomain.setPage(pageInfo.getPageNum());
        pageDomain.setPageSize(recordDomain.getPageSize());
        pageDomain.setTotalPages(pageInfo.getPages());
        List<RecordDomain> list = new ArrayList<RecordDomain>();
        //从pageInfo中取记录数
        if (pageInfo.getList()!= null) {
            for (Record r : pageInfo.getList()) {
                RecordDomain rd=new RecordDomain(r.getRid(),r.getExid(),r.getUid(),r.getUseranswer(),r.getGrade(),r.getTemp());
                rd.setPage(recordDomain.getPage());
                rd.setPageSize(recordDomain.getPageSize());
                list.add(rd);
            }
        }
        pageDomain.setData(list);
        return pageDomain;
    }

    @Transactional(readOnly = true)
    public PageDomain<RecordVoUserExamDomain> findVoByPage(RecordVoUserExamDomain vo, Integer page, Integer pageSize) {
        Example example = new Example(RecordVoUserExam.class);   //条件
        //分页条件设置
        PageHelper.startPage(vo.getPage(), vo.getPageSize());
        //排序条件
        example.setOrderByClause("rid desc");
        //  Criteria: 查询的规则
        Example.Criteria c = example.createCriteria();
        if (CommonUtils.isNotNull(vo.getEname())) {
            //条件创建    where 1=1 and description like '%xx%';
            c.andLike("ename", "%" + vo.getEname() + "%");
        }
        if (CommonUtils.isNotNull(vo.getUname())) {
            //条件创建    where 1=1 and description like '%xx%';
            c.andLike("uname", "%" + vo.getUname() + "%");
        }
        // PageInfo: 分页的结果   总记录数，第几页，每页多少条条，上一页，下一页， 总共多少页.
        PageInfo<RecordVoUserExam> pageInfo = new PageInfo<RecordVoUserExam>(recordVoUserExamMapper.selectByExample(example));
        PageDomain<RecordVoUserExamDomain> pageDomain = new PageDomain<RecordVoUserExamDomain>();
        pageDomain.setTotal(pageInfo.getTotal());
        pageDomain.setPage(pageInfo.getPageNum());
        pageDomain.setPageSize(vo.getPageSize());
        pageDomain.setTotalPages(pageInfo.getPages());
        List<RecordVoUserExamDomain> list = new ArrayList<RecordVoUserExamDomain>();
        //从pageInfo中取记录数
        if (pageInfo.getList()!= null) {
            for (RecordVoUserExam r : pageInfo.getList()) {
                RecordVoUserExamDomain rd=new RecordVoUserExamDomain(r.getRid(),r.getUid(),r.getUname(),r.getUseranswer(),r.getGrade(),r.getExid(),r.getEname(),r.getEids(),r.getExamtime(),r.getAname());
                list.add(rd);
            }
        }
        pageDomain.setData(list);
        return pageDomain;
    }
}

package com.yc.learning.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yc.learning.dao.impl.RecordMapper;
import com.yc.learning.domain.PageDomain;
import com.yc.learning.domain.RecordDomain;
import com.yc.learning.entity.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class BackModule_RecordService extends RecordServiceImpl{
    @Autowired(required = false)
    private RecordMapper recordMapper;

    @Transactional(readOnly = true)
    public PageDomain<RecordDomain> findByPage(RecordDomain recordDomain) {
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
        pageDomain.setTotalPages(pageInfo.getPages());
        List<RecordDomain> list = new ArrayList<RecordDomain>();
        //从pageInfo中取记录数
        if (pageInfo.getList()!= null) {
            for (Record r : pageInfo.getList()) {
                RecordDomain rd=new RecordDomain(r.getRid(),r.getExid(),r.getUid(),r.getUseranswer(),r.getGrade(),r.getTemp());
                list.add(rd);
            }
        }
        pageDomain.setData(list);
        return pageDomain;
    }
}

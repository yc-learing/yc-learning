package com.yc.learning.service;

import com.github.pagehelper.PageHelper;
import com.yc.learning.domain.PageDomain;
import com.yc.learning.domain.RecordDomain;
import com.yc.learning.entity.Record;
import com.yc.learning.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import com.yc.learning.dao.impl.RecordMapper;
import java.util.List;

@Service
@Transactional
public class RecordServiceImpl implements  RecordService {

    @Autowired(required = false)
    private RecordMapper RecordMapper;

    @Transactional(readOnly = true)
    @Override
    public List<Record> findAll() {
        List<Record> list = RecordMapper.selectAll();

        return list;
    }

    @Transactional(readOnly = true)
    @Override
    public PageDomain<Record> listByPage(RecordDomain domain) {
        Example example =new Example(Record.class);  //条件
        //分页查询条件
        PageHelper.startPage(domain.getPage(), domain.getPageSize());
        //排序条件
        //Criteria：查询的规则
        Example.Criteria c = example.createCriteria();
        if (CommonUtils.isNotNull(domain.getUid())) {
            //条件创建  where 1= 1 and description like '%xxx%'

            c.andLike("description", "%" + domain.getUid() + "%");
        }
        return  null;
    }

    @Override
    public void delete(Integer id) {
        this.RecordMapper.deleteByPrimaryKey(id);
    }

    @Override
    public RecordDomain findOne(Integer id) {
        Record record =this.RecordMapper.selectByPrimaryKey(id);
        RecordDomain domain = new RecordDomain(
                record.getRid(),record.getExid(),record.getUid(),record.getUseranswer()
                ,record.getGrade(),record.getTemp());
        return domain;
    }

    @Override
    public void insert(RecordDomain domain) {
        Record record = new Record();
        record.setExid(domain.getExid());
        record.setGrade(domain.getGrade());
        record.setUid(domain.getUid());
        record.setUseranswer(domain.getUseranswer());
        record.setTemp(domain.getTemp());
        /**
         *TODO:uuid生成
         */
        this.RecordMapper.insert(record);
        domain.setRid(record.getRid());
    }
}

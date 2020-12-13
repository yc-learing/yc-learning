package com.yc.learning.service;

import com.yc.learning.dao.impl.RecordMapper;
import com.yc.learning.domain.RecordDomain;
import com.yc.learning.entity.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        this.RecordMapper.insert(record);
        domain.setRid(record.getRid());
    }
}

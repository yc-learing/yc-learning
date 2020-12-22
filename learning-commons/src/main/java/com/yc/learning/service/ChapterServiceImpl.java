package com.yc.learning.service;

import com.yc.learning.dao.impl.ChapterMapper;
import com.yc.learning.domain.ChapterDomain;
import com.yc.learning.entity.Chapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ChapterServiceImpl implements  ChapterService{

    @Autowired(required = false)
    private ChapterMapper ChapterMapper;

    @Transactional(readOnly = true)
    @Override
    public List<Chapter> findAll() {
        List<Chapter> list = ChapterMapper.selectAll();

        return list;
    }



    @Override
    public int delete(Integer id) {
        return ChapterMapper.deleteByPrimaryKey(id);
    }

    @Override
    public ChapterDomain findOne(Integer id) {
        Chapter Chapter =this.ChapterMapper.selectByPrimaryKey(id);
        ChapterDomain ChapterDomain = new ChapterDomain(
                Chapter.getChid(),Chapter.getCname(),Chapter.getCid(),
                Chapter.getTemp()
        );
        return ChapterDomain;
    }

    @Override
    public int insert(ChapterDomain domain) {
        Chapter chapter = new Chapter();
        chapter.setCid(domain.getCid());
        chapter.setCname(domain.getCname());
        chapter.setTemp(domain.getTemp());
        return ChapterMapper.insert(chapter);
    }
}

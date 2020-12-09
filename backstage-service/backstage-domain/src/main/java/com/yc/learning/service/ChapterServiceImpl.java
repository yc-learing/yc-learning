package com.yc.learning.service;

import com.github.pagehelper.PageHelper;
import com.yc.learning.dao.impl.ChapterMapper;
import com.yc.learning.dao.impl.ChapterMapper;
import com.yc.learning.domain.ChapterDomain;
import com.yc.learning.domain.ChapterDomain;
import com.yc.learning.domain.PageDomain;
import com.yc.learning.entity.Chapter;
import com.yc.learning.entity.Chapter;
import com.yc.learning.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

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

    @Transactional(readOnly = true)
    @Override
    public PageDomain<Chapter> listByPage(ChapterDomain domain) {
        Example example =new Example(Chapter.class);  //条件
        //分页查询条件
        PageHelper.startPage(domain.getPage(), domain.getPageSize());
        //排序条件
        //Criteria：查询的规则
        Example.Criteria c = example.createCriteria();
        if (CommonUtils.isNotNull(domain.getCname())) {
            //条件创建  where 1= 1 and description like '%xxx%'

            c.andLike("description", "%" + domain.getCname() + "%");
        }
        return  null;
    }

    @Override
    public void delete(Integer id) {
        this.ChapterMapper.deleteByPrimaryKey(id);
    }

    @Override
    public ChapterDomain findOne(Integer id) {
        Chapter Chapter =this.ChapterMapper.selectByPrimaryKey(id);
        ChapterDomain ChapterDomain = new ChapterDomain(
                Chapter.getChid(),Chapter.getCname(),Chapter.getPchid(),Chapter.getCid(),
                Chapter.getTemp()
        );
        return ChapterDomain;
    }

    @Override
    public void insert(ChapterDomain domain) {
        Chapter chapter = new Chapter();
        chapter.setCid(domain.getCid());
        chapter.setCname(domain.getCname());
        chapter.setCid(domain.getCid());
        chapter.setTemp(domain.getTemp());

        this.ChapterMapper.insert(chapter);
        domain.setChid(chapter.getChid());
    }
}

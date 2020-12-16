package com.yc.learning.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yc.learning.annotaion.RedisAnnotation;
import com.yc.learning.dao.impl.ChapterMapper;
import com.yc.learning.domain.ChapterDomain;
import com.yc.learning.domain.PageDomain;
import com.yc.learning.entity.Chapter;
import com.yc.learning.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class BackModule_ChapterService extends ChapterServiceImpl{
    @Autowired(required = false)
    private ChapterMapper chapterMapper;

    @RedisAnnotation(useRedis = true)
    @Transactional(readOnly = true)
    public PageDomain<ChapterDomain> findByPage(ChapterDomain chapterDomain,Integer page, Integer pageSize) {
        Example example = new Example(Chapter.class);   //条件
        //分页条件设置
        PageHelper.startPage(chapterDomain.getPage(), chapterDomain.getPageSize());
        //排序条件
        example.setOrderByClause("cid desc");
        //  Criteria: 查询的规则
        Example.Criteria c = example.createCriteria();
        if (CommonUtils.isNotNull(chapterDomain.getCname())) {
            //条件创建    where 1=1 and description like '%xx%';
            c.andLike("cname", "%" + chapterDomain.getCname() + "%");
        }
        // PageInfo: 分页的结果   总记录数，第几页，每页多少条条，上一页，下一页， 总共多少页.
        PageInfo<Chapter> pageInfo = new PageInfo<Chapter>(chapterMapper.selectByExample(example));
        PageDomain<ChapterDomain> pageDomain = new PageDomain<ChapterDomain>();
        pageDomain.setTotal(pageInfo.getTotal());
        pageDomain.setPage(pageInfo.getPageNum());
        pageDomain.setPageSize(chapterDomain.getPageSize());
        pageDomain.setTotalPages(pageInfo.getPages());
        List<ChapterDomain> r = new ArrayList<ChapterDomain>();
        //从pageInfo中取记录数
        if (pageInfo.getList()!= null) {
            for (Chapter ch : pageInfo.getList()) {
                ChapterDomain cd=new ChapterDomain(ch.getChid(),ch.getCname(),ch.getCid(),ch.getTemp());
                cd.setPage(chapterDomain.getPage());
                cd.setPageSize(chapterDomain.getPageSize());
                r.add(cd);
            }
        }
        pageDomain.setData(r);
        return pageDomain;
    }


}

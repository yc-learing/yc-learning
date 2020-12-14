package com.yc.learning.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yc.learning.annotaion.RedisAnnotation;
import com.yc.learning.dao.impl.CourseMapper;
import com.yc.learning.domain.CourseDomain;
import com.yc.learning.domain.PageDomain;
import com.yc.learning.entity.Course;
import com.yc.learning.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liyan
 * @create 2020-12-2020/12/13-9:46
 */
@Service
@Transactional
public class BackModule_CourseService {
    @Autowired(required = false)
    private CourseMapper courseMapper;

    @Transactional(readOnly = true)
    @RedisAnnotation(useRedis = true)
    public PageDomain<CourseDomain> listByPage(CourseDomain courseDomain) {
        Example example = new Example(Course.class);   //条件
        //分页条件设置
        PageHelper.startPage(courseDomain.getPage(),courseDomain.getPageSize());
        //排序条件
        example.setOrderByClause("cid desc");
        //  Criteria: 查询的规则
        Example.Criteria cr = example.createCriteria();
        if (CommonUtils.isNotNull(courseDomain.getCoursename())) {
            //条件创建    where 1=1 and description like '%xx%';
            cr.andLike("coursename", "%" +courseDomain.getCoursename() + "%");
        }
        // PageInfo: 分页的结果   总记录数，第几页，每页多少条条，上一页，下一页， 总共多少页.
        PageInfo<Course> pageInfo = new PageInfo<Course>(courseMapper.selectByExample(example));
        PageDomain<CourseDomain> pageDomain = new PageDomain<CourseDomain>();
        pageDomain.setTotal(pageInfo.getTotal());
        pageDomain.setPage(pageInfo.getPageNum());
        pageDomain.setTotalPages(pageInfo.getPages());
        //List<Pic> list = picMapper.selectByExample(example);
        List<CourseDomain> r = new ArrayList<CourseDomain>();
        //从pageInfo中取记录数
        if (pageInfo.getList()!= null) {
            for (Course c : pageInfo.getList()) {
                CourseDomain cd = new CourseDomain(c.getCid(),c.getCoursename(),c.getDescr(),c.getPic(),c.getStatus());
                r.add(cd);
            }
        }
        pageDomain.setData(r);
        return pageDomain;
    }

}

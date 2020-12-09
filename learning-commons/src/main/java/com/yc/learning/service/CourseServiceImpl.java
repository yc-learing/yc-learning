package com.yc.learning.service;

import com.github.pagehelper.PageHelper;
import com.yc.learning.domain.CourseDomain;
import com.yc.learning.domain.PageDomain;
import com.yc.learning.entity.Course;
import com.yc.learning.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import com.yc.learning.impl.CourseMapper;
import java.util.List;

@Service
@Transactional
public class CourseServiceImpl implements  CourseService {

    @Autowired(required = false)
    private CourseMapper CourseMapper;

    @Transactional(readOnly = true)
    @Override
    public List<Course> findAll() {
        List<Course> list = CourseMapper.selectAll();

        return list;
    }

    @Transactional(readOnly = true)
    @Override
    public PageDomain<Course> listByPage(CourseDomain picDomain) {
        Example example =new Example(Course.class);  //条件
        //分页查询条件
        PageHelper.startPage(picDomain.getPage(), picDomain.getPageSize());
        //排序条件
        //Criteria：查询的规则
        Example.Criteria c = example.createCriteria();
        if (CommonUtils.isNotNull(picDomain.getCoursename())) {
            //条件创建  where 1= 1 and description like '%xxx%'

            c.andLike("description", "%" + picDomain.getCoursename() + "%");
        }
        return  null;
    }

    @Override
    public void delete(Integer id) {
        this.CourseMapper.deleteByPrimaryKey(id);
    }

    @Override
    public CourseDomain findOne(Integer id) {
        Course Course =this.CourseMapper.selectByPrimaryKey(id);
        CourseDomain CourseDomain = new CourseDomain(Course.getCid(), Course.getCoursename(), Course.getDescribe(), Course.getPic(),Course.getStatus());
        return CourseDomain;
    }

    @Override
    public void insert(CourseDomain domain) {
        Course course =new Course();
        course.setCoursename(domain.getCoursename());
        course.setDescribe(domain.getDescribe());
        course.setPic(domain.getPic());
        course.setStatus(domain.getStatus());
        this.CourseMapper.insert(course);
        domain.setCid(course.getCid());
    }
}

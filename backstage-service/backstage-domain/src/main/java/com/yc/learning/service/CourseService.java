package com.yc.learning.service;

import com.yc.learning.domain.CourseDomain;
import com.yc.learning.domain.CourseDomain;
import com.yc.learning.domain.PageDomain;
import com.yc.learning.entity.Course;
import com.yc.learning.entity.Course;

import java.util.List;

public interface CourseService {

    /**
     * 查询所有的管理员
     */
    public List<Course> findAll();


    /**
     * 查询指定的管理员（分页查询）
     */
    public PageDomain<Course> listByPage(CourseDomain Course);


    /**
     * 删除图片
     */
    public void delete(Integer id);

    /**
     * 根据id查图片详情(    图片的metadata )
     *
     * @param id
     * @return
     */
    public CourseDomain findOne(Integer id);

    /**
     * 修改管理员的信息
     * @param Course
     * @return
     */
    public void insert(CourseDomain Course);
}

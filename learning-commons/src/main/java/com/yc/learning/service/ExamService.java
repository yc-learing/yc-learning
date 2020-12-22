package com.yc.learning.service;

import com.yc.learning.domain.ExamDomain;
import com.yc.learning.entity.Exam;

import java.util.List;

public interface ExamService {

    /**
     * 查询所有的管理员
     */
    public List<Exam> findAll();


    /**
     * 查询指定的管理员（分页查询）
     */


    /**
     * 删除图片
     */
    public int delete(Integer id);

    /**
     * 根据id查图片详情(    图片的metadata )
     *
     * @param id
     * @return
     */
    public ExamDomain findOne(Integer id);

    /**
     * 修改管理员的信息
     * @param Exam
     * @return
     */
    public int insert(ExamDomain Exam);
}

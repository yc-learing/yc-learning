package com.yc.learning.service;

import com.yc.learning.domain.ExercisesDomain;
import com.yc.learning.entity.Exercises;

import java.util.List;

public interface ExercisesService {

    /**
     * 查询所有的管理员
     */
    public List<Exercises> findAll();


    /**
     * 查询指定的管理员（分页查询）
     */


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
    public ExercisesDomain findOne(Integer id);

    /**
     * 修改管理员的信息
     * @param Exercises
     * @return
     */
    public void insert(ExercisesDomain Exercises);
}

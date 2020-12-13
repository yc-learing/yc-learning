package com.yc.learning.service;

import com.yc.learning.domain.ChapterDomain;
import com.yc.learning.entity.Chapter;

import java.util.List;

public interface ChapterService {

    /**
     * 查询所有的管理员
     */
    public List<Chapter> findAll();


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
    public ChapterDomain findOne(Integer id);

    /**
     * 修改管理员的信息
     * @param Chapter
     * @return
     */
    public void insert(ChapterDomain Chapter);
}

package com.yc.learning.service;

import com.yc.learning.domain.PageDomain;
import com.yc.learning.domain.RecordDomain;
import com.yc.learning.entity.Record;

import java.util.List;

public interface RecordService {

    /**
     * 查询所有的管理员
     */
    public List<Record> findAll();


    /**
     * 查询指定的管理员（分页查询）
     */
    public PageDomain<Record> listByPage(RecordDomain Record);


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
    public RecordDomain findOne(Integer id);

    /**
     * 修改管理员的信息
     * @param Record
     * @return
     */
    public void insert(RecordDomain Record);
}

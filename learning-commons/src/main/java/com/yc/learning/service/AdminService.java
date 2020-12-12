package com.yc.learning.service;

import com.yc.learning.domain.AdminDomain;
import com.yc.learning.entity.Admin;

import java.util.List;

/**
 *
 */
public interface AdminService {

    /**
     * 查询所有的管理员
     */
    public List<Admin> findAll();


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
    public AdminDomain findOne(Integer id);

    /**
     * 修改管理员的信息
     * @param admin
     * @return
     */
    public void insert(AdminDomain admin);
}

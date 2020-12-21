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
    public AdminDomain findOne(Integer id);

    /**
     * 修改管理员的信息
     * @param admin
     * @return
     */
    public int insert(AdminDomain admin);
    /**
     * 修改管理员的信息
     * @param admin
     * @return
     */
    public int update(Integer aid,Object value,String field);
}

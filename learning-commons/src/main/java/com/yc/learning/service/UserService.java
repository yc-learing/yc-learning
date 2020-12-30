package com.yc.learning.service;

import com.yc.learning.domain.UserDomain;
import com.yc.learning.entity.User;

import java.util.List;

public interface UserService {

    /**
     * 查询所有的管理员
     */
    public List<User> findAll();

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
    public UserDomain findOne(Integer id);

    /**
     * 修改管理员的信息
     * @param User
     * @return
     */
    public int insert(UserDomain User);

    /**
     * 修改管理员的信息
     * @param User
     * @return
     */
    public int update(Integer uid,String value,String field);
}

package com.yc.learning.service;

import com.yc.learning.dao.impl.UserMapper;
import com.yc.learning.domain.UserDomain;
import com.yc.learning.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class UserServiceImpl implements UserService{

    @Autowired(required = false)
    private UserMapper UserMapper;

    @Transactional(readOnly = true)
    @Override
    public List<User> findAll() {
        List<User> list = UserMapper.selectAll();

        return list;
    }



    @Override
    public void delete(Integer id) {
        this.UserMapper.deleteByPrimaryKey(id);
    }

    @Override
    public UserDomain findOne(Integer id) {
        User user =this.UserMapper.selectByPrimaryKey(id);
        UserDomain domain = new UserDomain(
               user.getUid(),user.getUname(),user.getUpwd(),user.getTel(),user.getEmail(),
                user.getQq(),user.getVx(),user.getClasses(),user.getRegistrytime(),
                user.getEndtime(),user.getStatus());
        return domain;
    }

    @Override
    public void insert(UserDomain domain) {
        User user = new User(null,domain.getUname(),domain.getUpwd(),
                domain.getTel(),domain.getEmail(),domain.getQq(),
                domain.getVx(),domain.getClasses()
                ,domain.getRegistrytime(),domain.getEndtime()
                ,domain.getStatus());
        this.UserMapper.insert(user);
        domain.setUid(user.getUid());
    }
}

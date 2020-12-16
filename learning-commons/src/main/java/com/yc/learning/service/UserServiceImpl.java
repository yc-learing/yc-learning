package com.yc.learning.service;

import com.yc.learning.dao.impl.UserMapper;
import com.yc.learning.domain.UserDomain;
import com.yc.learning.entity.User;
import com.yc.learning.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

public class UserServiceImpl implements UserService{

    @Autowired(required = false)
    private UserMapper userMapper;

    @Transactional(readOnly = true)
    @Override
    public List<User> findAll() {
        List<User> list = userMapper.selectAll();

        return list;
    }



    @Override
    public void delete(Integer id) {
        this.userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public UserDomain findOne(Integer id) {
        User user =this.userMapper.selectByPrimaryKey(id);
        UserDomain domain = new UserDomain(
               user.getUid(),user.getUname(),user.getUpwd(),user.getTel(),user.getEmail(),
                user.getQq(),user.getVx(),user.getClasses(),user.getRegistrytime(),
                user.getEndtime(),user.getStatus());
        return domain;
    }

    @Override
    public void insert(UserDomain domain) {
        User user = new User(null,domain.getUname(), MD5Utils.stringToMD5(domain.getUpwd()),
                domain.getTel(),domain.getEmail(),domain.getQq(),
                domain.getVx(),domain.getClasses()
                ,domain.getRegistrytime(),domain.getEndtime()
                ,domain.getStatus());
        this.userMapper.insert(user);
        domain.setUid(user.getUid());
    }

    @Override
    public int update(UserDomain user) {
        User u=new User(user.getUid(),user.getUname(),MD5Utils.stringToMD5(user.getUpwd()),user.getTel(),user.getEmail(),
                user.getQq(),user.getVx(),user.getClasses(),user.getRegistrytime(),
                user.getEndtime(),user.getStatus());
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("uid", user.getUid());
        return userMapper.updateByExampleSelective(u, example);
    }
}

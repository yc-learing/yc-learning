package com.yc.learning.service;

import com.github.pagehelper.PageHelper;
import com.yc.learning.domain.PageDomain;
import com.yc.learning.domain.UserDomain;
import com.yc.learning.entity.User;
import com.yc.learning.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import  com.yc.learning.dao.impl.UserMapper;

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

    @Transactional(readOnly = true)
    @Override
    public PageDomain<User> listByPage(UserDomain domain) {
        Example example =new Example(User.class);  //条件
        //分页查询条件
        PageHelper.startPage(domain.getPage(), domain.getPageSize());
        //排序条件
        //Criteria：查询的规则
        Example.Criteria c = example.createCriteria();
        if (CommonUtils.isNotNull(domain.getUid())) {
            //条件创建  where 1= 1 and description like '%xxx%'

            c.andLike("description", "%" + domain.getUid() + "%");
        }
        return  null;
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

        /**
         *TODO:uuid生成
         */
        this.UserMapper.insert(user);
        domain.setUid(user.getUid());
    }
}

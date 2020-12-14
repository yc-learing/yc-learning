package com.yc.learning.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yc.learning.annotaion.RedisAnnotation;
import com.yc.learning.dao.impl.UserMapper;
import com.yc.learning.domain.PageDomain;
import com.yc.learning.domain.UserDomain;
import com.yc.learning.entity.User;
import com.yc.learning.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class BackModule_UserService extends UserServiceImpl{
    @Autowired(required = false)
    private UserMapper userMapper;

    @Transactional(readOnly = true)
    @RedisAnnotation(useRedis = true)
    public PageDomain<UserDomain> findByPage(UserDomain userDomain,Integer page, Integer pageSize){
        Example example = new Example(User.class);   //条件
        //分页条件设置
        PageHelper.startPage(userDomain.getPage(), userDomain.getPageSize());
        //排序条件
        example.setOrderByClause("uid desc");
        //Criteria:查询的规则
        Example.Criteria c = example.createCriteria();
        if (CommonUtils.isNotNull(userDomain.getUname())) {
            //条件创建 where 1=1 and pname like '%xx%';
            c.andLike("uname", "%" + userDomain.getUname()+ "%");
        }
        //long total = picMapper.selectCountByExample(example);
        // PageInfo: 分页的结果   总记录数，第几页，每页多少条条，上一页，下一页， 总共多少页.
        PageInfo<User> pageInfo = new PageInfo<User>(userMapper.selectByExample(example));
        PageDomain<UserDomain> pageDomain = new PageDomain<UserDomain>();
        pageDomain.setTotal(pageInfo.getTotal());
        pageDomain.setPage(pageInfo.getPageNum());
        pageDomain.setPageSize(userDomain.getPageSize());
        pageDomain.setTotalPages(pageInfo.getPages());
        //List<Pic> list = picMapper.selectByExample(example);
        List<UserDomain> list = new ArrayList<UserDomain>();
        //从pageInfo中取记录数
        if (pageInfo.getList() != null) {
            for (User u : pageInfo.getList()) {
                UserDomain ud = new UserDomain(u.getUid(), u.getUname(),u.getUpwd(),u.getTel(),u.getEmail(),
                        u.getQq(),u.getVx(),u.getClasses(),u.getRegistrytime(),u.getEndtime(),u.getStatus());
                ud.setPage(userDomain.getPage());
                ud.setPageSize(userDomain.getPageSize());
                list.add(ud);
            }
        }
        pageDomain.setData(list);
        return pageDomain;
    }
}

package com.yc.learning.service;

import com.github.pagehelper.PageHelper;
import com.yc.learning.dao.impl.AdminMapper;
import com.yc.learning.domain.AdminDomain;
import com.yc.learning.domain.PageDomain;
import com.yc.learning.entity.Admin;
import com.yc.learning.entity.Exam;
import com.yc.learning.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
@Transactional
public class AdminServiceImpl implements  AdminService{

    @Autowired(required = false)
    private AdminMapper adminMapper;

    @Transactional(readOnly = true)
    @Override
    public List<Admin> findAll() {
        List<Admin> list = adminMapper.selectAll();

        return list;
    }

    @Transactional(readOnly = true)
    @Override
    public PageDomain<Admin> listByPage(AdminDomain picDomain) {
        Example example =new Example(Admin.class);  //条件
        //分页查询条件
        PageHelper.startPage(picDomain.getPage(), picDomain.getPageSize());
        //排序条件
        //Criteria：查询的规则
        Example.Criteria c = example.createCriteria();
        if (CommonUtils.isNotNull(picDomain.getAname())) {
            //条件创建  where 1= 1 and description like '%xxx%'

            c.andLike("description", "%" + picDomain.getAname() + "%");
        }
        return  null;
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public Admin findOne(Integer id) {
        return null;
    }

    @Override
    public int update(Admin admin) {
        return 0;
    }
}

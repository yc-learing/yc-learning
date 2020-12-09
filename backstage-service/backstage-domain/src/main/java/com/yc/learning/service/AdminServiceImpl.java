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
            this.adminMapper.deleteByPrimaryKey(id);
    }

    @Override
    public AdminDomain findOne(Integer id) {
        Admin admin =this.adminMapper.selectByPrimaryKey(id);
        AdminDomain adminDomain = new AdminDomain(admin.getAid(), admin.getAname(), admin.getApwd(), admin.getStatus());
        return adminDomain;
    }

    @Override
    public void insert(AdminDomain admin) {
        Admin a = new Admin();
        a.setAname(admin.getAname());
        a.setApwd(admin.getApwd());
        a.setStatus(admin.getStatus());
        this.adminMapper.insert(a);
        // 在这里  mybatis完成了两步操作: 1. insert   2. select 到最新的id后，存到admin中
        //admin中的id已经获取到
        //关键:
        admin.setAid(a.getAid());
    }
}

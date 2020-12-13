package com.yc.learning.service;

import com.yc.learning.dao.impl.AdminMapper;
import com.yc.learning.domain.AdminDomain;
import com.yc.learning.entity.Admin;
import com.yc.learning.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        a.setApwd(MD5Utils.stringToMD5(admin.getApwd()));
        a.setStatus(admin.getStatus());
        this.adminMapper.insert(a);
        // 在这里  mybatis完成了两步操作: 1. insert   2. select 到最新的id后，存到admin中
        //admin中的id已经获取到
        //关键:
        admin.setAid(a.getAid());
    }
}

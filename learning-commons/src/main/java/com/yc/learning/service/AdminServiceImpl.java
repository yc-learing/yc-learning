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
    public int delete(Integer id) {
            return  adminMapper.deleteByPrimaryKey(id);
    }

    @Override
    public AdminDomain findOne(Integer id) {
        Admin admin =this.adminMapper.selectByPrimaryKey(id);
        AdminDomain adminDomain = new AdminDomain(admin.getAid(), admin.getAname(), admin.getApwd(), admin.getStatus());
        return adminDomain;
    }

    @Override
    public int insert(AdminDomain admin) {
        Admin a = new Admin();
        a.setAname(admin.getAname());
        a.setApwd(MD5Utils.stringToMD5(admin.getApwd()));
        a.setStatus(admin.getStatus());
        return  adminMapper.insert(a);

    }

    @Override
    public int update(Integer aid, Object value, String field) {
        return 0;
    }


    public int update( Integer aid,String value,String field) {
        if("apwd".equals(field)){
            value=MD5Utils.stringToMD5((String) value);
        }
        return adminMapper.update(aid, value, field);
    }
}

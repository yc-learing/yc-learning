package com.yc.learning.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yc.learning.dao.impl.AdminMapper;
import com.yc.learning.domain.AdminDomain;
import com.yc.learning.domain.PageDomain;
import com.yc.learning.entity.Admin;
import com.yc.learning.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class BackModule_AdminService extends AdminServiceImpl {

    @RedisAnnotation(deleteRedis = true)
    @Override
    public int update(AdminDomain admin) {
        return super.update(admin);
    }


    @Autowired(required = false)
    private AdminMapper adminMapper;

    @Transactional(readOnly = true)
    public PageDomain<AdminDomain> findByPage(AdminDomain adminDomain) {
        Example example = new Example(Admin.class);   //条件
        //分页条件设置
        PageHelper.startPage(adminDomain.getPage(), adminDomain.getPageSize());
        //排序条件
        example.setOrderByClause("aid desc");
        //  Criteria: 查询的规则
        Example.Criteria c = example.createCriteria();
        if (CommonUtils.isNotNull(adminDomain.getAname())) {
            //条件创建    where 1=1 and description like '%xx%';
            c.andLike("aname", "%" + adminDomain.getAname() + "%");
        }
        if (CommonUtils.isNotNull(adminDomain.getStatus())) {
            //条件创建    where 1=1 and description like '%xx%';
            c.andLike("status", "%" + adminDomain.getStatus() + "%");
        }
        // PageInfo: 分页的结果   总记录数，第几页，每页多少条条，上一页，下一页， 总共多少页.
        PageInfo<Admin> pageInfo = new PageInfo<Admin>(adminMapper.selectByExample(example));
        PageDomain<AdminDomain> pageDomain = new PageDomain<AdminDomain>();
        pageDomain.setTotal(pageInfo.getTotal());
        pageDomain.setPage(pageInfo.getPageNum());
        pageDomain.setTotalPages(pageInfo.getPages());
        //List<Pic> list = picMapper.selectByExample(example);
        List<AdminDomain> r = new ArrayList<AdminDomain>();
        //从pageInfo中取记录数
        if (pageInfo.getList()!= null) {
            for (Admin a : pageInfo.getList()) {
                AdminDomain ad = new AdminDomain(a.getAid(), a.getAname(),a.getApwd(),a.getStatus());
                r.add(ad);
            }
        }
        pageDomain.setData(r);
        return pageDomain;
    }


}


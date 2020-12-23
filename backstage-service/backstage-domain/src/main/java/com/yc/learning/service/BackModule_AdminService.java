package com.yc.learning.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yc.learning.annotaion.RedisAnnotation;
import com.yc.learning.dao.impl.AdminMapper;
import com.yc.learning.domain.AdminDomain;
import com.yc.learning.domain.PageDomain;
import com.yc.learning.entity.Admin;
import com.yc.learning.util.CommonUtils;
import com.yc.learning.util.MD5Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class BackModule_AdminService extends AdminServiceImpl {

    private static Logger logger = LoggerFactory.getLogger(BackModule_AdminService.class);

    @Autowired(required = false)
    private AdminMapper adminMapper;

    @Autowired(required = false)
    private RedisTemplate redisTemplate;


    @RedisAnnotation(deleteRedis = true)
    @Override
    public int update( Integer aid,String value,String field) {
        return   super.update(aid,value,field);
    }

    @Transactional(readOnly = true)
    public Admin login(Admin admin) {
        Example example = new Example(Admin.class);   //条件
        example.createCriteria().andEqualTo("aname", admin.getAname()).andEqualTo("apwd", MD5Utils.stringToMD5(admin.getApwd()));
        List<Admin> admins = adminMapper.selectByExample(example);
        return admins.size() == 0 ? null : admins.get(0);
    }

    public Admin check(String token) {
        if(token==null){
            return null;
        }
        ValueOperations<String,Admin> valueOperations = redisTemplate.opsForValue();
        logger.info("从reidis查询的键为：-->"+token);
        Admin admin = valueOperations.get(token);
        System.err.println(admin);
        return admin;
    }

    @Transactional(readOnly = true)
    @RedisAnnotation(useRedis = true)
    public PageDomain<AdminDomain> findByPage(AdminDomain adminDomain,Integer page, Integer pageSize) throws InstantiationException, IllegalAccessException {
//        ValueOperations<String, PageDomain<AdminDomain>> operations = redisTemplate.opsForValue();
//        String key ="BackModule_AdminService"+adminDomain.getAid();
//        boolean haskey = redisTemplate.hasKey("BackModule_AdminService"+adminDomain.getAid());
//        if (haskey) {
//            PageDomain<AdminDomain> redis = operations.get(key);
//            System.out.println(redis);
//            System.out.println("从缓存中获得数据："+redis);
//            System.out.println("------------------------------------");
//            return redis;
//        }     前置增强代码
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
        pageDomain.setPageSize(adminDomain.getPageSize());
        pageDomain.setTotalPages(pageInfo.getPages());
        //List<Pic> list = picMapper.selectByExample(example);
        List<AdminDomain> r = new ArrayList<AdminDomain>();
        //从pageInfo中取记录数
        if (pageInfo.getList()!= null) {
            for (Admin a : pageInfo.getList()) {
                AdminDomain ad = new AdminDomain(a.getAid(), a.getAname(),a.getApwd(),a.getStatus());
                ad.setPage(adminDomain.getPage());
                ad.setPageSize(adminDomain.getPageSize());
                r.add(ad);
            }
        }
        pageDomain.setData(r);
//        operations.set(key,pageDomain);   后置增强代码
        return pageDomain;
    }


    public int logout(String token) {
        logger.info("删除token为："+token);
        Boolean delete = redisTemplate.delete(token);
        if(delete==true){
            return 1;
        }
        return 0;
    }
}


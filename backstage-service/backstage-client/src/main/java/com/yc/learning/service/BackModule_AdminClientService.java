package com.yc.learning.service;

import com.alibaba.druid.support.json.JSONUtils;
import com.google.gson.Gson;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yc.learning.VO.AdminLoginVo;
import com.yc.learning.client.BackModule_AdminClient;
import com.yc.learning.domain.AdminDomain;
import com.yc.learning.entity.Admin;
import com.yc.learning.util.MD5Utils;
import com.yc.learning.util.TokenUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import sun.security.provider.MD5;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author liyan
 * @create 2020-12-2020/12/13-17:26
 */
@Service
public class BackModule_AdminClientService {
    private static final Logger log = LoggerFactory.getLogger(BackModule_AdminClientService.class);
    @Autowired
    private BackModule_AdminClient adminClient;

    @Autowired
    private RedisTemplate redisTemplate;


    @HystrixCommand(fallbackMethod = "findOneFallback")
    public String findOne(Integer id)  {
        return adminClient.findOne(id);
    }

    private String findOneFallback(Integer id) {
        Map map = new HashMap();
        map.put("code", "0");
        map.put("msg", "服务异常");
        return new Gson().toJson(map);
    }

    @HystrixCommand(fallbackMethod = "findByPageFallback")
    public String findByPage(Integer page, Integer pageSize,String aname) {
            return adminClient.findByPage(page,pageSize,aname);
    }

    private String findByPageFallback(Integer page, Integer pageSize, String aname) {
        Map map = new HashMap();
        map.put("code", "0");
        map.put("msg", "服务异常");
        return new Gson().toJson(map);
    }

    @HystrixCommand(fallbackMethod = "insertFallback")
    public String insert(AdminDomain adminDomain) {
            return adminClient.insert(adminDomain);
    }

    private String insertFallback(AdminDomain picDomain) {
        Map map = new HashMap();
        map.put("code", "0");
        map.put("msg", "服务异常，无法添加");
        return new Gson().toJson(map);
    }

    @HystrixCommand(fallbackMethod = "deleteFallback")
    public String delete(Integer id) {
        return adminClient.delete(id);
    }

    private String deleteFallback(Integer id) {
        Map map = new HashMap();
        map.put("code", "0");
        map.put("msg", "服务异常，无法删除" + id);
        return new Gson().toJson(map);
    }

    @HystrixCommand(fallbackMethod = "updateFallback")
    public String update(Integer aid,String value,String field) {
        System.out.println("修改clientservice");
        return adminClient.update(aid,value,field);
    }

    private String updateFallback(Integer aid,String value,String field) {
        Map map = new HashMap();
        map.put("code", "0");
        map.put("msg", "服务异常，无法更新");
        return new Gson().toJson(map);
    }

    @HystrixCommand(fallbackMethod = "loginFallback")
    public String login(Admin admin) {
        System.out.println("登录用户");
        String login = adminClient.login(admin);
        System.err.println(login);
        try {

            if(login!=null){
                Map parse=(HashMap)JSONUtils.parse(login);
                Integer code = (Integer) parse.get("code");
                if(code==1){
                    AdminLoginVo adminLoginVo = adminLoginVo(admin);
                    String s1 = new Gson().toJson(adminLoginVo);
                    System.out.println(s1);
                    return s1;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return login;
    }

    private String loginFallback(Admin admin) {
        Map map = new HashMap();
        map.put("code", "0");
        map.put("msg", "服务异常，无法登录");
        return new Gson().toJson(map);
    }


    /**
     * 生成AdminVO
     * @param aname
     * @param apwd
     * @return
     */
    private AdminLoginVo adminLoginVo(Admin admin){
        String aname = admin.getAname();
        String apwd = admin.getApwd();
        String adminToken = TokenUtils.createToken(aname,apwd);
        admin.setApwd(MD5Utils.stringToMD5(apwd));
        System.err.println(admin);
        redisTemplate.opsForValue().set(
                String.format("GODZILLA:ADMIN:TOKEN:%s", adminToken),
                admin,60*2, TimeUnit.MINUTES
        );
        AdminLoginVo adminLoginVo =new AdminLoginVo(admin);
        adminLoginVo.setToken("GODZILLA:ADMIN:TOKEN:"+adminToken);
        return adminLoginVo;
    }

    public String check(String token) {
        log.info("检查用户是否登录"+token);
        String check = adminClient.check(token);
        return check;
    }
}

package com.yc.learning.service;

import com.google.gson.Gson;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yc.learning.client.BackModule_AdminClient;
import com.yc.learning.domain.AdminDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liyan
 * @create 2020-12-2020/12/13-17:26
 */
@Service
public class BackModule_AdminClientService {
    @Autowired
    private BackModule_AdminClient adminClient;


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
    public String update(Integer aid,Object value,String field) {
        return adminClient.update(aid,value,field);
    }

    private String updateFallback(Integer aid,Object value,String field) {
        Map map = new HashMap();
        map.put("code", "0");
        map.put("msg", "服务异常，无法更新");
        return new Gson().toJson(map);
    }

}

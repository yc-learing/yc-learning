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
        return new Gson().toJson(adminClient.findOne(id));
    }

    private String findOneFallback(Integer id) {
        Map map = new HashMap();
        map.put("code", "0");
        map.put("msg", "服务异常");
        return new Gson().toJson(map);
    }

    @HystrixCommand(fallbackMethod = "findByPageFallback")
    public String findByPage(Integer page, Integer pageSize,String aname) {
            return new Gson().toJson(adminClient.findByPage(page,pageSize,aname));


    }

    private String findByPageFallback(Integer page, Integer pageSize, String aname) {
        Map map = new HashMap();
        map.put("code", "0");
        map.put("msg", "服务异常");
        return new Gson().toJson(map);
    }

    @HystrixCommand(fallbackMethod = "insertFallback")
    public String insert(AdminDomain adminDomain) {
            return new Gson().toJson(adminClient.insert(adminDomain));
    }

    private String insertFallback(AdminDomain picDomain) {
        Map map = new HashMap();
        map.put("code", "0");
        map.put("msg", "服务异常，无法添加");
        return new Gson().toJson(map);
    }

    @HystrixCommand(fallbackMethod = "deleteFallback")
    public String delete(Integer id) {
        return new Gson().toJson(adminClient.delete(id));
    }

    private String deleteFallback(Integer id) {
        Map map = new HashMap();
        map.put("code", "-1");
        map.put("msg", "服务异常，无法删除" + id);
        return new Gson().toJson(map);
    }

}

package com.yc.learning.service;

import com.google.gson.Gson;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yc.learning.client.BackModule_UserClient;
import com.yc.learning.domain.UserDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class BackModule_UserClientService {
    @Autowired
    private BackModule_UserClient userClient;

    @HystrixCommand(fallbackMethod = "findByPageFallback")
    public String findByPage(Integer page, Integer pageSize,String uname) {
        return userClient.findByPage(page,pageSize,uname);
    }

    private String findByPageFallback(Integer page, Integer pageSize, String uname) {
        Map map = new HashMap();
        map.put("code", "0");
        map.put("msg", "服务异常");
        return new Gson().toJson(map);
    }

    @HystrixCommand(fallbackMethod = "findOneFallback")
    public String findOne(Integer id)  {
        return userClient.findOne(id);
    }

    private String findOneFallback(Integer id) {
        Map map = new HashMap();
        map.put("code", "0");
        map.put("msg", "服务异常");
        return new Gson().toJson(map);
    }

    @HystrixCommand(fallbackMethod = "insertFallback")
    public String insert(UserDomain userDomain) {
        return userClient.insert(userDomain);
    }

    private String insertFallback(UserDomain userDomain) {
        Map map = new HashMap();
        map.put("code", "0");
        map.put("msg", "服务异常，无法添加");
        return new Gson().toJson(map);
    }

    @HystrixCommand(fallbackMethod = "deleteFallback")
    public String delete(Integer id) {
        return userClient.delete(id);
    }

    private String deleteFallback(Integer id) {
        Map map = new HashMap();
        map.put("code", "0");
        map.put("msg", "服务异常，无法删除" + id);
        return new Gson().toJson(map);
    }

    @HystrixCommand(fallbackMethod = "updateFallback")
    public String update(Integer uid,String value,String field) {
        return userClient.update(uid,value,field);
    }

    private String updateFallback(Integer uid,String value,String field) {
        Map map = new HashMap();
        map.put("code", "0");
        map.put("msg", "服务异常，无法更新");
        return new Gson().toJson(map);
    }
}

package com.yc.learning.service;

import com.google.gson.Gson;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yc.learning.client.BackModule_RecordClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class BackModule_RecordClientService {
    @Autowired
    private BackModule_RecordClient recordClient;

    @HystrixCommand(fallbackMethod = "findByPageFallback")
    public String findByPage(Integer page,Integer pageSize){
        return recordClient.findByPage(page,pageSize);
    }

    private String findByPageFallback(Integer page,Integer pageSize){
        Map map = new HashMap();
        map.put("code", "0");
        map.put("msg", "服务异常");
        return new Gson().toJson(map);
    }
}

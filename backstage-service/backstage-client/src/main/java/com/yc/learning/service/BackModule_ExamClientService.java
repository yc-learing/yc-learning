package com.yc.learning.service;

import com.google.gson.Gson;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yc.learning.client.BackModule_ExamClient;
import com.yc.learning.domain.ExamDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class BackModule_ExamClientService {
    @Autowired
    private BackModule_ExamClient examClient;

    @HystrixCommand(fallbackMethod = "findByPageFallback")
    public String findByPage(Integer page,Integer pageSize,String ename){
        return examClient.findByPage(page,pageSize,ename);
    }

    private String findByPageFallback(Integer page,Integer pageSize,String ename){
        Map map = new HashMap();
        map.put("code", "0");
        map.put("msg", "服务异常");
        return new Gson().toJson(map);
    }

    @HystrixCommand(fallbackMethod = "findByExidFallback")
    public String findByExid(Integer exid){
        return examClient.findByExid(exid);
    }

    private String findByExidFallback(Integer exid){
        Map map = new HashMap();
        map.put("code", "0");
        map.put("msg", "服务异常");
        return new Gson().toJson(map);
    }

    @HystrixCommand(fallbackMethod = "addExamFallback")
    public String addExam(ExamDomain examDomain){
        return examClient.addExam(examDomain);
    }

    private String addExamFallback(ExamDomain examDomain){
        Map map = new HashMap();
        map.put("code", "0");
        map.put("msg", "服务异常");
        return new Gson().toJson(map);
    }
}

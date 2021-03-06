package com.yc.learning.service;

import com.google.gson.Gson;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yc.learning.client.BackModule_CourseClient;
import com.yc.learning.domain.CourseDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class BackModule_CourseClientService {
    @Autowired
    private BackModule_CourseClient courseClient;

    @HystrixCommand(fallbackMethod = "findByPageFallback")
    public String findByPage(Integer page,Integer pageSize,String coursename){
        return courseClient.findByPage(page,pageSize,coursename);
    }

    private String findByPageFallback(Integer page,Integer pageSize,String coursename){
        Map map = new HashMap();
        map.put("code", "0");
        map.put("msg", "服务异常");
        return new Gson().toJson(map);
    }

    @HystrixCommand(fallbackMethod = "findCoursenameWithCnameFallback")
    public String findCoursenameWithCname(){
        return courseClient.findCoursenameWithCname();
    }

    private String findCoursenameWithCnameFallback(){
        Map map = new HashMap();
        map.put("code", "0");
        map.put("msg", "服务异常");
        return new Gson().toJson(map);
    }

    @HystrixCommand(fallbackMethod = "createFallback")
    public String create(CourseDomain courseDomain) {
        return courseClient.create(courseDomain);
    }

    private String createFallback(CourseDomain courseDomain) {
        Map map = new HashMap();
        map.put("code", "-1");
        map.put("msg", "服务异常，无法添加" + courseDomain.getPic());
        return new Gson().toJson(map);
    }
}

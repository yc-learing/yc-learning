package com.yc.learning.service;

import com.google.gson.Gson;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yc.learning.client.Learning_ExamModule_ExamClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class Learning_ExamModule_ExamClientService {
    @Autowired
    private Learning_ExamModule_ExamClient examClient;

    @HystrixCommand(fallbackMethod = "findExamFallBack")
    public String findExam( String classes){
        return examClient.findExam(classes);
    }

    private String findExamFallBack(String classes){
        Map map = new HashMap();
        map.put("code", "0");
        map.put("msg", "服务异常");
        return new Gson().toJson(map);
    }

    @HystrixCommand(fallbackMethod = "findByExidFallback")
    public String findExerciesByExam(Integer exid){
        return examClient.findExerciesByExam(exid);
    }

    private String findByExidFallback(Integer exid){
        Map map = new HashMap();
        map.put("code", "0");
        map.put("msg", "服务异常");
        return new Gson().toJson(map);
    }
}

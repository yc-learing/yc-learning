package com.yc.learning.service;

import com.google.gson.Gson;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yc.learning.client.BackModule_ExercisesClient;
import com.yc.learning.domain.ExercisesDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class BackModule_ExercisesClientService {
    @Autowired
    private BackModule_ExercisesClient exercisesClient;

    @HystrixCommand(fallbackMethod = "findByPageFallback")
    public String findByPage(Integer page,Integer pageSize,String content){
        return exercisesClient.findByPage(page,pageSize,content);
    }

    private String findByPageFallback(Integer page,Integer pageSize,String content){
        Map map = new HashMap();
        map.put("code", "0");
        map.put("msg", "服务异常");
        return new Gson().toJson(map);
    }

    @HystrixCommand(fallbackMethod = "insertFallback")
    public String insert(ExercisesDomain exercisesDomain) {
        return exercisesClient.insert(exercisesDomain);
    }

    private String insertFallback(ExercisesDomain exercisesDomain) {
        Map map = new HashMap();
        map.put("code", "0");
        map.put("msg", "服务异常，无法添加");
        return new Gson().toJson(map);
    }

    @HystrixCommand(fallbackMethod = "findVoByPageFallback")
    public String findVoByPage(Integer page,Integer pageSize,Integer chid,Integer cid){
        return exercisesClient.findVoByPage(page,pageSize,chid,cid);
    }

    private String findVoByPageFallback(Integer page,Integer pageSize,Integer chid,Integer cid){
        Map map = new HashMap();
        map.put("code", "0");
        map.put("msg", "服务异常");
        return new Gson().toJson(map);
    }
}

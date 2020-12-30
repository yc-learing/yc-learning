package com.yc.learning.controller;

import com.google.gson.Gson;
import com.yc.learning.domain.ExamDomain;
import com.yc.learning.domain.PageDomain;
import com.yc.learning.entity.Exercises;
import com.yc.learning.service.BackModule_ExamService;
import com.yc.learning.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * @author liyan
 * @create 2020-12-2020/12/13-9:17
 */
@RestController
@RequestMapping("/back-exam")
public class BackModule_ExamController {
    @Autowired(required = false)
    private BackModule_ExamService examService;

    @RequestMapping(value = "/findByPage",method = RequestMethod.GET)
    public CompletableFuture<String> findByPage(Integer page, Integer pageSize, String ename){
        return CompletableFuture.supplyAsync(()->{
            Map<String ,Object> map=new HashMap<>();
            try {
                ExamDomain examDomain=new ExamDomain();
                if (CommonUtils.isNotNull(page)) {
                    examDomain.setPage(page);
                }
                if (CommonUtils.isNotNull(pageSize)) {
                    examDomain.setPageSize(pageSize);
                }
                if (CommonUtils.isNotNull(ename)) {
                    examDomain.setAname(ename);
                }
                PageDomain<ExamDomain> pageDomain=examService.findByPage(examDomain,page,pageSize);
                map.put("code",1);
                map.put("data",pageDomain);
                return new Gson().toJson(map);
            }catch (Exception e){
                map.put("code",0);
                map.put("msg","程序错误");
                e.printStackTrace();
                return new Gson().toJson(map);
            }
        });
    }

    @RequestMapping(value = "/findByExid",method = RequestMethod.GET)
    public CompletableFuture<String> findByExid(Integer exid){
        return CompletableFuture.supplyAsync(()->{
            Map<String ,Object> map=new HashMap<>();
            try {
                List<Exercises> list=examService.findByExid(exid);
                map.put("code",1);
                map.put("data",list);
                return new Gson().toJson(map);
            }catch (Exception e){
                map.put("code",0);
                map.put("msg","程序错误");
                e.printStackTrace();
                return new Gson().toJson(map);
            }
        });
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public CompletableFuture<String> addExam(@RequestBody ExamDomain domain){
        return CompletableFuture.supplyAsync(()->{
            Map<String ,Object> map=new HashMap<>();
            try {
                examService.insert(domain);
                map.put("code",1);
                map.put("msg","添加成功");
                return new Gson().toJson(map);
            }catch (Exception e){
                map.put("code",0);
                map.put("msg","程序错误");
                e.printStackTrace();
                return new Gson().toJson(map);
            }
        });
    }
}

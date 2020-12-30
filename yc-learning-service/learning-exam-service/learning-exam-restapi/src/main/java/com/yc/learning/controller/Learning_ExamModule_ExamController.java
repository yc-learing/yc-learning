package com.yc.learning.controller;

import com.google.gson.Gson;
import com.yc.learning.entity.Exam;
import com.yc.learning.entity.Record;
import com.yc.learning.service.Learning_ExamModule_ExamService;
import com.yc.learning.service.Learning_ExamModule_RecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/exam")
public class Learning_ExamModule_ExamController {
    private static Logger logger = LoggerFactory.getLogger(Learning_ExamModule_ExamController.class);

    @Autowired(required = false)
    private Learning_ExamModule_ExamService examService;

    @Autowired(required = false)
    private Learning_ExamModule_RecordService recordService;

    @RequestMapping(value = "/findexam", method = RequestMethod.GET)
    public CompletableFuture<String> findexam(@RequestParam(value = "classes",required = false)String classes) {
        return CompletableFuture.supplyAsync(() -> {
            Map<String, Object> map = new HashMap<>();
            try {

                List<Exam> byClasses = examService.findByClasses(classes);
                map.put("code","1");
                map.put("data",byClasses);
                return new Gson().toJson(map);
            } catch (Exception e) {
                map.put("code",0);
                map.put("msg","程序错误");
                e.printStackTrace();
                return new Gson().toJson(map);
            }
        });
    }


    @RequestMapping(value = "/findExerciesByExam",method = RequestMethod.GET)
    public CompletableFuture<String> findExerciesByExam(@RequestParam(value = "exid",required = false)Integer exid) {
        return CompletableFuture.supplyAsync(() -> {
            Map<String, Object> map = new HashMap<>();
            try {
                List<Object> exerciesByExam = examService.findExerciesByExam(exid);

                map.put("code","1");
                map.put("data",exerciesByExam);
                return new Gson().toJson(map);
            } catch (Exception e) {
                map.put("code",0);
                map.put("msg","程序错误");
                e.printStackTrace();
                return new Gson().toJson(map);
            }
        });
    }


    @RequestMapping(value = "/addRecord",method = RequestMethod.POST)
    public CompletableFuture<String> addRecord(@RequestBody Record record) {
        return CompletableFuture.supplyAsync(() -> {
            Map<String, Object> map = new HashMap<>();
            try {
                //先查找有没有考过试卷
                int count = recordService.CountRecordByuid_exid(record);
                if(count<=0){

                    int update= recordService.insert(record);
                    map.put("code","1");
                    if(update==1){
                        map.put("msg","交卷成功！！");
                    }else{
                        map.put("msg","交卷失败,请联系管理员");
                    }
                    return new Gson().toJson(map);
                }else{
                    int update=recordService.updateRecordByExid_Uid(record);
                    map.put("code","1");
                    if(update==1){
                        map.put("msg","交卷成功！！");
                    } else{
                        map.put("msg","交卷失败,请联系管理员");
                    }
                    return new Gson().toJson(map);

                }


            } catch (Exception e) {
                map.put("code",0);
                map.put("msg","程序错误");
                e.printStackTrace();
                return new Gson().toJson(map);
            }
        });
    }




}

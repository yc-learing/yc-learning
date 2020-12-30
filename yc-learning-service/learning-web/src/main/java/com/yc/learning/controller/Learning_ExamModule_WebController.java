package com.yc.learning.controller;

import com.yc.learning.entity.Record;
import com.yc.learning.future.Learning_ExamModule_ExamFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/exam")
public class Learning_ExamModule_WebController {

    @Autowired(required = false)
    private Learning_ExamModule_ExamFuture examFuture;

    @RequestMapping(value = "findExam",method = RequestMethod.GET)
    public CompletableFuture<String> findExam(@RequestParam(value = "classes",required = false) String classes) {
        return examFuture.findExam(classes);
    }

    @RequestMapping(value = "findExerciesByExam",method = RequestMethod.GET)
    public CompletableFuture<String> findExerciesByExam(@RequestParam(value = "exid",required = false) Integer exid) {
        return examFuture.findByExid(exid);
    }

    @RequestMapping(value = "addRecord",method = RequestMethod.POST)
    public CompletableFuture<String> addRecord(@RequestBody Record record) {
        System.out.println(record);
        return examFuture.addRecord(record);
    }

}

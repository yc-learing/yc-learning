package com.yc.learning.web.controller;

import com.yc.learning.domain.ExamDomain;
import com.yc.learning.future.BackModule_ExamFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/exam")
public class BackModule_ExamWebController {

    @Autowired(required = false)
    private BackModule_ExamFuture examFuture;

    @RequestMapping(value = "findByPage",method = RequestMethod.GET)
    public CompletableFuture<String> findByPage(@RequestParam(value = "page",required = false) Integer page, @RequestParam(value = "pageSize",required = false) Integer pageSize, @RequestParam(value = "ename",required = false) String ename) {
        return examFuture.findByPage(page, pageSize, ename);
    }

    //根据exid查询试卷详细信息
    @RequestMapping(value = "findByExid",method = RequestMethod.GET)
    public CompletableFuture<String> findByExid(@RequestParam(value = "exid",required = false) Integer exid) {
        return examFuture.findByExid(exid);
    }

    @RequestMapping(value = "add",method = RequestMethod.POST)
    public CompletableFuture<String> addExam(@RequestBody ExamDomain examDomain) {
        System.out.println(examDomain);
        return examFuture.addExam(examDomain);
    }
}

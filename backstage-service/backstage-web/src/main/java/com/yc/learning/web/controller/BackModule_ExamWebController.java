package com.yc.learning.web.controller;

import com.yc.learning.future.BackModule_ExamFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}

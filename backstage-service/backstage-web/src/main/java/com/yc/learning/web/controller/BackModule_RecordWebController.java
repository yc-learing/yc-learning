package com.yc.learning.web.controller;

import com.yc.learning.future.BackModule_RecordFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/record")
public class BackModule_RecordWebController {
    @Autowired(required = false)
    private BackModule_RecordFuture recordFuture;

    @RequestMapping(value = "findByPage",method = RequestMethod.GET)
    public CompletableFuture<String> findByPage(@RequestParam(value = "page",required = false) Integer page, @RequestParam(value = "pageSize",required = false) Integer pageSize) {
        return recordFuture.findByPage(page, pageSize);
    }

    @RequestMapping(value = "findVoByPage",method = RequestMethod.GET)
    public CompletableFuture<String> findVoByPage(@RequestParam(value = "page",required = false) Integer page, @RequestParam(value = "pageSize",required = false) Integer pageSize,@RequestParam(value = "uname",required = false)String uname,@RequestParam(value = "ename",required = false)String ename) {
        return recordFuture.findVoByPage(page, pageSize,uname,ename);
    }
}

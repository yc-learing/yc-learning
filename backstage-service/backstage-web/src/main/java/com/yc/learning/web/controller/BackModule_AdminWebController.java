package com.yc.learning.web.controller;

import com.yc.learning.entity.Admin;
import com.yc.learning.future.BackModule_AdminFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/admin")
public class BackModule_AdminWebController {
    private static Logger logger = LoggerFactory.getLogger(BackModule_AdminWebController.class.getName());

    @Autowired(required = false)
    private BackModule_AdminFuture adminFuture;

    @RequestMapping(value = "findByPage",method = RequestMethod.GET)
    public CompletableFuture<String> findByPage(@RequestParam(value = "page",required = false) Integer page, @RequestParam(value = "pageSize",required = false) Integer pageSize, @RequestParam(value = "aname",required = false) String aname) {
        return adminFuture.findByPage(page, pageSize, aname);
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public CompletableFuture<String> update(
            @RequestParam(value = "aid") Integer aid,
            @RequestParam(value = "value") String value,
            @RequestParam(value = "field") String field){
        return adminFuture.update(aid,value,field);
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public CompletableFuture<String> login(
            @RequestBody Admin admin){
        logger.info(admin+"用户登录");
        return adminFuture.login(admin);
    }


    @RequestMapping(value = "check", method = RequestMethod.POST)
    public CompletableFuture<String> check(
            @RequestParam(value = "token",required = false) String token){
        logger.info("token为："+token);
        return adminFuture.check(token);
    }
}

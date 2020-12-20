package com.yc.learning.web.controller;

import com.yc.learning.future.BackModule_UserFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/user")
public class BackModule_UserWebController {
    @Autowired
    private BackModule_UserFuture userFuture;

    @RequestMapping(value = "findByPage",method = RequestMethod.GET)
    public CompletableFuture<String> findByPage(@RequestParam(value = "page",required = false) Integer page, @RequestParam(value = "pageSize",required = false) Integer pageSize, @RequestParam(value = "uname",required = false) String uname) {
        return userFuture.findByPage(page, pageSize, uname);
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public CompletableFuture<String> update(
            @RequestParam(value = "aid") Integer aid,
            @RequestParam(value = "value") String value,
            @RequestParam(value = "field") String field){
        return userFuture.update(aid,value,field);
    }
}

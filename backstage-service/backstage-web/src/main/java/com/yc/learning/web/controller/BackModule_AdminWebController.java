package com.yc.learning.web.controller;

import com.yc.learning.domain.AdminDomain;
import com.yc.learning.future.BackModule_AdminFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/admin")
public class BackModule_AdminWebController {
    @Autowired(required = false)
    private BackModule_AdminFuture adminFuture;

    @RequestMapping(value = "findByPage",method = RequestMethod.GET)
    public CompletableFuture<String> findByPage(@RequestParam(value = "page",required = false) Integer page, @RequestParam(value = "pageSize",required = false) Integer pageSize, @RequestParam(value = "aname",required = false) String aname) {
        return adminFuture.findByPage(page, pageSize, aname);
    }

    //注册
    @RequestMapping(value = "register",method = RequestMethod.POST)
    public CompletableFuture<String> register (@RequestBody AdminDomain adminDomain){
        return adminFuture.insert(adminDomain);
    }

    //删除
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public CompletableFuture<String> delete(@PathVariable Integer id){
        return adminFuture.delete(id);
    }



    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public CompletableFuture<String> update(
            @RequestParam(value = "aid") Integer aid,
            @RequestParam(value = "value") Object value,
            @RequestParam(value = "field") String field){

        return adminFuture.update(aid,value,field);
    }
}

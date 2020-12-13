package com.yc.learning.controller;

import com.google.gson.Gson;
import com.yc.learning.domain.PageDomain;
import com.yc.learning.domain.UserDomain;
import com.yc.learning.service.BackModule_UserService;
import com.yc.learning.util.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/back-user")
public class BackModule_UserController {
    private static Logger logger = LoggerFactory.getLogger(BackModule_AdminController.class);


    @Autowired(required = false)
    private BackModule_UserService userService;

    @RequestMapping(value = "/findByPage",method = RequestMethod.GET)
    public CompletableFuture<String> findByPage(Integer page, Integer pageSize, String uname){
        return CompletableFuture.supplyAsync(()->{
            Map<String ,Object> map=new HashMap<>();
            try {
                UserDomain userDomain=new UserDomain();
                if (CommonUtils.isNotNull(page)) {
                    userDomain.setPage(page);
                }
                if (CommonUtils.isNotNull(pageSize)) {
                    userDomain.setPageSize(pageSize);
                }
                if (CommonUtils.isNotNull(uname)) {
                    userDomain.setUname(uname);
                }
                PageDomain<UserDomain> pageDomain=userService.findByPage(userDomain);
                map.put("code",1);
                map.put("data",pageDomain);
                return new Gson().toJson(map);
            }catch (Exception e){
                map.put("code",0);
                map.put("data","程序错误");
                e.printStackTrace();
                return new Gson().toJson(map);
            }
        });
    }

    //注册
    @RequestMapping(value = "register",method = RequestMethod.POST)
    public CompletableFuture<String> register (@RequestBody UserDomain userDomain) throws Exception {
        return CompletableFuture.supplyAsync(() -> {
            Map<String, Object> map = new HashMap<>();
            try {
                userService.insert(userDomain);
                logger.info("新增->ID=" + userDomain.getUid());
                map.put("code", 1);
                map.put("data", "注册成功");
                return new Gson().toJson(map);
            }catch (Exception e) {
                map.put("code",0);
                map.put("data","注册失败");
                e.printStackTrace();
                return new Gson().toJson(map);
            }

        });
    }

    //删除
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public CompletableFuture<String> delete(@PathVariable Integer id) throws Exception {
        return CompletableFuture.supplyAsync(() -> {
            Map<String, Object> map = new HashMap<>();
            try{
                userService.delete(id);
                logger.info("删除->ID=" + id);
                map.put("code", 1);
                return new Gson().toJson(map);
            }catch (Exception e) {
                map.put("code",0);
                map.put("data","刪除失败");
                e.printStackTrace();
                return new Gson().toJson(map);
            }

        });
    }
}

package com.yc.learning.controller;

import com.alibaba.druid.support.json.JSONUtils;
import com.yc.learning.entity.User;
import com.yc.learning.future.UserModule_UserFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.concurrent.CompletableFuture;

/**
 * @author liyan
 * @create 2020-12-2020/12/28-18:58
 */
@RestController
@RequestMapping("/user")
public class UserModel_UserWebController {

    private static final Logger logger = LoggerFactory.getLogger(UserModel_UserWebController.class);

    @Autowired(required = false)
    private  UserModule_UserFuture userFuture;

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public CompletableFuture<String> login(
            @RequestBody User user){
        logger.info(user+"用户登录");
        return userFuture.login(user);
    }


    @RequestMapping(value = "check", method = RequestMethod.POST)
    public CompletableFuture<String> check(
            @RequestBody(required = false) String token){
        HashMap map= (HashMap) JSONUtils.parse(token);
        token = (String) map.get("token");
        System.err.println("web端传过来的token为："+token);
        logger.info("查询token值为："+token);
        if(token==null){
            return null;
        }
        return userFuture.check(token);
    }

    @RequestMapping(value = "logout", method = RequestMethod.POST)
    public CompletableFuture<String> logout(
            @RequestBody(required = false) String token){
        HashMap map= (HashMap) JSONUtils.parse(token);
        token = (String) map.get("token");
        System.err.println(token);
        logger.info("删除token值为："+token);
        if(token==null){
            return null;
        }
        return userFuture.logout(token);
    }

}

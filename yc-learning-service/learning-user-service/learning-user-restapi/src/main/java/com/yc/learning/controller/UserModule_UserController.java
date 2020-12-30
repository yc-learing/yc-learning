package com.yc.learning.controller;

import com.google.gson.Gson;
import com.yc.learning.entity.User;
import com.yc.learning.service.UserModule_UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * @author liyan
 * @create 2020-12-2020/12/27-18:35
 */
@RestController
@RequestMapping("/user")
public class UserModule_UserController {
    private static Logger logger = LoggerFactory.getLogger(UserModule_UserController.class);

    @Autowired(required = false)
    private UserModule_UserService userService;

    @RequestMapping(value = "login",method = RequestMethod.POST)
    public  CompletableFuture<String> login(@RequestBody User user)throws  Exception{
        return CompletableFuture.supplyAsync(() -> {
            Map<String, Object> map = new HashMap<>();
            try{
                User login = userService.login(user);
                if(login!=null){
                    Integer status = login.getStatus();
                    if(status==1){
                        logger.info(login+"登录成功");
                        map.put("code", 1);
                        map.put("msg","登录成功");
                    }
                    else{
                        logger.info(login+"该账户被冻结");
                        map.put("code",0);
                        map.put("msg","登录失败！该账户被冻结！！");
                    }
                    return new Gson().toJson(map);
                }else{
                    logger.info(user+"账户或密码错误");
                    map.put("code",0);
                    map.put("msg","登录失败！账户名或密码错误！！");
                    return new Gson().toJson(map);
                }

            }catch (Exception e) {
                map.put("code",0);
                map.put("msg","登录失败");
                e.printStackTrace();
                return new Gson().toJson(map);
            }
        });
    }



    //检查用户是否登录
    @RequestMapping(value = "/check",method = RequestMethod.POST)
    public CompletableFuture<String> check(@RequestBody(required = false)String token) throws Exception {
        return CompletableFuture.supplyAsync(() -> {
            Map<String, Object> map = new HashMap<>();
            try {
                User check = userService.check(token);
                System.err.println(check);
                logger.info("从redis查到登录的用户为:"+check);
                logger.info("检查用户是否登录->token=" + token);
                if(check==null){
                    map.put("code",0);
                    map.put("message","用户登录失效！！");
                    return new Gson().toJson(map);
                }
                map.put("code", 1);
                map.put("user",check);
                map.put("message","token查询登录用户成功！！");
                return new Gson().toJson(map);
            } catch (Exception e) {
                map.put("code", 0);
                map.put("data", "微服务不可用，请重新再试");
                e.printStackTrace();
                return new Gson().toJson(map);
            }

        });
    }

    //检查用户是否登录
    @RequestMapping(value = "/logout",method = RequestMethod.POST)
    public CompletableFuture<String> logout(@RequestBody(required = false)String token) throws Exception {
        return CompletableFuture.supplyAsync(() -> {
            Map<String, Object> map = new HashMap<>();
            try {
                int delete = userService.logout(token);
                map.put("code",1);
                map.put("msg","删除token成功！！");
                return new Gson().toJson(map);
            } catch (Exception e) {
                map.put("code", 0);
                map.put("data", "微服务不可用，请重新再试");
                e.printStackTrace();
                return new Gson().toJson(map);
            }

        });
    }



}

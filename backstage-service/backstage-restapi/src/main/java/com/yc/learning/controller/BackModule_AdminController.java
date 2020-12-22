package com.yc.learning.controller;

import com.google.gson.Gson;
import com.yc.learning.annotaion.RedisAnnotation;
import com.yc.learning.domain.AdminDomain;
import com.yc.learning.domain.PageDomain;
import com.yc.learning.entity.Admin;
import com.yc.learning.service.BackModule_AdminService;
import com.yc.learning.util.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/back-admin")
public class BackModule_AdminController {
    private static Logger logger = LoggerFactory.getLogger(BackModule_AdminController.class);

    @Autowired(required = false)
    private BackModule_AdminService adminService;


    @RedisAnnotation(deleteRedis = true)
    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public void deleteTest(){
    }

    @RequestMapping(value = "/findByPage", method = RequestMethod.GET)
    public CompletableFuture<String> findByPage(Integer page, Integer pageSize,  String aname, Integer status) {
        return CompletableFuture.supplyAsync(() -> {
            Map<String, Object> map = new HashMap<>();
            try {
                AdminDomain adminDomain = new AdminDomain();
                if (CommonUtils.isNotNull(page)) {
                    adminDomain.setPage(page);
                }
                if (CommonUtils.isNotNull(pageSize)) {
                    adminDomain.setPageSize(pageSize);
                }
                if (CommonUtils.isNotNull(aname)) {
                    adminDomain.setAname(aname);
                }
                if (CommonUtils.isNotNull(status)) {
                    adminDomain.setStatus(status);
                }
                PageDomain<AdminDomain> pageDomain = adminService.findByPage(adminDomain,page,pageSize);
                map.put("code", 1);
                map.put("data", pageDomain);
                return new Gson().toJson(map);
            } catch (Exception e) {
                map.put("code",0);
                map.put("msg","程序错误");
                e.printStackTrace();
                return new Gson().toJson(map);
            }
        });
    }

    //注册
    @RequestMapping(value = "register",method = RequestMethod.POST)
    public CompletableFuture<String> register (@RequestBody AdminDomain adminDomain) throws Exception {
        return CompletableFuture.supplyAsync(() -> {
            Map<String, Object> map = new HashMap<>();
            try {
                adminService.insert(adminDomain);
                logger.info("新增->ID=" + adminDomain.getAid());
                map.put("code", 1);
                map.put("msg", "注册成功");
                return new Gson().toJson(map);
            }catch (Exception e) {
                map.put("code",0);
                map.put("msg","注册失败");
                e.printStackTrace();
                return new Gson().toJson(map);
            }

        });
    }

    @RequestMapping(value = "login",method = RequestMethod.POST)
    public  CompletableFuture<String> login(@RequestBody Admin admin)throws  Exception{
        return CompletableFuture.supplyAsync(() -> {
            Map<String, Object> map = new HashMap<>();
            try{
                Admin login = adminService.login(admin);
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
                    logger.info(admin+"账户或密码错误");
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

    //修改
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public CompletableFuture<String> update(Integer aid,String value,String field) throws Exception {
        return CompletableFuture.supplyAsync(() -> {
            Map<String, Object> map = new HashMap<>();
            try{
                adminService.update( aid, value, field);
                logger.info("更新成功");
                map.put("code", 1);
                map.put("msg","更新成功");
                return new Gson().toJson(map);
            }catch (Exception e) {
                map.put("code",0);
                map.put("msg","更新失败");
                e.printStackTrace();
                return new Gson().toJson(map);
            }
        });
    }

    @RequestMapping(value = "login",method = RequestMethod.POST)
    public  CompletableFuture<String> login(@RequestBody Admin admin)throws  Exception{
        return CompletableFuture.supplyAsync(() -> {
            Map<String, Object> map = new HashMap<>();
            try{
                Admin login = adminService.login(admin);
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
                    logger.info(admin+"账户或密码错误");
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

    //删除
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public CompletableFuture<String> delete(@PathVariable Integer id) throws Exception {
        return CompletableFuture.supplyAsync(() -> {
            Map<String, Object> map = new HashMap<>();
            try{
                adminService.delete(id);
                logger.info("删除->ID=" + id);
                map.put("code", 1);
                map.put("msg","删除成功");
                return new Gson().toJson(map);
            }catch (Exception e) {
                map.put("code",0);
                map.put("msg","刪除失败");
                e.printStackTrace();
                return new Gson().toJson(map);
            }

        });
    }

    //检查用户是否登录
    @RequestMapping(value = "/check",method = RequestMethod.POST)
    public CompletableFuture<String> check(@RequestParam("token")String token) throws Exception {
        return CompletableFuture.supplyAsync(() -> {
            Map<String, Object> map = new HashMap<>();
            try {
                Admin check = adminService.check(token);
                System.err.println(check);
                logger.info("从redis查到登录的用户为:"+check);
                logger.info("检查用户是否登录->token=" + token);
                if(check==null){
                    map.put("code",0);
                    map.put("message","用户登录失效！！");
                    return new Gson().toJson(map);
                }
                map.put("code", 1);
                map.put("admin",check);
                map.put("message","token查询登录用户成功！！");
                return new Gson().toJson(map);
            } catch (Exception e) {
                map.put("code", 0);
                map.put("msg", "程序错误");
                e.printStackTrace();
                return new Gson().toJson(map);
            }

        });
    }

    //检查用户是否登录
    @RequestMapping(value = "/check",method = RequestMethod.POST)
    public CompletableFuture<String> check(@RequestParam("token")String token) throws Exception {
        return CompletableFuture.supplyAsync(() -> {
            Map<String, Object> map = new HashMap<>();
            try {
                Admin check = adminService.check(token);
                System.err.println(check);
                logger.info("从redis查到登录的用户为:"+check);
                logger.info("检查用户是否登录->token=" + token);
                if(check==null){
                    map.put("code",0);
                    map.put("message","用户登录失效！！");
                    return new Gson().toJson(map);
                }
                map.put("code", 1);
                map.put("admin",check);
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
}

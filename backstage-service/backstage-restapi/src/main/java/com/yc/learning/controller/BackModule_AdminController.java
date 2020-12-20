package com.yc.learning.controller;

import com.google.gson.Gson;
import com.yc.learning.domain.AdminDomain;
import com.yc.learning.domain.PageDomain;
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
                map.put("data","程序错误");
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

    //删除
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public CompletableFuture<String> delete(@PathVariable Integer id) throws Exception {
        return CompletableFuture.supplyAsync(() -> {
            Map<String, Object> map = new HashMap<>();
            try{
                adminService.delete(id);
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

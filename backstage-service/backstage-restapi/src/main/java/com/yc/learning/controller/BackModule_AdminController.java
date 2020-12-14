package com.yc.learning.controller;

import com.google.gson.Gson;
import com.yc.learning.domain.AdminDomain;
import com.yc.learning.domain.PageDomain;
import com.yc.learning.service.BackModule_AdminService;
import com.yc.learning.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/back-admin")
public class BackModule_AdminController {
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
                PageDomain<AdminDomain> pageDomain = adminService.listByPage(adminDomain);
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
}

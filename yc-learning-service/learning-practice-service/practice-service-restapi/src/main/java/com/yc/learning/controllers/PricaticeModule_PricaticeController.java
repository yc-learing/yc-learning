package com.yc.learning.controllers;

import com.google.gson.Gson;
import com.yc.learning.domain.AdminDomain;
import com.yc.learning.domain.PageDomain;
import com.yc.learning.service.BackModule_AdminService;
import com.yc.learning.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * @author liyan
 * @create 2020-12-2020/12/9-21:18
 */
@RestController
@RequestMapping("/practice")
public class PricaticeModule_PricaticeController {


    @Autowired
    private BackModule_AdminService adminService;



    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public CompletableFuture<String> findAll(@PathVariable("page") Integer page, @PathVariable("pageSize")Integer pageSize, @PathVariable("aid")Integer aid, @PathVariable("aname")String aname, Integer status) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                AdminDomain adminDomain = new AdminDomain();

                if (CommonUtils.isNotNull(page)) {
                    adminDomain.setPage(page);
                }
                if (CommonUtils.isNotNull(pageSize)) {
                    adminDomain.setPageSize(pageSize);
                }
                if (CommonUtils.isNotNull(aid)) {
                    adminDomain.setAid(aid);
                }if (CommonUtils.isNotNull(aname)) {
                    adminDomain.setAname(aname);
                }if (CommonUtils.isNotNull(status)) {
                    adminDomain.setStatus(status);
                }
                PageDomain<AdminDomain> pageDomain = adminService.listByPage(adminDomain);

                Map<String, Object> map = new HashMap<>();
                map.put("code", 1);
                map.put("data", pageDomain);


                return new Gson().toJson(map);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        });
    }
}

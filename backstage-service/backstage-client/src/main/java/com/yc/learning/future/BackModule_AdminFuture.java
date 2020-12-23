package com.yc.learning.future;

import com.yc.learning.domain.AdminDomain;
import com.yc.learning.entity.Admin;
import com.yc.learning.service.BackModule_AdminClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

// 对外公开的业务层
@Component
public class BackModule_AdminFuture {
    @Autowired
    private BackModule_AdminClientService adminClientService;   //业务层

    @Async   //异步调用
    public CompletableFuture<String> findOne(Integer id) {
        return CompletableFuture.supplyAsync(() -> {
            return adminClientService.findOne(id);
        });
    }

    @Async
    public CompletableFuture<String> findByPage(Integer page, Integer pageSize,
                                              String aname) {
        return CompletableFuture.supplyAsync(() -> {
            return adminClientService.findByPage(page, pageSize, aname);
        });
    }

    @Async
    public CompletableFuture<String> insert(AdminDomain picDomain) {
        return CompletableFuture.supplyAsync(() -> {
            return adminClientService.insert(picDomain);
        });
    }

    @Async
    public CompletableFuture<String> delete(Integer id) {
        return CompletableFuture.supplyAsync(() -> {
            return adminClientService.delete(id);
        });
    }

    @Async
    public CompletableFuture<String> update(Integer aid,String value,String field) {
        return CompletableFuture.supplyAsync(() -> {
            return adminClientService.update(aid,value,field);
        });
    }

    @Async   //异步调用
    public CompletableFuture<String> login(Admin admin) {
        return CompletableFuture.supplyAsync(() -> {
            return adminClientService.login(admin);
        });
    }

    @Async
    public CompletableFuture<String> check(String token) {
        return CompletableFuture.supplyAsync(() -> {
            return adminClientService.check(token);
        });
    }
    @Async
    public CompletableFuture<String> logout(String token) {
        return CompletableFuture.supplyAsync(() -> {
            return adminClientService.logout(token);
        });
    }
}

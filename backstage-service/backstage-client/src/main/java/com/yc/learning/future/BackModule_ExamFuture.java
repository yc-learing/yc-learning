package com.yc.learning.future;

import com.yc.learning.service.BackModule_ExamClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class BackModule_ExamFuture {
    @Autowired
    private BackModule_ExamClientService examClientService;

    //分页查询
    @Async
    public CompletableFuture<String> findByPage(Integer page, Integer pageSize, String ename) {
        return CompletableFuture.supplyAsync(() -> {
            return examClientService.findByPage(page, pageSize, ename);
        });
    }

    //分页查询
    @Async
    public CompletableFuture<String> findByExid(Integer exid) {
        return CompletableFuture.supplyAsync(() -> {
            return examClientService.findByExid(exid);
        });
    }
}

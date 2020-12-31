package com.yc.learning.future;

import com.yc.learning.service.BackModule_RecordClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class BackModule_RecordFuture {
    @Autowired
    private BackModule_RecordClientService recordClientService;

    @Async
    public CompletableFuture<String> findByPage(Integer page, Integer pageSize) {
        return CompletableFuture.supplyAsync(() -> {
            return recordClientService.findByPage(page, pageSize);
        });
    }

    @Async
    public CompletableFuture<String> findVoByPage(Integer page, Integer pageSize,String uname,String ename) {
        return CompletableFuture.supplyAsync(() -> {
            return recordClientService.findVoByPage(page, pageSize,uname,ename);
        });
    }
}

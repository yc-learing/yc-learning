package com.yc.learning.future;

import com.yc.learning.service.BackModule_UserClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

// 对外公开的业务层
@Component
public class BackModule_UserFuture {
    @Autowired
    private BackModule_UserClientService userClientService;

    @Async
    public CompletableFuture<String> findByPage(Integer page, Integer pageSize,String uname) {
        return CompletableFuture.supplyAsync(() -> {
            return userClientService.findByPage(page, pageSize, uname);
        });
    }
}

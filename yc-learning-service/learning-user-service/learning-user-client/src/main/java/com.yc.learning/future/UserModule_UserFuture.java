package com.yc.learning.future;

import com.yc.learning.entity.User;
import com.yc.learning.service.UserModule_UserClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

/**
 * @author liyan
 * @create 2020-12-2020/12/27-19:13
 */
// 对外公开的业务层
@Component
public class UserModule_UserFuture {

    @Autowired(required = false)
    UserModule_UserClientService userClientService;


    @Async   //异步调用
    public CompletableFuture<String> login(User user) {
        return CompletableFuture.supplyAsync(() -> {
            return userClientService.login(user);
        });
    }

    @Async
    public CompletableFuture<String> check(String token) {
        return CompletableFuture.supplyAsync(() -> {
            return userClientService.check(token);
        });
    }
    @Async
    public CompletableFuture<String> logout(String token) {
        return CompletableFuture.supplyAsync(() -> {
            return userClientService.logout(token);
        });
    }
}

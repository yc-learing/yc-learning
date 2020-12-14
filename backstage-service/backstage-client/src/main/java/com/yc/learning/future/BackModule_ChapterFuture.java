package com.yc.learning.future;

import com.yc.learning.service.BackModule_ChapterClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

// 对外公开的业务层
@Component
public class BackModule_ChapterFuture {
    @Autowired(required = false)
    private BackModule_ChapterClientService chapterClientService;//业务层

    //分页查询
    @Async
    public CompletableFuture<String> findByPage(Integer page, Integer pageSize,String cname) {
        return CompletableFuture.supplyAsync(() -> {
            return chapterClientService.findByPage(page, pageSize, cname);
        });
    }
}

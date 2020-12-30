package com.yc.learning.future;

import com.yc.learning.client.Learning_ExamModule_ExamClient;
import com.yc.learning.entity.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class Learning_ExamModule_ExamFuture {
    @Autowired
    private Learning_ExamModule_ExamClient examClientService;

    //分页查询
    @Async
    public CompletableFuture<String> findExam( String classes) {
        return CompletableFuture.supplyAsync(() -> {
            return examClientService.findExam(classes);
        });
    }

    //分页查询
    @Async
    public CompletableFuture<String> findByExid(Integer exid) {
        return CompletableFuture.supplyAsync(() -> {
            return examClientService.findExerciesByExam(exid);
        });
    }

    @Async
    //添加记录
    public CompletableFuture<String> addRecord(Record record) {
        return CompletableFuture.supplyAsync(() -> {
            return examClientService.addRecord(record);
        });
    }
}

package com.yc.learning.future;

import com.yc.learning.domain.ExercisesDomain;
import com.yc.learning.service.BackModule_ExercisesClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class BackModule_ExercisesFuture {
    @Autowired
    private BackModule_ExercisesClientService exercisesClientService;

    @Async
    public CompletableFuture<String> findByPage(Integer page, Integer pageSize, String content) {
        return CompletableFuture.supplyAsync(() -> {
            return exercisesClientService.findByPage(page, pageSize, content);
        });
    }

    @Async
    public CompletableFuture<String> insert(ExercisesDomain exercisesDomain) {
        return CompletableFuture.supplyAsync(() -> {
            return exercisesClientService.insert(exercisesDomain);
        });
    }

    @Async
    public CompletableFuture<String> findVoByPage(Integer page, Integer pageSize, Integer chid,Integer cid) {
        return CompletableFuture.supplyAsync(() -> {
            return exercisesClientService.findVoByPage(page, pageSize, chid,cid);
        });
    }
}

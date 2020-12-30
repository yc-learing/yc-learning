package com.yc.learning.future;

import com.yc.learning.domain.CourseDomain;
import com.yc.learning.service.BackModule_CourseClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class BackModule_CourseFuture {
    @Autowired
    private BackModule_CourseClientService courseClientService;

    @Async
    public CompletableFuture<String> findByPage(Integer page, Integer pageSize, String coursename) {
        return CompletableFuture.supplyAsync(() -> {
            return courseClientService.findByPage(page, pageSize, coursename);
        });
    }

    @Async
    public CompletableFuture<String> findCoursenameWithCname() {
        return CompletableFuture.supplyAsync(() -> {
            return courseClientService.findCoursenameWithCname();
        });
    }

    @Async
    public CompletableFuture<String> create(CourseDomain courseDomain) {
        return CompletableFuture.supplyAsync(() -> {
            return courseClientService.create(courseDomain);
        });
    }
}

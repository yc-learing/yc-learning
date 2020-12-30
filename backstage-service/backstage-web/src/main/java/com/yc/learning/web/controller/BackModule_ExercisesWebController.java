package com.yc.learning.web.controller;

import com.yc.learning.domain.ExercisesDomain;
import com.yc.learning.future.BackModule_ExercisesFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/exercises")
public class BackModule_ExercisesWebController {
    @Autowired(required = false)
    private BackModule_ExercisesFuture exercisesFuture;

    @RequestMapping(value = "findByPage",method = RequestMethod.GET)
    public CompletableFuture<String> findByPage(@RequestParam(value = "page",required = false) Integer page, @RequestParam(value = "pageSize",required = false) Integer pageSize, @RequestParam(value = "content",required = false) String content) {
        return exercisesFuture.findByPage(page, pageSize, content);
    }

    @RequestMapping(value = "insert",method = RequestMethod.POST)
    public CompletableFuture<String> insert(@RequestBody ExercisesDomain exercisesDomain){
        System.out.println(exercisesDomain);
        return exercisesFuture.insert(exercisesDomain);
//        return null;
    }
}

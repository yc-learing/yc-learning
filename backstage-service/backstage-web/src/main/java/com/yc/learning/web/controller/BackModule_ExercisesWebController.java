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
        return exercisesFuture.insert(exercisesDomain);
    }

    @RequestMapping(value = "findVoByPage",method = RequestMethod.GET)
    public CompletableFuture<String> findVoByPage(@RequestParam(value = "page",required = false) Integer page, @RequestParam(value = "pageSize",required = false) Integer pageSize, @RequestParam(value = "chid",required = false) Integer chid,@RequestParam(value = "cid",required = false) Integer cid) {
        return exercisesFuture.findVoByPage(page, pageSize, chid,cid);
    }
}

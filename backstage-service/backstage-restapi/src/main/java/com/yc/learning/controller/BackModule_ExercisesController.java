package com.yc.learning.controller;

import com.google.gson.Gson;
import com.yc.learning.domain.ExercisesDomain;
import com.yc.learning.domain.PageDomain;
import com.yc.learning.service.BackModule_ExercisesService;
import com.yc.learning.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/back-exercises")
public class BackModule_ExercisesController {
    @Autowired
    private BackModule_ExercisesService exercisesService;

    @RequestMapping(value = "/findByPage", method = RequestMethod.GET)
    public CompletableFuture<String> findByPage(Integer page, Integer pageSize, String content) {
        return CompletableFuture.supplyAsync(() -> {
            Map<String, Object> map = new HashMap<>();
            try {
                ExercisesDomain exercisesDomain=new ExercisesDomain();
                if (CommonUtils.isNotNull(page)) {
                    exercisesDomain.setPage(page);
                }
                if (CommonUtils.isNotNull(pageSize)) {
                    exercisesDomain.setPageSize(pageSize);
                }
                if (CommonUtils.isNotNull(content)) {
                    exercisesDomain.setContent(content);
                }
                PageDomain<ExercisesDomain> pageDomain = exercisesService.findByPage(exercisesDomain,page,pageSize);
                map.put("code", 1);
                map.put("data", pageDomain);
                return new Gson().toJson(map);
            } catch (Exception e) {
                map.put("code",0);
                map.put("data","程序错误");
                e.printStackTrace();
                return new Gson().toJson(map);
            }
        });
    }
}

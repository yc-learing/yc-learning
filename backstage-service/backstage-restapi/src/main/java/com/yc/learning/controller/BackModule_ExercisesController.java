package com.yc.learning.controller;

import com.google.gson.Gson;
import com.yc.learning.domain.ExercisesDomain;
import com.yc.learning.domain.PageDomain;
import com.yc.learning.service.BackModule_ExercisesService;
import com.yc.learning.util.CommonUtils;
import com.yc.learning.view.domain.ExercisesVoChpaterCourseDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
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
                map.put("msg","程序错误");
                e.printStackTrace();
                return new Gson().toJson(map);
            }
        });
    }

    @RequestMapping(value = "/findVoByPage", method = RequestMethod.GET)
    public CompletableFuture<String> findVoByPage(Integer page, Integer pageSize, Integer chid,Integer cid) {
        return CompletableFuture.supplyAsync(() -> {
            Map<String, Object> map = new HashMap<>();
            try {
                ExercisesVoChpaterCourseDomain vo=new ExercisesVoChpaterCourseDomain();
                if (CommonUtils.isNotNull(page)) {
                    vo.setPage(page);
                }
                if (CommonUtils.isNotNull(pageSize)) {
                    vo.setPageSize(pageSize);
                }
                if (CommonUtils.isNotNull(cid)) {
                    vo.setCid(cid);
                }
                if (CommonUtils.isNotNull(chid)) {
                    vo.setChid(chid);
                }
                PageDomain<ExercisesVoChpaterCourseDomain> pageDomain = exercisesService.findVoByPage(vo,page,pageSize);
                map.put("code", 1);
                map.put("data", pageDomain);
                return new Gson().toJson(map);
            } catch (Exception e) {
                map.put("code",0);
                map.put("msg","程序错误");
                e.printStackTrace();
                return new Gson().toJson(map);
            }
        });
    }

    @RequestMapping(value = "insert",method = RequestMethod.POST)
    public CompletableFuture<String> insert (@RequestBody ExercisesDomain exercisesDomain) throws Exception {
        return CompletableFuture.supplyAsync(() -> {
            Map<String, Object> map = new HashMap<>();
            try {
                exercisesService.insert(exercisesDomain);
                map.put("code", 1);
                map.put("msg", "添加成功");
                return new Gson().toJson(map);
            }catch (Exception e) {
                map.put("code",0);
                map.put("msg","注册失败");
                e.printStackTrace();
                return new Gson().toJson(map);
            }
        });
    }
}

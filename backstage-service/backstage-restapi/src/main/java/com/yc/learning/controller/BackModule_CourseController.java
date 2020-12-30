package com.yc.learning.controller;

import com.google.gson.Gson;
import com.yc.learning.domain.CourseDomain;
import com.yc.learning.domain.PageDomain;
import com.yc.learning.service.BackModule_CourseService;
import com.yc.learning.util.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * @author liyan
 * @create 2020-12-2020/12/13-9:55
 */
@RestController
@RequestMapping("/back-course")
public class BackModule_CourseController {

    private static Logger logger = LoggerFactory.getLogger(BackModule_CourseController.class);

    @Autowired
    private BackModule_CourseService courseService;



    @RequestMapping(value = "/findByPage", method = RequestMethod.GET)
    public CompletableFuture<String> findByPage(Integer page, Integer pageSize,  String coursename) {
        return CompletableFuture.supplyAsync(() -> {
            Map<String, Object> map = new HashMap<>();
            try {
                CourseDomain courseDomain = new CourseDomain();

                if (CommonUtils.isNotNull(page)) {
                    courseDomain.setPage(page);
                }
                if (CommonUtils.isNotNull(pageSize)) {
                    courseDomain.setPageSize(pageSize);
                }
                if (CommonUtils.isNotNull(coursename)) {
                    courseDomain.setCoursename(coursename);
                }
                PageDomain<CourseDomain> pageDomain = courseService.findByPage(courseDomain,page,pageSize);
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

    //查询课程信息
    @RequestMapping(value = "/findCoursenameWithCname", method = RequestMethod.GET)
    public CompletableFuture<String> findCoursenameWithCname() {
        return CompletableFuture.supplyAsync(() -> {
            Map<String, Object> map = new HashMap<>();
            try {
                map.put("code", 1);
                map.put("data", courseService.findCourseName());
                return new Gson().toJson(map);
            } catch (Exception e) {
                map.put("code",0);
                map.put("msg","程序错误");
                e.printStackTrace();
                return new Gson().toJson(map);
            }
        });
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public CompletableFuture<String> save(@RequestBody CourseDomain courseDomain) throws Exception {
        return CompletableFuture.supplyAsync(() -> {
            courseService.insert(courseDomain);
            logger.info("新增->ID=" + courseDomain.getCid());
            Map<String, Object> map = new HashMap<>();
            map.put("code", 1);
            map.put("data", courseDomain);
            return new Gson().toJson(map);
        });
    }

}

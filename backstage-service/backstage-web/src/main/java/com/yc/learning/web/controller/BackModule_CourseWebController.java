package com.yc.learning.web.controller;

import com.yc.learning.future.BackModule_CourseFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/course")
public class BackModule_CourseWebController {

    @Autowired(required = false)
    private BackModule_CourseFuture courseFuture;

    @RequestMapping(value = "findByPage",method = RequestMethod.GET)
    public CompletableFuture<String> findByPage(@RequestParam(value = "page",required = false) Integer page, @RequestParam(value = "pageSize",required = false) Integer pageSize, @RequestParam(value = "coursename",required = false) String coursename) {
        return courseFuture.findByPage(page, pageSize, coursename);
    }
}

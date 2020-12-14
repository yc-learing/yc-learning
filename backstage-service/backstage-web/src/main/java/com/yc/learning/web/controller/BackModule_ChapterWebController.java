package com.yc.learning.web.controller;

import com.yc.learning.future.BackModule_ChapterFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/chapter")
public class BackModule_ChapterWebController {
    @Autowired(required = false)
    private BackModule_ChapterFuture chapterFuture;

    @RequestMapping(value = "findByPage",method = RequestMethod.GET)
    public CompletableFuture<String> findByPage(@RequestParam(value = "page",required = false) Integer page, @RequestParam(value = "limit",required = false) Integer pageSize, @RequestParam(value = "cname",required = false) String cname) {
        return chapterFuture.findByPage(page, pageSize, cname);
    }
}

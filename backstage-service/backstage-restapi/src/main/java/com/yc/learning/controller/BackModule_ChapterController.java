package com.yc.learning.controller;

import com.google.gson.Gson;
import com.yc.learning.domain.ChapterDomain;
import com.yc.learning.domain.PageDomain;
import com.yc.learning.service.BackModule_ChapterService;
import com.yc.learning.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/back-chapter")
public class BackModule_ChapterController {
    @Autowired
    private BackModule_ChapterService chapterService;

    @RequestMapping(value = "/findByPage", method = RequestMethod.GET)
    public CompletableFuture<String> findByPage(Integer page, Integer pageSize, String cname) {
        return CompletableFuture.supplyAsync(() -> {
            Map<String, Object> map = new HashMap<>();
            try {
                ChapterDomain chapterDomain = new ChapterDomain();

                if (CommonUtils.isNotNull(page)) {
                    chapterDomain.setPage(page);
                }
                if (CommonUtils.isNotNull(pageSize)) {
                    chapterDomain.setPageSize(pageSize);
                }
                if (CommonUtils.isNotNull(cname)) {
                    chapterDomain.setCname(cname);
                }
                PageDomain<ChapterDomain> pageDomain = chapterService.findByPage(chapterDomain,page,pageSize);
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

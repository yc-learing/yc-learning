package com.yc.learning.controller;

import com.google.gson.Gson;
import com.yc.learning.domain.PageDomain;
import com.yc.learning.domain.RecordDomain;
import com.yc.learning.service.BackModule_RecordService;
import com.yc.learning.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/back-record")
public class BackModule_RecordController {
    @Autowired(required = false)
    private BackModule_RecordService recordService;

    @RequestMapping(value = "/findByPage", method = RequestMethod.GET)
    public CompletableFuture<String> findByPage(Integer page, Integer pageSize) {
        return CompletableFuture.supplyAsync(() -> {
            Map<String, Object> map = new HashMap<>();
            try {
                RecordDomain recordDomain = new RecordDomain();

                if (CommonUtils.isNotNull(page)) {
                    recordDomain.setPage(page);
                }
                if (CommonUtils.isNotNull(pageSize)) {
                    recordDomain.setPageSize(pageSize);
                }
                PageDomain<RecordDomain> pageDomain = recordService.findByPage(recordDomain);
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

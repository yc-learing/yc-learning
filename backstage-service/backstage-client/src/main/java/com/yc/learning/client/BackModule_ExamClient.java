package com.yc.learning.client;

import com.yc.learning.config.FeignClientConfig;
import com.yc.learning.domain.ExamDomain;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "base-zuul-gateway",
        configuration = FeignClientConfig.class
)  // 配置要按自定义的类FeignClientConfig
public interface BackModule_ExamClient {

    @RequestMapping(method = RequestMethod.GET, value = "/back-proxy/back-exam/findByPage",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String findByPage (@RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize, @RequestParam("ename") String ename);

    @RequestMapping(method = RequestMethod.GET, value = "/back-proxy/back-exam/findByExid",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String findByExid (@RequestParam("exid") Integer exid);

    @RequestMapping(method = RequestMethod.POST, value = "/back-proxy/back-exam/add",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String addExam (@RequestBody ExamDomain examDomain);
}

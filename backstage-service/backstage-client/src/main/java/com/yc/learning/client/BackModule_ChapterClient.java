package com.yc.learning.client;

import com.yc.learning.config.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

// feign客户端要访问的是  zuul服务 BASE-ZUUL-GATEWAY
@FeignClient(name = "BASE-ZUUL-GATEWAY",
        configuration = FeignClientConfig.class
)  // 配置要按自定义的类FeignClientConfig
public interface BackModule_ChapterClient {
    @RequestMapping(method = RequestMethod.GET, value = "/back-proxy/back-chapter/findByPage",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String findByPage(@RequestParam("page")Integer page,@RequestParam("pageSize")Integer pageSize,@RequestParam("cname") String cname);
}

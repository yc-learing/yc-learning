package com.yc.learning.client;

import com.yc.learning.config.FeignClientConfig;
import com.yc.learning.domain.ExercisesDomain;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "base-zuul-gateway",
        configuration = FeignClientConfig.class
)  // 配置要按自定义的类FeignClientConfig
public interface BackModule_ExercisesClient {

    @RequestMapping(method = RequestMethod.GET, value = "/back-proxy/back-exercises/findByPage",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String findByPage (@RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize, @RequestParam("content") String content);

    @RequestMapping(method = RequestMethod.POST, value = "/back-proxy/back-exercises/insert",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String insert(@RequestBody ExercisesDomain exercisesDomain);

    @RequestMapping(method = RequestMethod.GET, value = "/back-proxy/back-exercises/findVoByPage",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String findVoByPage(@RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize,@RequestParam("chid") Integer chid,@RequestParam("cid")Integer cid);
}

package com.yc.learning.client;

import com.yc.learning.config.FeignClientConfig;
import com.yc.learning.domain.UserDomain;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

//在eureka上注册的服务名
@FeignClient(name = "base-zuul-gateway",
        configuration = FeignClientConfig.class
)  // 配置要按自定义的类FeignClientConfig
public interface BackModule_UserClient {
    @RequestMapping(method = RequestMethod.GET, value = "/back-proxy/back-user/findByPage",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String findByPage (@RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize, @RequestParam("uname") String uname);

    @RequestMapping(method = RequestMethod.GET, value = "/back-proxy/back-user/{id}")
    String findOne(@RequestParam("id") Integer id);


    @RequestMapping(method = RequestMethod.POST, value = "/back-proxy/back-user/insert",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String insert(@RequestBody UserDomain userDomain);

    @RequestMapping(method = RequestMethod.DELETE, value = "/back-proxy/back-user/{id}")
    String delete(@RequestParam("id") Integer id);

    @RequestMapping(method = RequestMethod.POST, value = "/back-proxy/back-user/update")
    String update(@RequestParam("uid")Integer uid,@RequestParam("value")String value,@RequestParam("field")String field);

}

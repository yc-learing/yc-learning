package com.yc.learning.client;

import com.yc.learning.config.FeignClientConfig;
import com.yc.learning.domain.AdminDomain;
import com.yc.learning.entity.Admin;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @author liyan
 * @create 2020-12-2020/12/13-17:27
 */
//在eureka上注册的服务名
@FeignClient(name = "base-zuul-gateway",
        configuration = FeignClientConfig.class
)  // 配置要按自定义的类FeignClientConfig
public interface BackModule_AdminClient {
    @RequestMapping(method = RequestMethod.GET, value = "/back-proxy/back-admin/{id}")
    String findOne(@RequestParam("id") Integer id);

    @RequestMapping(method = RequestMethod.GET, value = "/back-proxy/back-admin/findByPage",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String findByPage (@RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize, @RequestParam("aname") String aname);

    @RequestMapping(method = RequestMethod.POST, value = "/back-proxy/back-admin/insert",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String insert(@RequestBody AdminDomain adminDomain);

    @RequestMapping(method = RequestMethod.DELETE, value = "/back-proxy/back-admin/{id}")
    String delete(@RequestParam("id") Integer id);

    @RequestMapping(method = RequestMethod.POST, value = "/back-proxy/back-admin/update",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String update(@RequestParam("aid")Integer aid,@RequestParam("value")String value,@RequestParam("field")String field);

    @RequestMapping(method = RequestMethod.POST, value = "/back-proxy/back-admin/login",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String login(@RequestBody Admin admin);

    @RequestMapping(method = RequestMethod.POST, value = "/back-proxy/back-admin/check",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String check(@RequestBody(required = false) String token);

    @RequestMapping(method = RequestMethod.POST, value = "/back-proxy/back-admin/logout",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String logout(@RequestBody(required = false)String token);
}

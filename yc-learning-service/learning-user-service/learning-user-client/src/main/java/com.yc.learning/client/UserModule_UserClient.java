package com.yc.learning.client;

import com.yc.learning.config.FeignClientConfig;
import com.yc.learning.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author liyan
 * @create 2020-12-2020/12/27-19:04
 */
@FeignClient(name = "base-zuul-gateway",
        configuration = FeignClientConfig.class
)
public interface UserModule_UserClient {

    @RequestMapping(method = RequestMethod.POST, value = "/user-proxy/user/login",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String login(@RequestBody User user);

    @RequestMapping(method = RequestMethod.POST, value = "/user-proxy/user/check",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String check(@RequestBody(required = false) String token);

    @RequestMapping(method = RequestMethod.POST, value = "/user-proxy/user/logout",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String logout(@RequestBody(required = false)String token);
}

package com.yc.learning.service;

import com.alibaba.druid.support.json.JSONUtils;
import com.google.gson.Gson;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yc.learning.vo.UserLoginVo;
import com.yc.learning.client.UserModule_UserClient;
import com.yc.learning.entity.User;
import com.yc.learning.util.MD5Utils;
import com.yc.learning.util.TokenUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author liyan
 * @create 2020-12-2020/12/27-19:14
 */
@Service
public class UserModule_UserClientService {
    private static final Logger log = LoggerFactory.getLogger(UserModule_UserClientService.class);
    @Autowired(required = false)
    private UserModule_UserClient userClient;

    @Autowired
    private RedisTemplate redisTemplate;

    @HystrixCommand(fallbackMethod = "loginFallback")
    public String login(User user) {
        System.out.println("登录用户");
        System.out.println(user);
        String login = userClient.login(user);
        System.err.println(login);
        try {
            if(login!=null){
                Map parse=(HashMap) JSONUtils.parse(login);
                Integer code = (Integer) parse.get("code");
                if(code==1){
                    UserLoginVo userLoginVo = new UserLoginVo(user);
                    String s1 = new Gson().toJson(userLoginVo);
                    System.out.println(s1);
                    return s1;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return login;
    }

    private String loginFallback(User user) {
        Map map = new HashMap();
        map.put("code", "0");
        map.put("msg", "服务异常，无法登录");
        return new Gson().toJson(map);
    }


    /**
     * 生成AdminVO
     * @param user                                                     
     * @return
     */
    private UserLoginVo userLoginVo(User user){
        String aname = user.getUname();
        String apwd = user.getUpwd();
        String adminToken = TokenUtils.createToken(aname,apwd);
        user.setUpwd(MD5Utils.stringToMD5(apwd));
        System.err.println(user);
        redisTemplate.opsForValue().set(
                String.format("GODZILLA:ADMIN:TOKEN:%s", adminToken),
                user,60*2, TimeUnit.MINUTES
        );
        UserLoginVo userLoginVo =new UserLoginVo(user);
        userLoginVo.setToken("GODZILLA:ADMIN:TOKEN:"+adminToken);
        return userLoginVo;
    }

    public String check(String token) {
        log.info("检查用户是否登录"+token);
        String check = userClient.check(token);
        return check;
    }

    public String logout(String token) {
        log.info("删除token为："+token);
        String check = userClient.logout(token);
        return check;
    }



}

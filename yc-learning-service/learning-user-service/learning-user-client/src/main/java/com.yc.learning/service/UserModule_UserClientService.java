package com.yc.learning.service;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yc.learning.vo.UserJson;
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
        String jsonString = userClient.login(user);
        System.err.println(jsonString);  //返回的json数据
        try {
            if(jsonString!=null){
                Map parse=(HashMap) JSONUtils.parse(jsonString);
                Integer code = (Integer) parse.get("code");
                if(code==1){
                    Map mapdata =(HashMap) parse.get("data");
                    JSON json = (JSON) JSON.toJSON(mapdata);
                    UserJson user_data = JSONObject.toJavaObject((JSON) JSONObject.toJSON(mapdata), UserJson.class);
                    System.out.println(user_data);
                    //从数据库查到有相应的值
                    //将map转换为实体bean类
                    UserLoginVo userLoginVo = userLoginVo(user_data);//存到redis中
                    HashMap map =new HashMap();
                    map.put("code",1);
                    map.put("msg","登陆成功!");
                    String s1 = new Gson().toJson(userLoginVo);
                    String token = userLoginVo.getToken();
                    map.put("token",token);
                    System.out.println(new Gson().toJson(map));
                    return new Gson().toJson(map);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return jsonString;
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
    private UserLoginVo userLoginVo(UserJson user){
        String aname = user.getUname();
        String apwd = user.getUpwd();
        String userToken = TokenUtils.createToken(aname,apwd);
        user.setUpwd(MD5Utils.stringToMD5(apwd));
        System.err.println(user);
        log.info("加入缓存key="+user);
        redisTemplate.opsForValue().set(
                String.format("GODZILLA:USER:TOKEN:%s", userToken),
                user,60*2, TimeUnit.MINUTES
        );
        UserLoginVo userLoginVo=new UserLoginVo();
        userLoginVo.setToken(userToken);
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

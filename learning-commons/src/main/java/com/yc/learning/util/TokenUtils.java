package com.yc.learning.util;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class TokenUtils {
    @Autowired
    private RedisTemplate redisTemplate;
    private static final Logger log = LoggerFactory.getLogger(TokenUtils.class);


    public static String createToken(Map<String, String> map, Integer expireSeconds) {
        String token = null;
        Map<String, Object> mapHeader = new HashMap<>();
        mapHeader.put("alg", "HS256");
        mapHeader.put("typ", "JWT");
        JWTCreator.Builder jwtBuilder = JWT.create();
        jwtBuilder.withHeader(mapHeader);

        if (map != null && !map.isEmpty()) {
            Set<Map.Entry<String, String>> setEntry = map.entrySet();
            for (Map.Entry<String, String> entry : setEntry) {
                jwtBuilder.withClaim(entry.getKey(), entry.getValue());
            }
        }

        if (expireSeconds != null && expireSeconds > 0) {
            Calendar nowTime = Calendar.getInstance();
            int calendarField = Calendar.SECOND;
            nowTime.add(calendarField, expireSeconds);
            Date expiresDate = nowTime.getTime();
            jwtBuilder.withExpiresAt(expiresDate);
        }
        jwtBuilder.withIssuedAt(new Date());

        try {
            token = jwtBuilder.sign(Algorithm.HMAC256("GODZILLALOGINtoken"));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (JWTCreationException e) {
            e.printStackTrace();
        }
        return token;
    }
    public static String createToken(String aname,String apwd) {
        Map<String, String> mapClaim = new HashMap<>();
        mapClaim.put("aname", aname);
        mapClaim.put("apwd",apwd);
        Integer expireSeconds = 2* 60 * 60;// 这里设置过期时间为两个小时
        return createToken(mapClaim, expireSeconds);
    }
}
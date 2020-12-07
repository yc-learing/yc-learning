package com.yc.learning.fallback;

import com.google.gson.Gson;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/*
   zuul是一个代理服务，但如果被代理的服务突然断了, 则zuul要启用熔断处理
   测试: http://localhost:9501/yc-api/piclib-proxy/piclib/findAll
       关闭微服务piclibrestapi
 */
@Component
public class ZuulFallBackHandler implements FallbackProvider {
    //回退处理用在哪些zuul的代理路径上.
    @Override
    public String getRoute() {
        return "*";
    }

    @Override   //拼装回退处理给客户端响应的结果
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        return new ClientHttpResponse() {
            @Override
            public HttpStatus getStatusCode() throws IOException {
                return HttpStatus.BAD_REQUEST;  // 400
            }

            @Override
            public int getRawStatusCode() throws IOException {
                return HttpStatus.BAD_REQUEST.value();
            }

            @Override
            public String getStatusText() throws IOException {
                return HttpStatus.BAD_REQUEST.getReasonPhrase();    // Bad Request
            }

            @Override
            public void close() {

            }

            @Override
            public InputStream getBody() throws IOException {
                Map map = new HashMap();
                map.put("code", "400");
                map.put("msg", "微服务不可用，请稍后重试");
                String result = new Gson().toJson(map);
                return new ByteArrayInputStream(result.getBytes());
            }

            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders headers = new HttpHeaders();
                headers.set("Content-Type", "application/json;charset=UTF-8");
                return headers;
            }
        };
    }
}
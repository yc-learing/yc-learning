package com.yc.learning.client;

import com.yc.learning.config.FeignClientConfig;
import com.yc.learning.entity.Record;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "base-zuul-gateway",
        configuration = FeignClientConfig.class
)  // 配置要按自定义的类FeignClientConfig
public interface Learning_ExamModule_ExamClient {

    @RequestMapping(method = RequestMethod.GET, value = "/exam-proxy/exam/findexam",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String findExam (@RequestParam(value = "classes" ,required = false) String classes);

    @RequestMapping(method = RequestMethod.GET, value = "/exam-proxy/exam/findExerciesByExam",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String findExerciesByExam (@RequestParam("exid") Integer exid);

    @RequestMapping(method = RequestMethod.POST, value = "/exam-proxy/exam/addRecord",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String addRecord(@RequestBody Record record);
}

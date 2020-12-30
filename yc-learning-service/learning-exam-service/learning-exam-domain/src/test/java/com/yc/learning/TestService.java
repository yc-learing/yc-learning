package com.yc.learning;

import com.sun.istack.internal.logging.Logger;
import com.yc.learning.service.Learning_ExamModule_ExamService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {DaoConfiguration.class})
public class TestService {
    private static final Logger logger = Logger.getLogger(TestService.class);


    @Autowired(required = false)
    private Learning_ExamModule_ExamService examMapper;



    @Test
    public void findbyClass(){
        System.out.println(examMapper.findByClasses("78"));

    }

}

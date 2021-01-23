//package com.yc.learning;
//
//import com.sun.istack.internal.logging.Logger;
//import com.yc.learning.*;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringRunner;
//
//@RunWith(SpringRunner.class)
//@ContextConfiguration(classes = {DaoConfiguration.class, RedisAOP.class})
//public class TestService {
//    private static final Logger logger = Logger.getLogger(TestService.class);
//
//    @Autowired
//    private AdminService adminService;
//    @Autowired(required = false)
//    private UserService userService;
//    @Autowired(required = false)
//    private ExamMapper examMapper;
//
//    @Test
//    public void insertAdmin(){
//        AdminDomain domain =new AdminDomain(null,"HillCheung","123",1);
//        String MD5PWD = MD5Utils.stringToMD5(domain.getApwd());
//        domain.setApwd(MD5PWD);
//
//        adminService.insert(domain);
//    }
//
//    @Test
//    public void findbyClass(){
//        System.out.println(examMapper.findByClasses("78"));
//
//    }
//
//}

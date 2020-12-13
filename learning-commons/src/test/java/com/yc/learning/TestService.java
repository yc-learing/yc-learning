package com.yc.learning;

import com.sun.istack.internal.logging.Logger;
import com.yc.learning.domain.AdminDomain;
import com.yc.learning.service.AdminService;
import com.yc.learning.service.UserService;
import com.yc.learning.util.MD5Utils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {DaoConfiguration.class})
public class TestService {
    private static final Logger logger = Logger.getLogger(TestService.class);

    @Autowired
    private AdminService adminService;
    @Autowired(required = false)
    private UserService userService;

    @Test
    public void insertAdmin(){
        AdminDomain domain =new AdminDomain(null,"HillCheung","123",1);
        String MD5PWD = MD5Utils.stringToMD5(domain.getApwd());
        domain.setApwd(MD5PWD);

        adminService.insert(domain);
    }
}

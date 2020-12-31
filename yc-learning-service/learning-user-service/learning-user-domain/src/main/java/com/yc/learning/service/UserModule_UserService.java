package com.yc.learning.service;

import com.yc.learning.vo.UserJson;
import com.yc.learning.dao.impl.UserMapper;
import com.yc.learning.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author liyan
 * @create 2020-12-2020/12/27-17:07
 */
@Service
@Transactional
public class UserModule_UserService extends UserServiceImpl{


    private static Logger logger = LoggerFactory.getLogger(UserModule_UserService.class);

    @Autowired(required = false)
    private UserMapper userMapper;

    @Autowired(required = false)
    private RedisTemplate redisTemplate;




    @Transactional(readOnly = true)
    public User login(User user) {
        Example example = new Example(User.class);   //条件
        example.createCriteria().andEqualTo("uname", user.getUname()).andEqualTo("upwd",user.getUpwd()/*MD5Utils.stringToMD5(user.getUpwd())*/);
        List<User> users = userMapper.selectByExample(example);
        return users.size() == 0 ? null : users.get(0);
    }

    public UserJson check(String token) {
        if(token==null){
            return null;
        }
        token="GODZILLA:USER:TOKEN:"+token;
        ValueOperations<String,UserJson> valueOperations = redisTemplate.opsForValue();
        logger.info("从reidis查询的键为：-->"+token);
        UserJson user = valueOperations.get(token);
        System.err.println(user);
        return user;
    }

    public int logout(String token) {
        token="GODZILLA:USER:TOKEN:"+token;
        logger.info("删除token为："+token);
        Boolean delete = redisTemplate.delete(token);
        if(delete==true){
            return 1;
        }
        return 0;
    }

}

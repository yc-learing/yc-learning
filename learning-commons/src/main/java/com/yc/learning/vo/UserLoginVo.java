package com.yc.learning.vo;

import com.yc.learning.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author liyan
 * @create 2020-12-2020/12/27-19:20
 */

@Getter
@Setter
public class UserLoginVo extends User implements Serializable {


    private String token;   //密钥

    public UserLoginVo(){

    }

    public UserLoginVo(User user){

        super(user.getUid(),user.getUname(),user.getUpwd(),user.getTel(),user.getEmail(),user.getQq(),user.getVx(),user.getClasses(),user.getRegistrytime(),user.getEndtime(),user.getStatus());
    }



}

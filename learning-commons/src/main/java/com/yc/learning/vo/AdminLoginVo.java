package com.yc.learning.VO;

import com.yc.learning.entity.Admin;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class AdminLoginVo extends Admin implements Serializable {


    private String token;   //密钥



    public AdminLoginVo(){

    }

    public AdminLoginVo(Admin admin){
        super(admin.getAid(),admin.getAname(),admin.getApwd(),admin.getStatus());
    }


}

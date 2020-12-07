package com.yc.learning.service;

import com.yc.learning.bean.User;
import com.yc.learning.dao.UserMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired(required = false)
    private UserMapper mapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=mapper.queryUserByUserName(username);
        if (username == null){
            throw  new UsernameNotFoundException("用户名不存在");
        }

        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
        String role = user.getRoles();
        System.out.println(role);
        if (StringUtils.isNotBlank(role)){
            authorityList.add(new SimpleGrantedAuthority(role.trim()));
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),authorityList);
    }
}

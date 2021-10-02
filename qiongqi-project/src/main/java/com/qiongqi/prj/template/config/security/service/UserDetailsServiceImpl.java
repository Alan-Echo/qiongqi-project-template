package com.qiongqi.prj.template.config.security.service;

import com.qiongqi.prj.template.modules.admin.dto.VAdminDTO;
import com.qiongqi.prj.template.modules.admin.service.VAdminService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 说明
 * @author: ※狗尾巴草
 * @date: 2020-12-15 15:20
 **/
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private VAdminService vAdminService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        if(!StringUtils.isNotBlank(userName)){
            throw new RuntimeException("用户名不能为空");
        }
        VAdminDTO adminDTO = vAdminService.getByUserName(userName);
        if(adminDTO==null){
            throw new RuntimeException("用户不存在");
        }
        //设置用户权限
        List<GrantedAuthority> grantedAuthorities  = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_admin"));
//        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_user"));
        //userEntity.getAccount(), userEntity.getPassword(), userEntity.getEnabled() == 1, userEntity.getAccount_not_expired() == 1, userEntity.getCredentials_not_expired() == 1, userEntity.getAccount_not_locked() == 1, grantedAuthorities
        User user = new User(adminDTO.getUserName(),adminDTO.getPassword(),true,true,true,true,grantedAuthorities);
        return user;
    }
}

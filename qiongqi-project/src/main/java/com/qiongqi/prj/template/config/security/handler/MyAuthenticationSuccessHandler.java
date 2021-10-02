package com.qiongqi.prj.template.config.security.handler;

import com.alibaba.fastjson.JSON;
import com.qiongqi.prj.template.modules.admin.dto.VAdminDTO;
import com.qiongqi.prj.template.modules.admin.service.VAdminService;
import com.qiongqi.prj.template.utils.JWT.XTokenUtils;
import com.qiongqi.prj.template.utils.response.code.ResponseCode;
import com.qiongqi.prj.template.utils.response.data.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: 认证成功处理器
 * @author: ※狗尾巴草
 * @date: 2020-12-15 15:26
 **/
@Component
public class MyAuthenticationSuccessHandler implements org.springframework.security.web.authentication.AuthenticationSuccessHandler {

    @Autowired
    private VAdminService vAdminService;

//    @Autowired
//    private RedisUtil redisUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        //更新用户表上次登录时间、更新人、更新时间等字段
        User userDetails = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        VAdminDTO adminDTO = vAdminService.getByUserName(userDetails.getUsername());
        adminDTO.setLastLoginTime(new Date());
        vAdminService.update(adminDTO);

        //  获取用户权限
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        String role = "";
        for (GrantedAuthority authority : authorities){
            role +=authority.getAuthority()+",";
        }
        role=role.substring(0,role.length()-1);
        //将权限放入token中
        Map<String, Object> map = new HashMap<>();
        map.put(ResponseCode.ParamterUserIdKey,adminDTO.getId());
        map.put("role",role);
        map.put("userName",adminDTO.getUserName());
        String token = null;
        try {
            token = XTokenUtils.createToken(map, ResponseCode.TokenExp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        HashMap<Object, Object> hashMap = new HashMap<>();
        hashMap.put("token",token);
        ResponseData<Object> result = new ResponseData<>();
//        redisUtil.set(adminDTO.getId().toString(),token,ResponseCode.TokenExp);
        //处理编码方式，防止中文乱码的情况
        httpServletResponse.setContentType("text/json;charset=utf-8");
        //塞到HttpServletResponse中返回给前台
        System.out.println("登录成功"+token);
        httpServletResponse.getWriter().write(JSON.toJSONString(result.success(hashMap)));

    }
}

package com.qiongqi.config.security.handler;

import com.alibaba.fastjson.JSON;
import com.qiongqi.config.Redis.RedisUtil;
import com.qiongqi.utils.JWT.JWTHelp;
import com.qiongqi.utils.response.code.ResponseCode;
import com.qiongqi.utils.response.data.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description: 退出成功处理器
 * @author: ※狗尾巴草
 * @date: 2020-12-15 16:58
 **/
@Component
public class MyLogoutSuccessHandler implements LogoutSuccessHandler {

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        String headerToken = request.getHeader(ResponseCode.TokenKey);
        String userId =null;
//        System.out.println("退出成功处理器: 退出处理");
        ResponseData result =null;
        try {
             userId = JWTHelp.getFromToken(headerToken, ResponseCode.ParamterUserIdKey);
            if(userId==null){
                result = new ResponseData<>().fail(ResponseCode.USER_NOT_LOGIN);
            }else {
                result = new ResponseData<>().success("退出成功");
            }
        }catch (Exception e){
            result = new ResponseData<>().fail(ResponseCode.USER_NOT_LOGIN);
        }
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(result));
    }
}

package com.qiongqi.video.guaikavideo.config.security.handler;

import com.alibaba.fastjson.JSON;
import com.qiongqi.video.guaikavideo.config.Exception.XAuthenticationException;
import com.qiongqi.video.guaikavideo.utils.response.code.ResponseCode;
import com.qiongqi.video.guaikavideo.utils.response.data.ResponseData;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description: 认证失败处理器
 * @author: ※狗尾巴草
 * @date: 2020-12-15 16:41
 **/
@Component
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        //返回json数据
        ResponseData<Object> result = new ResponseData<>();
        if (e instanceof AccountExpiredException) {
            //账号过期
            result = new ResponseData<>().fail(ResponseCode.USER_ACCOUNT_EXPIRED);
        } else if (e instanceof BadCredentialsException) {
            //密码错误
            result = new ResponseData<>().fail(ResponseCode.USER_CREDENTIALS_ERROR);
        } else if (e instanceof CredentialsExpiredException) {
            //密码过期
            result = new ResponseData<>().fail(ResponseCode.USER_CREDENTIALS_EXPIRED);
        } else if (e instanceof DisabledException) {
            //账号不可用
            result = new ResponseData<>().fail(ResponseCode.USER_ACCOUNT_DISABLE);
        } else if (e instanceof LockedException) {
            //账号锁定
            result = new ResponseData<>().fail(ResponseCode.USER_ACCOUNT_LOCKED);
        } else if (e instanceof InternalAuthenticationServiceException) {
            //用户不存在
            result = new ResponseData<>().fail(ResponseCode.USER_ACCOUNT_NOT_EXIST);
        }else if(e instanceof XAuthenticationException){
            if(((XAuthenticationException) e).getCode().equals(ResponseCode.VER_CODE_ERROR.getCode())){
                //验证码
                result = new ResponseData<>().fail(ResponseCode.VER_CODE_ERROR);
            }else if(e.getMessage().equals(ResponseCode.USER_NOT_LOGIN.getMessage())){
                //未登录
                result = new ResponseData<>().fail(ResponseCode.USER_NOT_LOGIN);
            }else {
                //其他
                result = new ResponseData<>().fail(ResponseCode.COMMON_FAIL);
            }

        }else{
            //其他错误
            result = new ResponseData<>().fail(ResponseCode.COMMON_FAIL);
        }
        //处理编码方式，防止中文乱码的情况
        httpServletResponse.setContentType("text/json;charset=utf-8");
        //塞到HttpServletResponse中返回给前台
        httpServletResponse.getWriter().write(JSON.toJSONString(result));
    }
}

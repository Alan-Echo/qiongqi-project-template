package com.qiongqi.video.guaikavideo.config.security.handler;

import com.alibaba.fastjson.JSON;
import com.qiongqi.video.guaikavideo.utils.response.code.ResponseCode;
import com.qiongqi.video.guaikavideo.utils.response.data.ResponseData;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description: 匿名用户访问无权限资源时的异常
 * @author: ※狗尾巴草
 * @date: 2020-12-15 17:04
 **/
@Component
public class MyAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        ResponseData result = new ResponseData<>().fail(ResponseCode.NO_PERMISSION);
        httpServletResponse.setContentType("text/json;charset=utf-8");
        httpServletResponse.getWriter().write(JSON.toJSONString(result));
    }
}

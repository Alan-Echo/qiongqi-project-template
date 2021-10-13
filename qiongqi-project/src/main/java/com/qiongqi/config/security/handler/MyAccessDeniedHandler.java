package com.qiongqi.config.security.handler;//package com.xt.blog.tavern.config.security.handler;

import com.alibaba.fastjson.JSON;
import com.qiongqi.utils.response.code.ResponseCode;
import com.qiongqi.utils.response.data.ResponseData;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description: 登录异常捕获
 * @author: ※狗尾巴草
 * @date: 2020-12-15 17:01
 **/
@Component
public class MyAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        ResponseData fail = new ResponseData<>().fail(ResponseCode.USER_ACCOUNT_NOT_EXIST);
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().write(JSON.toJSONString(fail));
    }
}

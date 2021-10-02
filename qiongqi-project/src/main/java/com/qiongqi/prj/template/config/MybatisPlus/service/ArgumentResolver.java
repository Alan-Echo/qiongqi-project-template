package com.qiongqi.prj.template.config.MybatisPlus.service;//package com.qiongqi.blog.common.service;
//
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.MethodParameter;
//import org.springframework.stereotype.Service;
//import org.springframework.web.bind.support.WebDataBinderFactory;
//import org.springframework.web.context.request.NativeWebRequest;
//import org.springframework.web.method.support.HandlerMethodArgumentResolver;
//import org.springframework.web.method.support.ModelAndViewContainer;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//
///**
// * //实现HandlerMethodArgumentResolver接口来自定义一个参数处理器
// * 传递用户名参数,redis查询用户是否存在,是:已登陆,否则:未登录
// */
//@Service
//public class ArgumentResolver implements HandlerMethodArgumentResolver {
//
//    @Autowired
//    private RedisUtil redisUtil;
//
//
//    @Override
//    public boolean supportsParameter(MethodParameter parameter) {
//        Class<?> parameterType = parameter.getParameterType();
//        return String.class==parameterType;
//    }
//
//    @Override
//    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory binderFactory) throws Exception {
//        //获取到请求对象和响应对象
//        HttpServletRequest request = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
//        HttpServletResponse response= nativeWebRequest.getNativeResponse(HttpServletResponse.class);
//        String UserName = request.getParameter(ResponseCode.userKey);
//        Object object = redisUtil.get(UserName + ResponseCode._Joiner + ResponseCode.userObj);
//        if (object==null){
//            return false;
//        }
//        return true;
//    }
//}

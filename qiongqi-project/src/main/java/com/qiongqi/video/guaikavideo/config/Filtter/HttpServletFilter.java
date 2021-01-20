package com.qiongqi.video.guaikavideo.config.Filtter;//package com.xt.blog.tavern.Filtter;
//
//import com.xt.blog.tavern.Exception.XAuthenticationException;
//import com.xt.blog.tavern.config.Redis.RedisUtil;
//import com.xt.blog.tavern.config.security.handler.MyAuthenticationFailureHandler;
//import com.xt.blog.tavern.utils.JWT.JWTHelp;
//import com.xt.blog.tavern.utils.statusCode.ResponseCode;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * @description: 参数过滤器
// * @author: ※狗尾巴草
// * @date: 2020-12-18 17:13
// **/
//
//@Component
//public class HttpServletFilter implements Filter {
//
//    @Autowired
//    private MyAuthenticationFailureHandler failureHandler;
//
//    @Autowired
//    private RedisUtil redisUtil;
//
//    private String[] FiltterUrl={
//            "/admin/",
//            "/user/"
//    };
//
//    @Override
//    public void init(FilterConfig filterConfig) {
//    }
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        Map<String, String[]> m = new HashMap<String, String[]>(request.getParameterMap());
//        System.out.println("参数过滤器");
//        try {
//            addParameter(request,m);
//        }catch (XAuthenticationException e){
//            failureHandler.onAuthenticationFailure((HttpServletRequest) request,(HttpServletResponse) response,  e);
//            return;
//        }
//        ParameterRequestWrapper parameterRequestWrapper = new ParameterRequestWrapper((HttpServletRequest) request, m);
//
//        //继续向后传递修改后的request,拦截器不能实现。
//        chain.doFilter(parameterRequestWrapper, response);
//    }
//
//    @Override
//    public void destroy() {
//
//    }
//
//    /** 添加参数 */
//    private void addParameter(ServletRequest servletRequest,Map<String, String[]> parameterMap){
//        HttpServletRequest request =(HttpServletRequest) servletRequest;
////        System.out.println("参数过滤器: "+request.getRequestURI());
//        String headerToken = request.getHeader(ResponseCode.TokenKey);
//        if(StringUtils.isNotBlank(headerToken)){
//            String uid = JWTHelp.getFromToken(headerToken, ResponseCode.ParamterUserIdKey);
//            Object redisToken = redisUtil.get(uid);
//            System.out.println("参数过滤器: 当前访问角色: "+JWTHelp.getFromToken(headerToken,"role"));
////            if(uid==null){
////                throw new XAuthenticationException(ResponseCode.USER_NOT_LOGIN.getMessage());
////            }
//            if(uid!=null){
//                //添加参数
//                parameterMap.put(ResponseCode.ParamterUserIdKey,new String[]{uid});
//                if(redisToken.equals(headerToken)){
//                    parameterMap.put(ResponseCode.TokenKey,new String[]{headerToken});
//                }
//            }
//        }
//    }
//
//
//}
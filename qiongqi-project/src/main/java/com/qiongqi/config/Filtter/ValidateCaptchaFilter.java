package com.qiongqi.config.Filtter;

import com.qiongqi.config.Exception.XAuthenticationException;
import com.qiongqi.config.Redis.RedisUtil;
import com.qiongqi.config.security.handler.MyAuthenticationFailureHandler;
import com.qiongqi.utils.response.code.ResponseCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description: 验证码过滤器
 * @author: ※狗尾巴草
 * @date: 2020-12-15 17:47
 **/
@Component
public class ValidateCaptchaFilter  extends OncePerRequestFilter {

    @Autowired
    private MyAuthenticationFailureHandler failureHandler;

    @Autowired
    private RedisUtil redisUtil;

    private static final String LOGIN_PATH = "/qiongqi/project/auth/login";
    private static final String POST_METHOD = "post";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(LOGIN_PATH.equalsIgnoreCase(request.getRequestURI())&&POST_METHOD.equalsIgnoreCase(request.getMethod())){
            try {
                doValidateCapth(request,response);

            }catch (XAuthenticationException e){
                failureHandler.onAuthenticationFailure(request,response,  e);
                return;
            }
        }
//        Map<String, String[]> m = new HashMap<String, String[]>(request.getParameterMap());
//        try {
//            addParameter(request,m);
//        }catch (XAuthenticationException e){
//            failureHandler.onAuthenticationFailure((HttpServletRequest) request,(HttpServletResponse) response,  e);
//            return;
//        }
//        ParameterRequestWrapper parameterRequestWrapper = new ParameterRequestWrapper((HttpServletRequest) request, m);
//
        filterChain.doFilter(request,response);
    }

    /** 验证码过滤器 */
    private void doValidateCapth(HttpServletRequest request, HttpServletResponse response){
//        System.out.println("验证码过滤器");
        String verCode = request.getParameter(ResponseCode.VER_CODE);
        String t = request.getParameter("_t");
        if(StringUtils.isNotBlank(t)&& StringUtils.isNotBlank(verCode)){
            Object o = redisUtil.get(ResponseCode.VER_CODE+"_" + t);
            if(o==null){
                throw new XAuthenticationException(ResponseCode.VER_CODE_ERROR);
            }
            if(verCode.equalsIgnoreCase(o.toString())){
                System.out.println("验证码验证成功");
//                redisUtil.del(ResponseCode.VER_CODE+"_" + t);//删除当前使用的验证码
                return;
            }
            throw new XAuthenticationException(ResponseCode.VER_CODE_ERROR);
        }
        throw new XAuthenticationException(ResponseCode.VER_CODE_ERROR);
    }



//    /** 添加参数 */
//    private void addParameter(ServletRequest servletRequest, Map<String, String[]> parameterMap){
//        System.out.println("参数过滤器");
//        HttpServletRequest request =(HttpServletRequest) servletRequest;
////        System.out.println("参数过滤器: "+request.getRequestURI());
//        String headerToken = request.getHeader(ResponseCode.TokenKey);
//        if(StringUtils.isNotBlank(headerToken)){
//            String uid = JWTHelp.getFromToken(headerToken, ResponseCode.ParamterUserIdKey);
//            Object redisToken = redisUtil.get(uid);
////            System.out.println("参数过滤器: 当前访问角色: "+JWTHelp.getFromToken(headerToken,"role"));
////            if(uid==null){
////                throw new XAuthenticationException(ResponseCode.USER_NOT_LOGIN.getMessage());
////            }
//            if(uid!=null){
//                //添加参数
//                if(redisToken.equals(headerToken)){
//                    parameterMap.put(ResponseCode.ParamterUserIdKey,new String[]{uid});
//                    parameterMap.put(ResponseCode.TokenKey,new String[]{headerToken});
//                }
//            }
//        }
//    }

}

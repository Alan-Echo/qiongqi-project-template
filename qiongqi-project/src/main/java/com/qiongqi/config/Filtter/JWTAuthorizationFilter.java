package com.qiongqi.config.Filtter;

import com.qiongqi.config.Exception.XException;
import com.qiongqi.config.security.handler.MyAuthenticationFailureHandler;
import com.qiongqi.utils.JWT.JWTHelp;
import com.qiongqi.utils.Utils;
import com.qiongqi.utils.response.code.ResponseCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: jwt令牌鉴权
 * @author: ※狗尾巴草
 * @date: 2020-12-15 17:08
 **/
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    @Autowired
    private MyAuthenticationFailureHandler failureHandler;
    private String[] urls = {
            "/app/common/verCode/imgCode/**",
            "/app/web/login",
            "/app/applet/login",
            "/auth/login",
            "/auth/logout",
            "/app/web/reg"
    };

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String tokenHeader = request.getHeader(ResponseCode.TokenKey);
        // 对请求头d的token，进行解析,获取用户权限
        SecurityContextHolder.getContext().setAuthentication(getAuthentication(tokenHeader));
        super.doFilterInternal(request, response, chain);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(String token) {
//        System.out.println("jwt令牌鉴权");
//        try {
        String uid = JWTHelp.getFromToken(token, ResponseCode.ParamterUserIdKey);
//        String redisToken = request.getParameter(ResponseCode.TokenKey);
        if(StringUtils.isNotBlank(uid)){
                String email = JWTHelp.getFromToken(token, "userName");
                String roles = JWTHelp.getFromToken(token,"role");
//                System.out.println("token校验: 当前访问角色: "+roles);
                List<GrantedAuthority> grantedAuthorities  = new ArrayList<>();
                for(String role: roles.split(",")){
                    if(StringUtils.isNotBlank(role)){
                        grantedAuthorities.add(new SimpleGrantedAuthority(role));
                    }
                }
                if (email != null){
                    return new UsernamePasswordAuthenticationToken(email, null,grantedAuthorities);
                }
        }else {
            throw new XException(ResponseCode.USER_NOT_LOGIN);
        }
//        } catch (Exception e) {
//            return null;
//        }
        return null;
    }
//
//    private ParameterRequestWrapper changeParamter(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//        Map<String, String[]> m = new HashMap<String, String[]>(request.getParameterMap());
//        try {
//            addParameter(request,m);
//        }catch (XAuthenticationException e){
//            failureHandler.onAuthenticationFailure(request,response,  e);
//            return null;
//        }
//       return new ParameterRequestWrapper((HttpServletRequest) request, m);
//
//    }
//
//    /** 添加参数 */
//    private void addParameter(ServletRequest servletRequest, Map<String, String[]> parameterMap){
//        HttpServletRequest request =(HttpServletRequest) servletRequest;
//        System.out.println("参数过滤器: "+request.getRequestURI());
//        String xtoken = request.getHeader(ResponseCode.TokenKey);
//        if(StringUtils.isNotBlank(xtoken)){
//            String uid = JWTHelp.getFromToken(xtoken, ResponseCode.ParamterUserIdKey);
////            if(uid==null){
////                throw new XAuthenticationException(ResponseCode.USER_NOT_LOGIN.getMessage());
////            }
//            if(uid!=null){
//                //添加参数
//                parameterMap.put(ResponseCode.ParamterUserIdKey,new String[]{uid});
//            }
//        }
//    }
//

}

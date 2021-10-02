package com.qiongqi.prj.template.utils.controller;

import com.qiongqi.prj.template.config.Exception.XException;
import com.qiongqi.prj.template.utils.JWT.TokenResult.TokenResult;
import com.qiongqi.prj.template.utils.JWT.XTokenUtils;
import com.qiongqi.prj.template.utils.response.code.ResponseCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;


public class BaseController {

    protected static final String ERROR_MSG_KEY = "errorMsg";
    /** 原始的request */
    public  HttpServletRequest getRequest(){
        return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
    }
    /** 原始的response */
    public  HttpServletResponse getResponse(){
        return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse();
    }
    /** 封装为Map的session attributes */
    protected HttpSession getSession() {
        return getRequest().getSession();
    }

    /**
     * 获取token
     * @return
     */
    public String getToken(){
        String token = getRequest().getHeader("X-Token");
        TokenResult tokenResult = XTokenUtils.parseToken(token);
        if(tokenResult.getCode()!=200){
            throw new XException(ResponseCode.USER_NOT_LOGIN);
        }
        return token;
    }

    /**
     * 获取登录用户Id
     * @return
     */
    public Long getUserId(){
        String token = getToken();
        if(token!=null){
            TokenResult tokenResult = XTokenUtils.parseToken(token);
            String userId = tokenResult.getData().get("userId").toString();
            if(StringUtils.isNotBlank(userId)){
                return Long.valueOf(userId);
            }
        }
        return null;
    }

    /** ③ 获取基于应用程序的url绝对路径   */
    public final String getAppbaseUrl(String url) {
        Assert.hasLength(url, "url不能为空");
        Assert.isTrue(url.startsWith("/"), "必须以/打头");
        return getRequest().getContextPath() + url;
    }
    /** 封装为Map的getRequest()uest parameters */
    public Map getParameters() {
        return getRequest().getParameterMap();
    }
    /** 封装为Map的session attributes */
    protected Map getSessionMap() {
        return (Map)getRequest().getSession();
    }
    /** 原始的ServletContext */
    public ServletContext getServletContext() {
        return getRequest().getSession().getServletContext();
    }
    /** 设置session的最大生命周期  maxInactiveInterval的单位为秒 */
    public void getSessionMaxInactiveInterval(int maxInactiveInterval) {
        getRequest().getSession().setMaxInactiveInterval(maxInactiveInterval);
    }

    /** 获取客户端IP地址 */
    public String getClientAddress() {
        String address = getRequest().getHeader("X-Forwarded-For");
        if (address != null) {
            return address;
        } else {
            address = getRequest().getRemoteAddr();
        }
        return address;
    }

    /**
     * 获得请求参数
     * @return
     */
    public HashMap<String, Object> getParamsToMap() {
        Map<String, String[]> parameterMap = getRequest().getParameterMap();
        HashMap<String, Object> map = new HashMap<String, Object>();
        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            String key = entry.getKey().toString();
            String[] value = parameterMap.get(key);
            map.put(key, value[0]);
        }
        return map;
    }


}

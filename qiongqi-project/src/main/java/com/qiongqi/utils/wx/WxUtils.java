package com.qiongqi.utils.wx;

import com.alibaba.fastjson.JSONObject;
import com.qiongqi.config.Exception.XException;
import com.qiongqi.config.Redis.RedisUtil;
import com.qiongqi.utils.http.Httprequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WxUtils {

    @Autowired
    private RedisUtil redisUtil;

    private String API = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";

    private String appid = "wxae5dc78dee4bffa1";

    private String secret="9d3821866dc2c84f23e4db87efbac52e";

    private long timeOut = 7150;

    public  String getAccessToken(){
        Object object = redisUtil.get(appid + "-img-check");
        if(object!=null){
            return object.toString();
        }
        String api = String.format(API, appid, secret);
        JSONObject jsonObject = Httprequest.get(api);
        //判断状态
        if(jsonObject.getIntValue("errcode")!=0){
            throw new XException(jsonObject.getIntValue("errcode"),jsonObject.getString("errmsg"));
        }
        //存储token
        redisUtil.set(appid + "-img-check",jsonObject.getString("access_token"),timeOut);
        return jsonObject.getString("access_token");
    }


    /**
     * auth.code2Session
     * https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/login/auth.code2Session.html
     * 请求参数   属性	     类型	   默认值	必填	      说明
     * @param   appId	     string		         是	   小程序 appId
     * @param   secret	     string		         是	   小程序 appSecret
     * @param   jsCode	     string		         是	   登录时获取的 code
     *          grantType	 string		         是	   授权类型，此处只需填写 authorization_code
     * 返回值
     * @return  JSON 数据包
     *           属性	     类型	   说明
     *          openid	     string	  用户唯一标识
     *          session_key	 string	  会话密钥
     *          unionid	     string	  用户在开放平台的唯一标识符，在满足 UnionID 下发条件的情况下会返回，详见 UnionID 机制说明。
     *          errcode	     number	  错误码
     *          errmsg	     string	  错误信息
     *
     *          errcode 的合法值
     *
     *          值	         说明	                     最低版本
     *          -1	         系统繁忙，此时请开发者稍候再试
     *          0	         请求成功
     *          40029	     code 无效
     *          45011	     频率限制，每个用户每分钟100次
     */
    public static JSONObject authCode2Session(String appId, String secret, String jsCode) {

        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + appId + "&secret=" + secret + "&js_code=" + jsCode + "&grant_type=authorization_code";
        JSONObject jsonObject = Httprequest.get(url);
        System.out.println(jsonObject);
//        if (StringUtils.isEmpty(str)) {
//            return null;
//        } else {
//            return JSONObject.parseObject(str);
//        }
        return jsonObject;
    }




}

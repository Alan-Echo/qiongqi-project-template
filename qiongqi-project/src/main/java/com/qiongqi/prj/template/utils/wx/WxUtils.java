package com.qiongqi.prj.template.utils.wx;

import com.alibaba.fastjson.JSONObject;
import com.qiongqi.prj.template.config.Exception.XException;
import com.qiongqi.prj.template.config.Redis.RedisUtil;
import com.qiongqi.prj.template.utils.http.Httprequest;
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







}

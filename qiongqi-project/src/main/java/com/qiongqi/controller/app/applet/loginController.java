package com.qiongqi.controller.app.applet;

import com.alibaba.fastjson.JSONObject;
import com.qiongqi.modules.applet.model.dto.VAppletDTO;
import com.qiongqi.modules.applet.service.VAppletService;
import com.qiongqi.modules.user.model.dto.VUserDTO;
import com.qiongqi.modules.user.model.entity.VUserEntity;
import com.qiongqi.modules.user.service.VUserService;
import com.qiongqi.utils.ConvertUtils;
import com.qiongqi.utils.JWT.XTokenUtils;
import com.qiongqi.utils.wx.WxUtils;
import com.qiongqi.utils.response.code.ResponseCode;
import com.qiongqi.utils.response.data.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: 小程序登录
 * @author: ※狗尾巴草
 * @date: 2021-03-22 22:29
 **/
@RequestMapping("/app/applet")
@RestController
public class loginController {

    @Autowired
    private VAppletService vAppletService;

    @Autowired
    private VUserService vUserService;

    /**
     * auth.code2Session
     * https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/login/auth.code2Session.html
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
    @PostMapping("login")
    public ResponseData login(@RequestBody JSONObject userInfo){
        System.out.println("登录:"+userInfo.getString("code"));
        VAppletDTO appletDTO = vAppletService.get(); //获取小程序配置
        JSONObject jsonObject = WxUtils.authCode2Session(appletDTO.getAppId(), appletDTO.getAppSecret(), userInfo.getString("code"));
        if(jsonObject.get("errcode")!=null){
            //登录失败
            return new ResponseData().fail(ResponseCode.USER_LOGIN_FAIL.getCode(),"登录失败");
        }
        //登录成功,
        String openid = jsonObject.getString("openid");
        VUserDTO byOpenId = vUserService.getByOpenId(openid); //根据openId查询数据库中是否有这个用户
        VUserEntity entity =null;
        if(byOpenId==null){//没有当前登录用户,保存新用户数据
            byOpenId = new VUserDTO();
            //设置新数据
            byOpenId.setAvatar(userInfo.getString("avatarUrl"));
            byOpenId.setNickName(userInfo.getString("nickName"));
            byOpenId.setOpenId(openid);
            entity = vUserService.save(byOpenId);//将数据存进数据库
            //生成登录成功的token令牌
        }else {
            //设置新数据
            byOpenId.setAvatar(userInfo.getString("avatarUrl"));
            byOpenId.setNickName(userInfo.getString("nickName"));
            byOpenId.setOpenId(openid);
            vUserService.update(byOpenId);
            entity = ConvertUtils.sourceToTarget(byOpenId,VUserEntity.class);
        }
        //将权限/角色放入token中
        Map<String, Object> map = new HashMap<>();
        map.put(ResponseCode.ParamterUserIdKey,entity.getId());
        map.put("role","ROLE_user"); //默认小程序用户属于"user" 用户组
        map.put("nickName",entity.getNickName());
        String token = null;
        try {
            token = XTokenUtils.createToken(map, ResponseCode.TokenExp);
            byOpenId.setToken(token);
            byOpenId.setId(entity.getId());
            return new ResponseData().success("登录成功",byOpenId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseData().fail("登录失败");
    }


}

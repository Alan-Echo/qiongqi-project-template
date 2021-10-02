package com.qiongqi.prj.template.controller.rear.common;

import com.qiongqi.prj.template.config.Redis.RedisUtil;
import com.qiongqi.prj.template.modules.admin.service.VAdminService;
import com.qiongqi.prj.template.utils.ImgCode.VerifyCodeUtil;
import com.qiongqi.prj.template.utils.response.code.ResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @description: 公共请求--验证码
 * @author: ※狗尾巴草
 * @date: 2020-08-20 23:00
 **/
@RestController
@CrossOrigin(allowCredentials="true",allowedHeaders="*")
@RequestMapping("app/common/verCode")
public class verCodeController {

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private VAdminService adminService;

    /** 获取图片验证码 */
    @GetMapping("imgCode/{_t}")
    public void registered(HttpServletRequest request, HttpServletResponse response, @PathVariable("_t") String _t) {
        String origin = response.getHeader("Origin");
        if(origin == null) {
            origin = response.getHeader("Referer");
        }
        response.setHeader("Access-Control-Allow-Origin", origin);//这里不能写*，*代表接受所有域名访问，如写*则下面一行代码无效。谨记
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
        response.setHeader("Access-Control-Allow-Credentials","true"); //是否支持cookie跨域

        response.setContentType("image/jpeg");//设置相应类型,告诉浏览器输出的内容为图片
        response.setHeader("Pragma", "No-cache");//设置响应头信息，告诉浏览器不要缓存此内容
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expire", 0);
        VerifyCodeUtil.getRandcode(request, response);
        redisUtil.set(ResponseCode.VER_CODE+"_"+_t,request.getSession().getAttribute(ResponseCode.VER_CODE), ResponseCode.VerImgVaildTime);
    }

}

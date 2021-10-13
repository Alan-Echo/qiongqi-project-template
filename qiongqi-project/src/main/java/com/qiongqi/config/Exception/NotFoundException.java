//package com.qiongqi.prj.template.config.Exception;
//
//import com.qiongqi.prj.template.utils.response.data.ResponseData;
//import org.springframework.boot.web.servlet.error.ErrorController;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import javax.servlet.http.HttpServletRequest;
//
//
///**
// * @description: 404 请求
// * @author: ※狗尾巴草
// * @date: 2020-07-08 15:23
// **/
//@Controller
//public class NotFoundException implements ErrorController {
//
//    @Override
//    public String getErrorPath() {
//        return "/error";
//    }
//
//    @RequestMapping(value = {"/error"})
//    @ResponseBody
//    public Object error(HttpServletRequest request) {
//        ResponseData<Object> data = new ResponseData<>();
//        data.setStatus(404);
//        data.setMsg("404 Not Found");
//        return data;
//    }
//}

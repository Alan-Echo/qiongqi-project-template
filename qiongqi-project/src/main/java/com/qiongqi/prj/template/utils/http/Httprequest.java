package com.qiongqi.prj.template.utils.http;

import com.alibaba.fastjson.JSONObject;
import com.github.kevinsawicki.http.HttpRequest;
import com.qiongqi.prj.template.utils.File.FileUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.Map;

/**
 * @description: 说明
 * @author: ※狗尾巴草
 * @date: 2021-03-22 23:01
 **/
public class Httprequest {

    /**
     * @description: http请求--post请求
     * @time: 2019/12/22 0:11
     */
    public static JSONObject post(String url,Map<String, Object> data){
        HttpRequest httpRequest = new HttpRequest(url, "POST");
        httpRequest.form(data);
        return JSONObject.parseObject(httpRequest.body());
    }


    /**
     * @description: http请求--get请求
     * @time: 2019/12/22 0:11
     */
    public static JSONObject get(String url){
        return JSONObject.parseObject(HttpRequest.get(url).body());
    }

    public static File getImg(String url,String fileName) throws Exception {
        HttpURLConnection connection = HttpRequest.get(url).getConnection();
        connection.addRequestProperty("User-Agent","Mozilla / 4.76");

        InputStream inputStream = connection.getInputStream();
        File temp = File.createTempFile(fileName, ".gif");
        System.out.println("文件路径: "+temp.getAbsolutePath());
        FileOutputStream outputStream = new FileOutputStream(temp);
        outputStream.write(FileUtils.readInputStream(inputStream));
        outputStream.close();
//        temp.deleteOnExit();//删除临时文件
//        FileUtils.copyInputStreamToFile(inputStream,temp);
        return temp;
    }


}

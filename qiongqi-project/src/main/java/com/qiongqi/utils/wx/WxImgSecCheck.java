package com.qiongqi.utils.wx;

import com.alibaba.fastjson.JSONObject;
import com.qiongqi.utils.File.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

public class WxImgSecCheck {

    @Autowired
    private WxUtils wxUtils;

    public JSONObject check( MultipartFile file){

        //压缩图片
        InputStream inputStream = FileUtils.compressImage(file);

        String token = wxUtils.getAccessToken();
        String url = "https://api.weixin.qq.com/wxa/img_sec_check?access_token=" + token;
        HttpClient httpclient = HttpClients.createDefault();
        HttpPost request = new HttpPost(url);
        request.addHeader("Content-Type", "application/octet-stream");
        try {
            //创建一个byte数组，和输入的文件的大小一样
            byte[] byt = new byte[inputStream.available()];
            //从输入流中读取全部，并将其存储在缓冲区数组byt 中。
            inputStream.read(byt);
            //定制提交内容
            request.setEntity(new ByteArrayEntity(byt, ContentType.create("image/jpg")));
//            由客户端执行(发送)请求,执行校验
            HttpResponse response = httpclient.execute(request);
            // 从响应模型中获取响应实体
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity, "UTF-8");// 转成string
            //打印校验结果
            System.out.println("图片检测结果：" + result);
            return JSONObject.parseObject(result);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if(inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }





}

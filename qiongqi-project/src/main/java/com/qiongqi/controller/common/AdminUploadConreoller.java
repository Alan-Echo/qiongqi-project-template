package com.qiongqi.controller.common;

import com.qiongqi.modules.qiniu.model.dto.VQiniuDTO;
import com.qiongqi.modules.qiniu.service.VQiniuService;
import com.qiongqi.utils.qiniu.QiniuUtil;
import com.qiongqi.utils.response.code.ResponseCode;
import com.qiongqi.utils.response.data.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @description: 文件上传接口
 * @author: ※狗尾巴草
 * @date: 2021-03-26 21:04
 **/
@RestController
@RequestMapping("/upload")
public class AdminUploadConreoller {

    @Autowired
    private VQiniuService vQiniuService;

    /** 
     * @description: 图片上传接口
     * @param file 图片文件
     * @author: ※狗尾巴草
     * @time: 2021-03-26 21:14
     */
    @PostMapping("/file")
    public ResponseData uploadImg(@RequestBody MultipartFile file){
        VQiniuDTO qiniuDTO = vQiniuService.getByStatus(1);
        QiniuUtil qiniuUtil = new QiniuUtil(qiniuDTO.getAk(),qiniuDTO.getSk(),qiniuDTO.getBucketName(),qiniuDTO.getZone(),qiniuDTO.getDoMain());
        try {
            String url = qiniuUtil.uploadImage(file);
            if(url!=null){
                return new ResponseData().success("上传成功",url);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseData().fail(ResponseCode.Upload_Error);
    }








}

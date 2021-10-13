package com.qiongqi.modules.applet.model.dto;

import com.qiongqi.config.MybatisPlus.dto.BasicDTO;
import lombok.Data;


/**
 * Description: 【 小程序配置 DTO 】
 *
 * @author : ※狗尾巴草
 * @version : 1.0.0
 * @email : "m962464oo@gmail.com"
 * @date : 2021-03-22 18:16:06
 */
@Data
public class VAppletDTO extends BasicDTO {

    // ==================== 字段名 ====================

    /**
     * 小程序APPID
     */
    private String appId;

    /**
     * 小程序密钥
     */
    private String appSecret;

    // ==================== 非持久 ====================


}

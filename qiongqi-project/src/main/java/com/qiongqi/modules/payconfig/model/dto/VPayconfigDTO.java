package com.qiongqi.modules.payconfig.model.dto;

import com.qiongqi.config.MybatisPlus.dto.BasicDTO;
import lombok.Data;


/**
 * Description: 【 支付相关配置 DTO 】
 *
 * @author : ※狗尾巴草
 * @version : 1.0.0
 * @email : "m962464oo@gmail.com"
 * @date : 2021-10-09 23:57:31
 */
@Data
public class VPayconfigDTO extends BasicDTO {

    // ==================== 字段名 ====================

    /**
     * 支付宝APPID
     */
    private String aliAppId;

    /**
     * 支付宝商户私钥
     */
    private String aliMerchantPrivateKey;

    /**
     * 支付宝公钥
     */
    private String aliPublicKey;

    /**
     * 支付通知回调域名
     */
    private String notifyUrl;

    /**
     * 支付有效期:(分钟)
     */
    private Integer payExTime=30;
    // ==================== 非持久 ====================


}

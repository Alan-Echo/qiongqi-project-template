package com.qiongqi.modules.payconfig.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.qiongqi.config.MybatisPlus.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Description: 【 支付相关配置 实体 】
 *
 * @author : ※狗尾巴草
 * @version : 1.0.0
 * @email : "m962464oo@gmail.com"
 * @date : 2021-10-09 23:57:31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("V_PAYCONFIG")
public class VPayconfigEntity extends BaseEntity<VPayconfigEntity> {

    // ==================== 字段名 ====================

    /**
     * 支付宝APPID
     */
    @TableField(value = "aliAppId")
    private String aliAppId;

    /**
     * 支付宝商户私钥
     */
    @TableField(value = "aliMerchantPrivateKey")
    private String aliMerchantPrivateKey;

    /**
     * 支付宝公钥
     */
    @TableField(value = "aliPublicKey")
    private String aliPublicKey;

    /**
     * 支付通知回调域名
     */
    @TableField(value = "notifyUrl")
    private String notifyUrl;
    /**
     * 支付有效期:(分钟)
     */
    private Integer payExTime=30;
    // ==================== 非持久 ====================


}

package com.qiongqi.modules.applet.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.qiongqi.config.MybatisPlus.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Description: 【 小程序配置 实体 】
 *
 * @author : ※狗尾巴草
 * @version : 1.0.0
 * @email : "m962464oo@gmail.com"
 * @date : 2021-03-22 18:16:06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("V_APPLET")
public class VAppletEntity extends BaseEntity<VAppletEntity> {

    // ==================== 字段名 ====================

    /**
     * 小程序APPID
     */
    @TableField(value = "appId")
    private String appId;

    /**
     * 小程序密钥
     */
    @TableField(value = "appSecret")
    private String appSecret;

    // ==================== 非持久 ====================


}

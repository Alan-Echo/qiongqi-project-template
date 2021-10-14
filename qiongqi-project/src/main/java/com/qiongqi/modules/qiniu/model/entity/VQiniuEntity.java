package com.qiongqi.modules.qiniu.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.qiongqi.config.MybatisPlus.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Description: 【 七牛云 实体 】
 *
 * @author : ※狗尾巴草
 * @version : 1.0.0
 * @email : "m962464oo@gmail.com"
 * @date : 2021-06-29 00:03:34
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("V_QINIU")
public class VQiniuEntity extends BaseEntity<VQiniuEntity> {

    // ==================== 字段名 ====================

    /**
     * 地址
     */
    @TableField(value = "zone")
    private String zone;

    /**
     * 空间名
     */
    @TableField(value = "bucketName")
    private String bucketName;

    /**
     * AK密码
     */
    @TableField(value = "ak")
    private String ak;

    /**
     * SK密钥
     */
    @TableField(value = "sk")
    private String sk;

    /**
     * 域名
     */
    @TableField(value = "doMain")
    private String doMain;

    /**
     * 状态:0:禁用;1启用
     */
    @TableField(value = "status")
    private Integer status;

    // ==================== 非持久 ====================


}

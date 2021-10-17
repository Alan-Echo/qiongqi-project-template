package com.qiongqi.modules.user.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.qiongqi.config.MybatisPlus.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * Description: 【 用户表 实体 】
 *
 * @author : ※狗尾巴草
 * @version : 1.0.0
 * @email : "m962464oo@gmail.com"
 * @date : 2021-01-16 14:22:50
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("V_USER")
public class VUserEntity extends BaseEntity<VUserEntity> {

    // ==================== 字段名 ====================

    /**
     * 微信openId
     */
    @TableField(value = "openId")
    private String openId;
    /**
     * 微信id
     */
    @TableField(value = "wxId")
    private String wxId;

    /**
     * 用户昵称
     */
    @TableField(value = "userName")
    private String userName;

    /**
     * 用户昵称
     */
    @TableField(value = "password")
    private String password;

    /**
     * QQ
     */
    @TableField(value = "qq")
    private String qq;

    /**
     * 邮箱
     */
    @TableField(value = "email")
    private String email;

    /**
     * 用户province
     */
    @TableField(value = "phone")
    private String phone;

    /**
     * 是否是VIP;1:是;0:否
     */
    @TableField(value = "isVip")
    private Integer isVip;

    /**
     * vip到期时间
     */
    @TableField(value = "expiredTime")
    private Date expiredTime;
    // ==================== 非持久 ====================


}

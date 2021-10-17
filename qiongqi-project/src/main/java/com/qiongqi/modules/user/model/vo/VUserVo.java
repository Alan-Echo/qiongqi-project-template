package com.qiongqi.modules.user.model.vo;

import lombok.Data;

import java.util.Date;

@Data
public class VUserVo {

    // ==================== 字段名 ====================

    /**
     * 微信openId
     */
    private String openId;

    /**
     * 用户昵称
     */
    private String userName;

    /**
     * QQ
     */
    private String qq;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 用户province
     */
    private String phone;


    /**
     * 是否是VIP;1:是;0:否
     */
    private Integer isVip;

    /**
     * vip到期时间
     */
    private Date expiredTime;
    // ==================== 非持久 ====================


    /** 登录令牌 */
    private String token;

    private String verCode;

}

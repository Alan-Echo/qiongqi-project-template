package com.qiongqi.modules.user.model.dto;

import com.qiongqi.config.MybatisPlus.dto.BasicDTO;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


/**
 * Description: 【 用户表 DTO 】
 *
 * @author : ※狗尾巴草
 * @version : 1.0.0
 * @email : "m962464oo@gmail.com"
 * @date : 2021-01-16 14:22:50
 */
@Data
public class VUserDTO extends BasicDTO {

    // ==================== 字段名 ====================
    /**
     * 用户昵称
     */
    private String userName;

    /**
     * 积分
     */
    private Integer integral;

    private String avatar;

    private Long pid;
    /**
     * 是否是VIP;1:是;0:否
     */
    private Integer isVip;

    /**
     * vip到期时间
     */
    private Date expiredTime;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 用户province
     */
    private String phone;
    /**
     * 微信ID
     */
    private String wxId;
    /**
     * 微信openId
     */
    private String openId;

    /**
     * QQ
     */
    private String qq;

    // ==================== 非持久 ====================
    private String token;
    private String verCode;

}

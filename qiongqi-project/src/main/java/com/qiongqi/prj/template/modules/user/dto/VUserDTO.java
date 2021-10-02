package com.qiongqi.prj.template.modules.user.dto;

import com.qiongqi.prj.template.config.MybatisPlus.dto.BasicDTO;
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
     * 微信ID
     */
    private String wxId;

    /**
     * 用户昵称
     */
    private String userName;

    private Integer score;

    @DateTimeFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    private Date clockIn;

    private String avatar;

    private Long robotId;

    private Long pid;

    private String groupName;

    private String groupWxId;


    /**
     * 微信openId
     */
    private String openId;

    /**
     * 用户昵称
     */
    private String nickName;

    private Integer gender;


    private String country;

    private String city;

    private String province;

    private String seesionKey;

    // ==================== 非持久 ====================
    private String token;
    private String robotName;
    private String pUserName;


}

package com.qiongqi.modules.user.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.qiongqi.config.MybatisPlus.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
     * 微信ID
     */
    @TableField(value = "wxId")
    private String wxId;

    /**
     * 用户昵称
     */
    @TableField(value = "userName")
    private String userName;

    /**
     * 用户积分
     */
    @TableField(value = "score")
    private Integer score;

    /**
     * 用户头像
     */
    @TableField(value = "avatar")
    private String avatar;

    /**
     *  机器人表的Id
     */
    @TableField(value = "robotId")
    private Long robotId;

    /**
     *  上级id
     */
    @TableField(value = "pid")
    private Long pid;

    /**
     *  群名
     */
    @TableField(value = "groupName")
    private String groupName;

    /**
     *  群wxId
     */
    @TableField(value = "groupWxId")
    private String groupWxId;


    /**
     * 微信ID
     */
    @TableField(value = "openId")
    private String openId;

    /**
     * 微信用户昵称
     */
    @TableField(value = "nickName")
    private String nickName;


    /**
     * 性别 0-未知 1-男,2-女
     */
    @TableField(value = "gender")
    private Integer gender;

    /**
     * 用户country
     */
    @TableField(value = "country")
    private String country;

    /**
     * 用户city
     */
    @TableField(value = "city")
    private String city;

    /**
     * 用户province
     */
    @TableField(value = "province")
    private String province;

    /**
     * 用户会话密钥
     */
    @TableField(value = "sessionKey")
    private String sessionKey;

    // ==================== 非持久 ====================


}

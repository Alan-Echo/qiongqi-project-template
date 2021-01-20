package com.qiongqi.video.guaikavideo.modules.user.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.qiongqi.video.guaikavideo.config.MybatisPlus.entity.BaseEntity;
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
    // ==================== 非持久 ====================


}

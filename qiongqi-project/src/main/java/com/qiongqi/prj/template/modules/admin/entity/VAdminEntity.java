package com.qiongqi.prj.template.modules.admin.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.qiongqi.prj.template.config.MybatisPlus.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * Description: 【 管理员 实体 】
 *
 * @author : ※狗尾巴草
 * @version : 1.0.0
 * @email : "m962464oo@gmail.com"
 * @date : 2021-01-16 14:22:49
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("V_ADMIN")
public class VAdminEntity extends BaseEntity<VAdminEntity> {

    // ==================== 字段名 ====================

    /**
     * 管理员登陆名
     */
    @TableField(value = "userName")
    private String userName;

    /**
     * 密码
     */
    @TableField(value = "password")
    private String password;

    /**
     * 上次登录时间
     */
    @TableField(value = "lastLoginTime")
    private Date lastLoginTime;

    // ==================== 非持久 ====================


}

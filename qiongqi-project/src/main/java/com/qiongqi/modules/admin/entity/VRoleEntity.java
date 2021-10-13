package com.qiongqi.modules.admin.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.qiongqi.config.MybatisPlus.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Description: 【 角色表 实体 】
 *
 * @author : ※狗尾巴草
 * @version : 1.0.0
 * @email : "m962464oo@gmail.com"
 * @date : 2021-01-16 14:58:33
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("V_ROLE")
public class VRoleEntity extends BaseEntity<VRoleEntity> {

    // ==================== 字段名 ====================

    /**
     * 角色名
     */
    @TableField(value = "name")
    private String name;

    /**
     * 角色标识符
     */
    @TableField(value = "code")
    private String code;

    // ==================== 非持久 ====================


}

package com.qiongqi.modules.vip.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.qiongqi.config.MybatisPlus.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * Description: 【 vip会员 实体 】
 *
 * @author : ※狗尾巴草
 * @version : 1.0.0
 * @email : "m962464oo@gmail.com"
 * @date : 2021-09-15 22:25:15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("V_VIP")
public class VVipEntity extends BaseEntity<VVipEntity> {

    // ==================== 字段名 ====================

    /**
     * 名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 价格
     */
    @TableField(value = "total")
    private BigDecimal total;

    /**
     * 类型;1:周卡;2:月卡;3:年卡
     */
    private Integer type=1;

    /**
     * 说明
     */
    @TableField(value = "remark")
    private String remark;

    @TableField(value = "img")
    private String img;

    /**
     * 销量
     */
    @TableField(value = "sales")
    private Integer sales=0;
    // ==================== 非持久 ====================


}

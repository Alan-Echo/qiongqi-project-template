package com.qiongqi.modules.order.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.qiongqi.config.MybatisPlus.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * Description: 【 订单 实体 】
 *
 * @author : ※狗尾巴草
 * @version : 1.0.0
 * @email : "m962464oo@gmail.com"
 * @date : 2021-10-09 23:57:42
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("V_ORDER")
public class VOrderEntity extends BaseEntity<VOrderEntity> {

    // ==================== 字段名 ====================

    /**
     * 订单金额
     */
    @TableField(value = "total")
    private BigDecimal total;

    /**
     * 用户ID
     */
    @TableField(value = "userId")
    private Long userId;

    /**
     * 商品ID
     */
    @TableField(value = "goodsId")
    private Long goodsId;

    /**
     * 是否支付;1:是;0:否
     */
    @TableField(value = "isPay")
    private Integer isPay;

    /**
     * 用户名
     */
    @TableField(value = "userName")
    private String userName;

    /**
     * 商品名
     */
    @TableField(value = "goodsName")
    private String goodsName;

    /**
     * 类型;1:周卡;2:月卡;3:年卡
     */
    @TableField(value = "orderType")
    private Integer orderType;

    /**
     * 订单数量
     */
    @TableField(value = "orderNum")
    private Integer orderNum;

    /**
     * 支付类型;1;支付宝;2;微信
     */
    private Integer payType;
    // ==================== 非持久 ====================


}

package com.qiongqi.modules.log_moneys.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.qiongqi.config.MybatisPlus.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * Description: 【 资金流水表 实体 】
 *
 * @author : ※狗尾巴草
 * @version : 1.0.0
 * @email : "m962464oo@gmail.com"
 * @date : 2021-10-12 01:30:01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("V_LOG_MONEYS")
public class VLogMoneysEntity extends BaseEntity<VLogMoneysEntity> {

    // ==================== 字段名 ====================

    /**
     * 用户ID/商家ID
     */
    @TableField(value = "userId")
    private Long userId;

    /**
     * 商品ID
     */
    @TableField(value = "goodsId")
    private Long goodsId;

    /**
     * 流水来源;1:交易订单 2:订单结算 3:提现申请 4.退款订单
     */
    @TableField(value = "dataSrc")
    private Integer dataSrc;

    /**
     * 备注/注释
     */
    @TableField(value = "remark")
    private String remark;

    /**
     * 流水标志;1:收入 0:支出
     */
    @TableField(value = "moneyType")
    private Integer moneyType;

    /**
     * 金额
     */
    @TableField(value = "money")
    private BigDecimal money;

    /**
     * 外部流水号;交易流水号
     */
    @TableField(value = "tradeNo")
    private String tradeNo;

    /**
     * 支付类型;1:支付宝;2:微信
     */
    @TableField(value = "payType")
    private Integer payType;

    /**
     * 有效状态;1:有效 -1:删除
     */
    @TableField(value = "dataFlag")
    private Integer dataFlag;

    /**
     * 订单ID
     */
    @TableField(value = "orderId")
    private Long orderId;

    // ==================== 非持久 ====================


}

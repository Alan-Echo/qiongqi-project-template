package com.qiongqi.modules.log_moneys.model.dto;

import com.qiongqi.config.MybatisPlus.dto.BasicDTO;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Description: 【 资金流水表 DTO 】
 *
 * @author : ※狗尾巴草
 * @version : 1.0.0
 * @email : "m962464oo@gmail.com"
 * @date : 2021-10-12 01:30:01
 */
@Data
public class VLogMoneysDTO extends BasicDTO {

    // ==================== 字段名 ====================

    /**
     * 用户ID/商家ID
     */
    private Long userId;

    /**
     * 商品ID
     */
    private Long goodsId;

    /**
     * 流水来源;1:交易订单 2:订单结算 3:提现申请 4.退款订单
     */
    private Integer dataSrc;

    /**
     * 备注/注释
     */
    private String remark;

    /**
     * 流水标志;1:收入 0:支出
     */
    private Integer moneyType;

    /**
     * 金额
     */
    private BigDecimal money;

    /**
     * 外部流水号;交易流水号
     */
    private String tradeNo;

    /**
     * 支付类型;1:支付宝;2:微信
     */
    private Integer payType;

    /**
     * 有效状态;1:有效 -1:删除
     */
    private Integer dataFlag;

    /**
     * 订单ID
     */
    private Long orderId;

    // ==================== 非持久 ====================


}

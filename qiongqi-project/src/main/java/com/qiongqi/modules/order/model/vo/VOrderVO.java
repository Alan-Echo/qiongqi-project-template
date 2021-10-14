package com.qiongqi.modules.order.model.vo;

import com.qiongqi.config.MybatisPlus.vo.BasicVO;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Description: 【 订单 DTO 】
 *
 * @author : ※狗尾巴草
 * @version : 1.0.0
 * @email : "m962464oo@gmail.com"
 * @date : 2021-10-09 23:57:42
 */
@Data
public class VOrderVO extends BasicVO {

    // ==================== 字段名 ====================

    /**
     * 订单金额
     */
    private BigDecimal total;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 商品ID
     */
    private Long goodsId;

    /**
     * 是否支付;1:是;0:否
     */
    private Integer isPay;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 商品名
     */
    private String goodsName;

    /**
     * 类型;1:周卡;2:月卡;3:年卡
     */
    private Integer orderType;

    /**
     * 订单数量
     */
    private Integer orderNum;

    /**
     * 支付类型;1;支付宝;2;微信
     */
    private Integer payType;
    // ==================== 非持久 ====================


}

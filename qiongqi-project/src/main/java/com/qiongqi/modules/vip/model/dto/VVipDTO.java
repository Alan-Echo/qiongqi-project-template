package com.qiongqi.modules.vip.model.dto;

import com.qiongqi.config.MybatisPlus.dto.BasicDTO;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Description: 【 vip会员 DTO 】
 *
 * @author : ※狗尾巴草
 * @version : 1.0.0
 * @email : "m962464oo@gmail.com"
 * @date : 2021-09-15 22:25:15
 */
@Data
public class VVipDTO extends BasicDTO {

    // ==================== 字段名 ====================

    /**
     * 名称
     */
    private String name;

    /**
     * 价格
     */
    private BigDecimal total;

    /**
     * 类型;1:周卡;2:月卡;3:年卡
     */
    private Integer type=1;

    /**
     * 说明
     */
    private String remark;

    private String img;
    /**
     * 销量
     */
    private Integer sales=0;
    // ==================== 非持久 ====================


}

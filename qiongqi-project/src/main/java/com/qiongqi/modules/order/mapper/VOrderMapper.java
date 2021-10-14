package com.qiongqi.modules.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qiongqi.modules.order.model.dto.VOrderDTO;
import com.qiongqi.modules.order.model.entity.VOrderEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Description: 【 订单 数据库操作Mapper 】
 *
 * @author : ※狗尾巴草
 * @version : 1.0.0
 * @email : "m962464oo@gmail.com"
 * @date : 2021-10-09 23:57:42
 */
@Mapper
public interface VOrderMapper extends BaseMapper<VOrderEntity> {

    // ==================== 查询 ====================


    /**
     * 描述: 【 通过ID查询单条数据 】
     *
     * @param id ID
     * @return
     * @author: ※狗尾巴草
     * @email: "m962464oo@gmail.com"
     * @date: 2021-10-09 23:57:42
     */
    @Select({"<script>" +
            "	SELECT T.* " +
            "   FROM V_ORDER T " +
            "   WHERE T.ID = #{id}" +
            "</script>"
    })
    VOrderDTO queryOneById(@Param("id") Long id);

    // ==================== ADD、MODIFY、REMOVE ===================


    // ==================== 其他 ====================

}

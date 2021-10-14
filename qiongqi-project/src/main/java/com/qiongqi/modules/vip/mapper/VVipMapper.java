package com.qiongqi.modules.vip.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qiongqi.modules.vip.model.dto.VVipDTO;
import com.qiongqi.modules.vip.model.entity.VVipEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Description: 【 vip会员 数据库操作Mapper 】
 *
 * @author : ※狗尾巴草
 * @version : 1.0.0
 * @email : "m962464oo@gmail.com"
 * @date : 2021-09-15 22:25:15
 */
@Mapper
public interface VVipMapper extends BaseMapper<VVipEntity> {

    // ==================== 查询 ====================


    /**
     * 描述: 【 通过ID查询单条数据 】
     *
     * @param id ID
     * @return
     * @author: ※狗尾巴草
     * @email: "m962464oo@gmail.com"
     * @date: 2021-09-15 22:25:15
     */
    @Select({"<script>" +
            "	SELECT T.* " +
            "   FROM V_VIP T " +
            "   WHERE T.ID = #{id}" +
            "</script>"
    })
    VVipDTO queryOneById(@Param("id") Long id);

    // ==================== ADD、MODIFY、REMOVE ===================


    // ==================== 其他 ====================

}

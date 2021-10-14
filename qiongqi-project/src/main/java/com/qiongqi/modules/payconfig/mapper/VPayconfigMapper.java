package com.qiongqi.modules.payconfig.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qiongqi.modules.payconfig.model.dto.VPayconfigDTO;
import com.qiongqi.modules.payconfig.model.entity.VPayconfigEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Description: 【 支付相关配置 数据库操作Mapper 】
 *
 * @author : ※狗尾巴草
 * @version : 1.0.0
 * @email : "m962464oo@gmail.com"
 * @date : 2021-10-09 23:57:31
 */
@Mapper
public interface VPayconfigMapper extends BaseMapper<VPayconfigEntity> {

    // ==================== 查询 ====================


    /**
     * 描述: 【 通过ID查询单条数据 】
     *
     * @param id ID
     * @return
     * @author: ※狗尾巴草
     * @email: "m962464oo@gmail.com"
     * @date: 2021-10-09 23:57:31
     */
    @Select({"<script>" +
            "	SELECT T.* " +
            "   FROM V_PAYCONFIG T " +
            "   WHERE T.ID = #{id}" +
            "</script>"
    })
    VPayconfigDTO queryOneById(@Param("id") Long id);

    // ==================== ADD、MODIFY、REMOVE ===================


    // ==================== 其他 ====================

}

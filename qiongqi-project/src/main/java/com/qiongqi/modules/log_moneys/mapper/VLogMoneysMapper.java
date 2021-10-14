package com.qiongqi.modules.log_moneys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qiongqi.modules.log_moneys.model.dto.VLogMoneysDTO;
import com.qiongqi.modules.log_moneys.model.entity.VLogMoneysEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Description: 【 资金流水表 数据库操作Mapper 】
 *
 * @author : ※狗尾巴草
 * @version : 1.0.0
 * @email : "m962464oo@gmail.com"
 * @date : 2021-10-12 01:30:01
 */
@Mapper
public interface VLogMoneysMapper extends BaseMapper<VLogMoneysEntity> {

    // ==================== 查询 ====================


    /**
     * 描述: 【 通过ID查询单条数据 】
     *
     * @param id ID
     * @return
     * @author: ※狗尾巴草
     * @email: "m962464oo@gmail.com"
     * @date: 2021-10-12 01:30:01
     */
    @Select({"<script>" +
            "	SELECT T.* " +
            "   FROM V_LOG_MONEYS T " +
            "   WHERE T.ID = #{id}" +
            "</script>"
    })
    VLogMoneysDTO queryOneById(@Param("id") Long id);

    // ==================== ADD、MODIFY、REMOVE ===================


    // ==================== 其他 ====================

}

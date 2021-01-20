package com.qiongqi.video.guaikavideo.modules.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qiongqi.video.guaikavideo.modules.admin.dto.VAdminDTO;
import com.qiongqi.video.guaikavideo.modules.admin.entity.VAdminEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Description: 【 管理员 数据库操作Mapper 】
 *
 * @author : ※狗尾巴草
 * @version : 1.0.0
 * @email : "m962464oo@gmail.com"
 * @date : 2021-01-16 14:22:49
 */
@Mapper
public interface VAdminMapper extends BaseMapper<VAdminEntity> {

    // ==================== 查询 ====================


    /**
     * 描述: 【 通过ID查询单条数据 】
     *
     * @param id ID
     * @return
     * @author: ※狗尾巴草
     * @email: "m962464oo@gmail.com"
     * @date: 2021-01-16 14:22:49
     */
    @Select({"<script>" +
            "	SELECT T.* " +
            "   FROM V_ADMIN T " +
            "   WHERE T.ID = #{id}" +
            "</script>"
    })
    VAdminDTO queryOneById(@Param("id") Long id);

    @Select("select * from V_ADMIN where userName = #{username}")
    VAdminDTO getByUserName(@Param("username") String username);

    @Select("select * from V_ADMIN ")
    VAdminDTO get();

    // ==================== ADD、MODIFY、REMOVE ===================


    // ==================== 其他 ====================

}

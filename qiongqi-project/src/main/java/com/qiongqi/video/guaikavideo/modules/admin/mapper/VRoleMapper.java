package com.qiongqi.video.guaikavideo.modules.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qiongqi.video.guaikavideo.modules.admin.dto.VRoleDTO;
import com.qiongqi.video.guaikavideo.modules.admin.entity.VRoleEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Description: 【 角色表 数据库操作Mapper 】
 *
 * @author : ※狗尾巴草
 * @version : 1.0.0
 * @email : "m962464oo@gmail.com"
 * @date : 2021-01-16 14:58:33
 */
@Mapper
public interface VRoleMapper extends BaseMapper<VRoleEntity> {

    // ==================== 查询 ====================


    /**
     * 描述: 【 通过ID查询单条数据 】
     *
     * @param id ID
     * @return
     * @author: ※狗尾巴草
     * @email: "m962464oo@gmail.com"
     * @date: 2021-01-16 14:58:33
     */
    @Select({"<script>" +
            "	SELECT T.* " +
            "   FROM V_ROLE T " +
            "   WHERE T.ID = #{id}" +
            "</script>"
    })
    VRoleDTO queryOneById(@Param("id") Long id);

    // ==================== ADD、MODIFY、REMOVE ===================


    // ==================== 其他 ====================

}

package com.qiongqi.modules.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qiongqi.modules.user.dto.VUserDTO;
import com.qiongqi.modules.user.entity.VUserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Description: 【 用户表 数据库操作Mapper 】
 *
 * @author : ※狗尾巴草
 * @version : 1.0.0
 * @email : "m962464oo@gmail.com"
 * @date : 2021-01-16 14:22:50
 */
@Mapper
public interface VUserMapper extends BaseMapper<VUserEntity> {

    // ==================== 查询 ====================


    /**
     * 描述: 【 通过ID查询单条数据 】
     *
     * @param id ID
     * @return
     * @author: ※狗尾巴草
     * @email: "m962464oo@gmail.com"
     * @date: 2021-01-16 14:22:50
     */
    @Select({"<script>" +
            "	SELECT T.* " +
            "   FROM V_USER T " +
            "   WHERE T.ID = #{id}" +
            "</script>"
    })
    VUserDTO queryOneById(@Param("id") Long id);

    @Select("select * from V_USER where wxId = #{wxId}")
    List<VUserDTO> getByWxId(@Param("wxId") String wxId);

    @Select("select * from V_USER where wxId = #{wxId} and robotId = #{robotId}")
    VUserDTO getByWxIdAndRobotId(@Param("wxId") String wxId,@Param("robotId") Long robotId);

    @Select("select * from V_USER where userName = #{userName}")
    List<VUserDTO> getByUserName(@Param("userName") String userName);

    @Select("select * from V_USER where openId = #{openId} ")
    VUserDTO getByOpenId(@Param("openId") String openId);

    // ==================== ADD、MODIFY、REMOVE ===================


    // ==================== 其他 ====================

}

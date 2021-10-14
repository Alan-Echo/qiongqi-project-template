package com.qiongqi.modules.qiniu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qiongqi.modules.qiniu.model.dto.VQiniuDTO;
import com.qiongqi.modules.qiniu.model.entity.VQiniuEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Description: 【 七牛云 数据库操作Mapper 】
 *
 * @author : ※狗尾巴草
 * @version : 1.0.0
 * @email : "m962464oo@gmail.com"
 * @date : 2021-06-29 00:03:34
 */
@Mapper
public interface VQiniuMapper extends BaseMapper<VQiniuEntity> {

    // ==================== 查询 ====================


    /**
     * 描述: 【 通过ID查询单条数据 】
     *
     * @param id ID
     * @return
     * @author: ※狗尾巴草
     * @email: "m962464oo@gmail.com"
     * @date: 2021-06-29 00:03:34
     */
    @Select({"<script>" +
            "	SELECT T.* " +
            "   FROM V_QINIU T " +
            "   WHERE T.ID = #{id}" +
            "</script>"
    })
    VQiniuDTO queryOneById(@Param("id") Long id);

    @Select("select * from V_QINIU where status = #{status} limit 1")
    VQiniuDTO getByStatus(@Param("status") int status);

    // ==================== ADD、MODIFY、REMOVE ===================


    // ==================== 其他 ====================

}

package com.qiongqi.prj.template.modules.applet.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qiongqi.prj.template.modules.applet.model.dto.VAppletDTO;
import com.qiongqi.prj.template.modules.applet.model.entity.VAppletEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * Description: 【 小程序配置 数据库操作Mapper 】
 *
 * @author : ※狗尾巴草
 * @version : 1.0.0
 * @email : "m962464oo@gmail.com"
 * @date : 2021-03-22 18:16:06
 */
@Mapper
public interface VAppletMapper extends BaseMapper<VAppletEntity> {

    // ==================== 查询 ====================


    /**
     * 描述: 【 通过ID查询单条数据 】
     *
     * @author: ※狗尾巴草
     * @email: "m962464oo@gmail.com"
     * @date: 2021-03-22 18:16:06
     */
    @Select({"<script>" +
            "	SELECT T.* " +
            "   FROM V_APPLET T " +
            "   limit 1" +
            "</script>"
    })
    VAppletDTO query();

    @Delete("delete from V_APPLET")
    boolean removeAll();

    // ==================== ADD、MODIFY、REMOVE ===================


    // ==================== 其他 ====================

}

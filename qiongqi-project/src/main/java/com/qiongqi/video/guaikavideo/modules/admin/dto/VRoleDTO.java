package com.qiongqi.video.guaikavideo.modules.admin.dto;

import com.qiongqi.video.guaikavideo.config.MybatisPlus.dto.BasicDTO;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


/**
 * Description: 【 角色表 DTO 】
 *
 * @author : ※狗尾巴草
 * @version : 1.0.0
 * @email : "m962464oo@gmail.com"
 * @date : 2021-01-16 14:58:33
 */
@Data
public class VRoleDTO extends BasicDTO {

    // ==================== 字段名 ====================

    /**
     * 角色名
     */
    private String name;

    /**
     * 角色标识符
     */
    private String code;

    // ==================== 非持久 ====================


}

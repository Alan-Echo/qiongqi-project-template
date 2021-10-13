package com.qiongqi.modules.admin.dto;

import com.qiongqi.config.MybatisPlus.dto.BasicDTO;
import lombok.Data;

import java.util.Date;


/**
 * Description: 【 管理员 DTO 】
 *
 * @author : ※狗尾巴草
 * @version : 1.0.0
 * @email : "m962464oo@gmail.com"
 * @date : 2021-01-16 14:22:49
 */
@Data
public class VAdminDTO extends BasicDTO {

    // ==================== 字段名 ====================

    /**
     * 管理员登陆名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    private Date lastLoginTime;
    // ==================== 非持久 ====================


}

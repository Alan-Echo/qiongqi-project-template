package com.qiongqi.modules.admin.model.vo;

import lombok.Data;

import java.util.Date;

@Data
public class VAdminVo {


    /**
     * 管理员登陆名
     */
    private String userName;

    /**
     * 邮箱
     */
    private String mail;

    /**
     * 上次登录时间
     */
    private Date lastLoginTime;

}

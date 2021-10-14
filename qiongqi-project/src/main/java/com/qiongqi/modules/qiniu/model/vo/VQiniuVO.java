package com.qiongqi.modules.qiniu.model.vo;

import com.qiongqi.config.MybatisPlus.vo.BasicVO;
import lombok.Data;


/**
 * Description: 【 七牛云 DTO 】
 *
 * @author : ※狗尾巴草
 * @version : 1.0.0
 * @email : "m962464oo@gmail.com"
 * @date : 2021-06-29 00:03:34
 */
@Data
public class VQiniuVO extends BasicVO {

    // ==================== 字段名 ====================

    /**
     * 地址
     */
    private String zone;

    /**
     * 空间名
     */
    private String bucketName;

    /**
     * AK密码
     */
    private String ak;

    /**
     * SK密钥
     */
    private String sk;

    /**
     * 域名
     */
    private String doMain;

    /**
     * 状态:0:禁用;1启用
     */
    private Integer status;

    // ==================== 非持久 ====================


}

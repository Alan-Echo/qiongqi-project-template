package com.qiongqi.modules.applet.service;


import com.qiongqi.config.MybatisPlus.service.BaseService;
import com.qiongqi.modules.applet.model.dto.VAppletDTO;
import com.qiongqi.modules.applet.model.entity.VAppletEntity;

/**
 * Description: 【 小程序配置 Service接口 】
 *
 * @author : ※狗尾巴草
 * @version : 1.0.0
 * @email : "m962464oo@gmail.com"
 * @date : 2021-03-22 18:16:06
 */
public interface VAppletService extends BaseService<VAppletEntity> {

    // ==================== 查询 ====================

    /**
     * Description:【 通过ID获取单条数据 】
     *
     * @return
     * @author: ※狗尾巴草
     * @email: "m962464oo@gmail.com"
     * @date: 2021-03-22 18:16:06
     */
    VAppletDTO get();

    // ==================== ADD、MODIFY、REMOVE ===================

    /**
     * Description:【 保存到数据库中 】
     *
     * @param dto 数据
     * @return
     * @author: ※狗尾巴草
     * @email: "m962464oo@gmail.com"
     * @date: 2021-03-22 18:16:06
     */
    boolean save(VAppletDTO dto);

    /**
     * Description:【 修改数据 】
     *
     * @param dto 数据
     * @return
     * @author: ※狗尾巴草
     * @email: "m962464oo@gmail.com"
     * @date: 2021-03-22 18:16:06
     */
    boolean update(VAppletDTO dto);

    boolean removeAll();


    // ==================== 其他 ====================

}

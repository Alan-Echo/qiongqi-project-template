package com.qiongqi.modules.payconfig.service;

import com.qiongqi.config.MybatisPlus.service.BaseService;
import com.qiongqi.modules.payconfig.model.dto.VPayconfigDTO;
import com.qiongqi.modules.payconfig.model.entity.VPayconfigEntity;
import com.qiongqi.utils.page.PageData;

import java.util.List;
import java.util.Map;

/**
 * Description: 【 支付相关配置 Service接口 】
 *
 * @author : ※狗尾巴草
 * @version : 1.0.0
 * @email : "m962464oo@gmail.com"
 * @date : 2021-10-09 23:57:31
 */
public interface VPayconfigService extends BaseService<VPayconfigEntity> {

    // ==================== 查询 ====================

    /**
     * Description:【 分页条件查询 】
     *
     * @param queryParams 查询参数
     * @return
     * @author: ※狗尾巴草
     * @email: "m962464oo@gmail.com"
     * @date: 2021-10-09 23:57:31
     */
    PageData<VPayconfigDTO> page(Map<String, Object> queryParams);

    /**
     * Description:【 条件查询 】
     *
     * @param queryParams 查询参数
     * @return
     * @author: ※狗尾巴草
     * @email: "m962464oo@gmail.com"
     * @date: 2021-10-09 23:57:31
     */
    List<VPayconfigDTO> list(Map<String, Object> queryParams);

    /**
     * Description:【 通过ID获取单条数据 】
     *
     * @param id
     * @return
     * @author: ※狗尾巴草
     * @email: "m962464oo@gmail.com"
     * @date: 2021-10-09 23:57:31
     */
    VPayconfigDTO get(Long id);

    // ==================== ADD、MODIFY、REMOVE ===================

    /**
     * Description:【 保存到数据库中 】
     *
     * @param dto 数据
     * @return
     * @author: ※狗尾巴草
     * @email: "m962464oo@gmail.com"
     * @date: 2021-10-09 23:57:31
     */
    boolean save(VPayconfigDTO dto);

    /**
     * Description:【 修改数据 】
     *
     * @param dto 数据
     * @return
     * @author: ※狗尾巴草
     * @email: "m962464oo@gmail.com"
     * @date: 2021-10-09 23:57:31
     */
    boolean update(VPayconfigDTO dto);

    /**
     * Description:【 通过ID删除数据 】
     *
     * @param id
     * @return
     * @author: ※狗尾巴草
     * @email: "m962464oo@gmail.com"
     * @date: 2021-10-09 23:57:31
     */
    boolean delete(Long id);

    /**
     * Description:【 通过ID数组,批量删除定时任务 】
     *
     * @param ids ID数组
     * @return
     * @author: ※狗尾巴草
     * @email: "m962464oo@gmail.com"
     * @date: 2021-10-09 23:57:31
     */
    boolean deleteBatch(Long[] ids);


    // ==================== 其他 ====================

}

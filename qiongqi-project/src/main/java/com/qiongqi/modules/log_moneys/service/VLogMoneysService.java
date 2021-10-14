package com.qiongqi.modules.log_moneys.service;

import com.qiongqi.config.MybatisPlus.service.BaseService;
import com.qiongqi.modules.log_moneys.model.dto.VLogMoneysDTO;
import com.qiongqi.modules.log_moneys.model.entity.VLogMoneysEntity;
import com.qiongqi.utils.page.PageData;

import java.util.List;
import java.util.Map;

/**
 * Description: 【 资金流水表 Service接口 】
 *
 * @author : ※狗尾巴草
 * @version : 1.0.0
 * @email : "m962464oo@gmail.com"
 * @date : 2021-10-12 01:30:01
 */
public interface VLogMoneysService extends BaseService<VLogMoneysEntity> {

    // ==================== 查询 ====================

    /**
     * Description:【 分页条件查询 】
     *
     * @param queryParams 查询参数
     * @return
     * @author: ※狗尾巴草
     * @email: "m962464oo@gmail.com"
     * @date: 2021-10-12 01:30:01
     */
    PageData<VLogMoneysDTO> page(Map<String, Object> queryParams);

    /**
     * Description:【 条件查询 】
     *
     * @param queryParams 查询参数
     * @return
     * @author: ※狗尾巴草
     * @email: "m962464oo@gmail.com"
     * @date: 2021-10-12 01:30:01
     */
    List<VLogMoneysDTO> list(Map<String, Object> queryParams);

    /**
     * Description:【 通过ID获取单条数据 】
     *
     * @param id
     * @return
     * @author: ※狗尾巴草
     * @email: "m962464oo@gmail.com"
     * @date: 2021-10-12 01:30:01
     */
    VLogMoneysDTO get(Long id);

    // ==================== ADD、MODIFY、REMOVE ===================

    /**
     * Description:【 保存到数据库中 】
     *
     * @param dto 数据
     * @return
     * @author: ※狗尾巴草
     * @email: "m962464oo@gmail.com"
     * @date: 2021-10-12 01:30:01
     */
    boolean save(VLogMoneysDTO dto);

    /**
     * Description:【 修改数据 】
     *
     * @param dto 数据
     * @return
     * @author: ※狗尾巴草
     * @email: "m962464oo@gmail.com"
     * @date: 2021-10-12 01:30:01
     */
    boolean update(VLogMoneysDTO dto);

    /**
     * Description:【 通过ID删除数据 】
     *
     * @param id
     * @return
     * @author: ※狗尾巴草
     * @email: "m962464oo@gmail.com"
     * @date: 2021-10-12 01:30:01
     */
    boolean delete(Long id);

    /**
     * Description:【 通过ID数组,批量删除定时任务 】
     *
     * @param ids ID数组
     * @return
     * @author: ※狗尾巴草
     * @email: "m962464oo@gmail.com"
     * @date: 2021-10-12 01:30:01
     */
    boolean deleteBatch(Long[] ids);


    // ==================== 其他 ====================

}

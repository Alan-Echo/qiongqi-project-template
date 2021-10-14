package com.qiongqi.modules.vip.service;

import com.qiongqi.config.MybatisPlus.service.BaseService;
import com.qiongqi.modules.vip.model.dto.VVipDTO;
import com.qiongqi.modules.vip.model.entity.VVipEntity;
import com.qiongqi.modules.vip.model.vo.VVipVO;
import com.qiongqi.utils.page.PageData;

import java.util.List;
import java.util.Map;

/**
 * Description: 【 vip会员 Service接口 】
 *
 * @author : ※狗尾巴草
 * @version : 1.0.0
 * @email : "m962464oo@gmail.com"
 * @date : 2021-09-15 22:25:15
 */
public interface VVipService extends BaseService<VVipEntity> {

    // ==================== 查询 ====================

    /**
     * Description:【 分页条件查询 】
     *
     * @param queryParams 查询参数
     * @return
     * @author: ※狗尾巴草
     * @email: "m962464oo@gmail.com"
     * @date: 2021-09-15 22:25:15
     */
    PageData<VVipDTO> page(Map<String, Object> queryParams);

    /**
     * Description:【 条件查询 】
     *
     * @param queryParams 查询参数
     * @return
     * @author: ※狗尾巴草
     * @email: "m962464oo@gmail.com"
     * @date: 2021-09-15 22:25:15
     */
    List<VVipVO> list(Map<String, Object> queryParams);

    /**
     * Description:【 通过ID获取单条数据 】
     *
     * @param id
     * @return
     * @author: ※狗尾巴草
     * @email: "m962464oo@gmail.com"
     * @date: 2021-09-15 22:25:15
     */
    VVipDTO get(Long id);

    // ==================== ADD、MODIFY、REMOVE ===================

    /**
     * Description:【 保存到数据库中 】
     *
     * @param dto 数据
     * @return
     * @author: ※狗尾巴草
     * @email: "m962464oo@gmail.com"
     * @date: 2021-09-15 22:25:15
     */
    boolean save(VVipDTO dto);

    /**
     * Description:【 修改数据 】
     *
     * @param dto 数据
     * @return
     * @author: ※狗尾巴草
     * @email: "m962464oo@gmail.com"
     * @date: 2021-09-15 22:25:15
     */
    boolean update(VVipDTO dto);

    /**
     * Description:【 通过ID删除数据 】
     *
     * @param id
     * @return
     * @author: ※狗尾巴草
     * @email: "m962464oo@gmail.com"
     * @date: 2021-09-15 22:25:15
     */
    boolean delete(Long id);

    /**
     * Description:【 通过ID数组,批量删除定时任务 】
     *
     * @param ids ID数组
     * @return
     * @author: ※狗尾巴草
     * @email: "m962464oo@gmail.com"
     * @date: 2021-09-15 22:25:15
     */
    boolean deleteBatch(Long[] ids);


    // ==================== 其他 ====================

}

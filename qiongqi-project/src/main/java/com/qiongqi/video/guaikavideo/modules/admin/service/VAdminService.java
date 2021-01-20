package com.qiongqi.video.guaikavideo.modules.admin.service;

import com.qiongqi.video.guaikavideo.config.MybatisPlus.service.BaseService;
import com.qiongqi.video.guaikavideo.modules.admin.dto.VAdminDTO;
import com.qiongqi.video.guaikavideo.modules.admin.entity.VAdminEntity;
import com.qiongqi.video.guaikavideo.utils.page.PageData;

import java.util.List;
import java.util.Map;

/**
 * Description: 【 管理员 Service接口 】
 *
 * @author : ※狗尾巴草
 * @version : 1.0.0
 * @email : "m962464oo@gmail.com"
 * @date : 2021-01-16 14:22:49
 */
public interface VAdminService extends BaseService<VAdminEntity> {

    // ==================== 查询 ====================

    /**
     * Description:【 分页条件查询 】
     *
     * @param queryParams 查询参数
     * @return
     * @author: ※狗尾巴草
     * @email: "m962464oo@gmail.com"
     * @date: 2021-01-16 14:22:49
     */
    PageData<VAdminDTO> page(Map<String, Object> queryParams);

    /**
     * Description:【 条件查询 】
     *
     * @param queryParams 查询参数
     * @return
     * @author: ※狗尾巴草
     * @email: "m962464oo@gmail.com"
     * @date: 2021-01-16 14:22:49
     */
    List<VAdminDTO> list(Map<String, Object> queryParams);

    /**
     * Description:【 通过ID获取单条数据 】
     *
     * @param id
     * @return
     * @author: ※狗尾巴草
     * @email: "m962464oo@gmail.com"
     * @date: 2021-01-16 14:22:49
     */
    VAdminDTO get();

    // ==================== ADD、MODIFY、REMOVE ===================

    /**
     * Description:【 保存到数据库中 】
     *
     * @param dto 数据
     * @return
     * @author: ※狗尾巴草
     * @email: "m962464oo@gmail.com"
     * @date: 2021-01-16 14:22:49
     */
    boolean save(VAdminDTO dto);

    /**
     * Description:【 修改数据 】
     *
     * @param dto 数据
     * @return
     * @author: ※狗尾巴草
     * @email: "m962464oo@gmail.com"
     * @date: 2021-01-16 14:22:49
     */
    boolean update(VAdminDTO dto);

    /**
     * Description:【 通过ID删除数据 】
     *
     * @param id
     * @return
     * @author: ※狗尾巴草
     * @email: "m962464oo@gmail.com"
     * @date: 2021-01-16 14:22:49
     */
    boolean delete(Long id);

    /**
     * Description:【 通过ID数组,批量删除定时任务 】
     *
     * @param ids ID数组
     * @return
     * @author: ※狗尾巴草
     * @email: "m962464oo@gmail.com"
     * @date: 2021-01-16 14:22:49
     */
    boolean deleteBatch(Long[] ids);

    VAdminDTO getByUserName(String username);


    // ==================== 其他 ====================

}

package com.qiongqi.modules.qiniu.service;

import com.qiongqi.config.MybatisPlus.service.BaseService;
import com.qiongqi.modules.qiniu.model.dto.VQiniuDTO;
import com.qiongqi.modules.qiniu.model.entity.VQiniuEntity;
import com.qiongqi.utils.page.PageData;

import java.util.List;
import java.util.Map;

/**
 * Description: 【 七牛云 Service接口 】
 *
 * @author : ※狗尾巴草
 * @version : 1.0.0
 * @email : "m962464oo@gmail.com"
 * @date : 2021-06-29 00:03:34
 */
public interface VQiniuService extends BaseService<VQiniuEntity> {

    // ==================== 查询 ====================

    /**
     * Description:【 分页条件查询 】
     *
     * @param queryParams 查询参数
     * @return
     * @author: ※狗尾巴草
     * @email: "m962464oo@gmail.com"
     * @date: 2021-06-29 00:03:34
     */
    PageData<VQiniuDTO> page(Map<String, Object> queryParams);

    /**
     * Description:【 条件查询 】
     *
     * @param queryParams 查询参数
     * @return
     * @author: ※狗尾巴草
     * @email: "m962464oo@gmail.com"
     * @date: 2021-06-29 00:03:34
     */
    List<VQiniuDTO> list(Map<String, Object> queryParams);

    /**
     * Description:【 通过ID获取单条数据 】
     *
     * @param id
     * @return
     * @author: ※狗尾巴草
     * @email: "m962464oo@gmail.com"
     * @date: 2021-06-29 00:03:34
     */
    VQiniuDTO get(Long id);

    // ==================== ADD、MODIFY、REMOVE ===================

    /**
     * Description:【 保存到数据库中 】
     *
     * @param dto 数据
     * @return
     * @author: ※狗尾巴草
     * @email: "m962464oo@gmail.com"
     * @date: 2021-06-29 00:03:34
     */
    boolean save(VQiniuDTO dto);

    /**
     * Description:【 修改数据 】
     *
     * @param dto 数据
     * @return
     * @author: ※狗尾巴草
     * @email: "m962464oo@gmail.com"
     * @date: 2021-06-29 00:03:34
     */
    boolean update(VQiniuDTO dto);

    /**
     * Description:【 通过ID删除数据 】
     *
     * @param id
     * @return
     * @author: ※狗尾巴草
     * @email: "m962464oo@gmail.com"
     * @date: 2021-06-29 00:03:34
     */
    boolean delete(Long id);

    /**
     * Description:【 通过ID数组,批量删除定时任务 】
     *
     * @param ids ID数组
     * @return
     * @author: ※狗尾巴草
     * @email: "m962464oo@gmail.com"
     * @date: 2021-06-29 00:03:34
     */
    boolean deleteBatch(Long[] ids);

    /**
     * 描述: 【 修改状态:0:禁用;1启用 】
     *
     * @param id
     * @param status
     * @author : ※狗尾巴草
     * @email : "m962464oo@gmail.com"
     * @date : 2021-06-29 00:03:34
     */
    boolean changeStatus(Long id, Integer status);

    VQiniuDTO getByStatus(int status);

    // ==================== 其他 ====================

}

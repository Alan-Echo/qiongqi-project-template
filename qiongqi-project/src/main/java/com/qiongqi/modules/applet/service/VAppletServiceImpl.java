package com.qiongqi.modules.applet.service;

import com.qiongqi.config.MybatisPlus.service.BaseServiceImpl;
import com.qiongqi.modules.applet.mapper.VAppletMapper;
import com.qiongqi.modules.applet.model.dto.VAppletDTO;
import com.qiongqi.modules.applet.model.entity.VAppletEntity;
import com.qiongqi.utils.ConvertUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Description: 【 小程序配置 Service接口实现类 】
 *
 * @author : ※狗尾巴草
 * @version : 1.0.0
 * @email : "m962464oo@gmail.com"
 * @date : 2021-03-22 18:16:06
 */
@Service
public class VAppletServiceImpl extends BaseServiceImpl<VAppletMapper, VAppletEntity> implements VAppletService {

    // ==================== 查询 ====================

    /**
     * Description:【 通过ID获取单条数据 】
     *
     * @param
     * @return
     * @author : ※狗尾巴草
     * @email : "m962464oo@gmail.com"
     * @date : 2021-03-22 18:16:06
     */
    @Override
    public VAppletDTO get() {
        VAppletDTO dto = baseMapper.query();

        return dto;
    }

    // ==================== 增、改、删====================

    /**
     * Description:【 保存到数据库中 】
     *
     * @param dto 数据
     * @return
     * @author : ※狗尾巴草
     * @email : "m962464oo@gmail.com"
     * @date : 2021-03-22 18:16:06
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(VAppletDTO dto) {
        VAppletEntity entity = ConvertUtils.sourceToTarget(dto, VAppletEntity.class);

        return save(entity);
    }

    /**
     * Description:【 修改数据 】
     *
     * @param dto 数据
     * @return
     * @author : ※狗尾巴草
     * @email : "m962464oo@gmail.com"
     * @date : 2021-03-22 18:16:06
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean update(VAppletDTO dto) {
        VAppletEntity entity = ConvertUtils.sourceToTarget(dto, VAppletEntity.class);


        return updateById(entity);
    }

    @Override
    public boolean removeAll() {
        return baseMapper.removeAll();
    }

    // ==================== 其他 ====================



}

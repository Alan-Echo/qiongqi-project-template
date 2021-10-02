package com.qiongqi.prj.template.modules.admin.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qiongqi.prj.template.config.MybatisPlus.service.BaseServiceImpl;
import com.qiongqi.prj.template.modules.admin.dto.VAdminDTO;
import com.qiongqi.prj.template.modules.admin.entity.VAdminEntity;
import com.qiongqi.prj.template.modules.admin.mapper.VAdminMapper;
import com.qiongqi.prj.template.utils.ConvertUtils;
import com.qiongqi.prj.template.utils.page.PageData;
import com.qiongqi.prj.template.utils.page.PageUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Description: 【 管理员 Service接口实现类 】
 *
 * @author : ※狗尾巴草
 * @version : 1.0.0
 * @email : "m962464oo@gmail.com"
 * @date : 2021-01-16 14:22:49
 */
@Service
public class VAdminServiceImpl extends BaseServiceImpl<VAdminMapper, VAdminEntity> implements VAdminService {

    // ==================== 查询 ====================

    /**
     * Description:【 分页条件查询 】
     *
     * @param queryParams 查询参数
     * @return
     * @author : ※狗尾巴草
     * @email : "m962464oo@gmail.com"
     * @date : 2021-01-16 14:22:49
     */
    @Override
    public PageData<VAdminDTO> page(Map<String, Object> queryParams) {
        PageUtils.initPage(queryParams);

        IPage<VAdminEntity> page = baseMapper.selectPage(
                PageUtils.initPage(queryParams),
                getWrapper(queryParams).orderByDesc(VAdminEntity::getCreateTime)
        );
        return getPageData(page, VAdminDTO.class);
    }

    /**
     * Description:【 列表条件查询 】
     *
     * @param queryParams Map参数
     * @return
     * @author : ※狗尾巴草
     * @email : "m962464oo@gmail.com"
     * @date : 2021-01-16 14:22:49
     */
    @Override
    public List<VAdminDTO> list(Map<String, Object> queryParams) {
        List<VAdminEntity> entityList = baseMapper.selectList(getWrapper(queryParams));

        return ConvertUtils.sourceToTarget(entityList, VAdminDTO.class);
    }

    /**
     * Description:【 通过ID获取单条数据 】
     *
     * @param id
     * @return
     * @author : ※狗尾巴草
     * @email : "m962464oo@gmail.com"
     * @date : 2021-01-16 14:22:49
     */
    @Override
    public VAdminDTO get() {
        VAdminDTO dto = baseMapper.get();
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
     * @date : 2021-01-16 14:22:49
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(VAdminDTO dto) {
        VAdminEntity entity = ConvertUtils.sourceToTarget(dto, VAdminEntity.class);

        return save(entity);
    }

    /**
     * Description:【 修改数据 】
     *
     * @param dto 数据
     * @return
     * @author : ※狗尾巴草
     * @email : "m962464oo@gmail.com"
     * @date : 2021-01-16 14:22:49
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean update(VAdminDTO dto) {
        VAdminEntity entity = ConvertUtils.sourceToTarget(dto, VAdminEntity.class);


        return updateById(entity);
    }

    /**
     * Description:【 通过ID删除数据 】
     *
     * @param id 部门ID
     * @return
     * @author : ※狗尾巴草
     * @email : "m962464oo@gmail.com"
     * @date : 2021-01-16 14:22:49
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(Long id) {


        //删除
        return removeById(id);
    }

    /**
     * Description:【 通过ID数组,批量删除定时任务 】
     *
     * @param ids ID数组
     * @return
     * @author : ※狗尾巴草
     * @email : "m962464oo@gmail.com"
     * @date : 2021-01-16 14:22:49
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteBatch(Long[] ids) {


        //删除数据
        return removeByIds(Arrays.asList(ids));
    }

    @Override
    public VAdminDTO getByUserName(String username) {
        return baseMapper.getByUserName( username);
    }


    // ==================== 其他 ====================


    /**
     * Description: 【 初始化查询条件 】
     *
     * @param queryParams
     * @author : ※狗尾巴草
     * @email : "m962464oo@gmail.com"
     * @date : 2021-01-16 14:22:49
     */
    private LambdaQueryWrapper<VAdminEntity> getWrapper(Map<String, Object> queryParams) {
        LambdaQueryWrapper<VAdminEntity> wrapper = new LambdaQueryWrapper<>();

        // 关键字
        //String searchWord = MapUtils.getString(queryParams, "searchWord", "");
        //wrapper.and(StringUtils.isNotBlank(searchWord.trim()), s -> s.like(VAdminEntity::getName, searchWord.trim()));

        // 状态
//        Integer searchStatus = MapUtils.getInteger(queryParams, "status", null);
//        wrapper.eq(searchStatus != null, VAdminEntity::getStatus, searchStatus);

        // others

        return wrapper;
    }


}

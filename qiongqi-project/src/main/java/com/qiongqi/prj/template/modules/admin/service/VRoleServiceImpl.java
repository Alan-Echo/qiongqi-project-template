package com.qiongqi.prj.template.modules.admin.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qiongqi.prj.template.config.MybatisPlus.service.BaseServiceImpl;
import com.qiongqi.prj.template.modules.admin.dto.VRoleDTO;
import com.qiongqi.prj.template.modules.admin.entity.VRoleEntity;
import com.qiongqi.prj.template.modules.admin.mapper.VRoleMapper;
import com.qiongqi.prj.template.utils.ConvertUtils;
import com.qiongqi.prj.template.utils.page.PageData;
import com.qiongqi.prj.template.utils.page.PageUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Description: 【 角色表 Service接口实现类 】
 *
 * @author : ※狗尾巴草
 * @version : 1.0.0
 * @email : "m962464oo@gmail.com"
 * @date : 2021-01-16 14:58:33
 */
@Service
public class VRoleServiceImpl extends BaseServiceImpl<VRoleMapper, VRoleEntity> implements VRoleService {

    // ==================== 查询 ====================

    /**
     * Description:【 分页条件查询 】
     *
     * @param queryParams 查询参数
     * @return
     * @author : ※狗尾巴草
     * @email : "m962464oo@gmail.com"
     * @date : 2021-01-16 14:58:33
     */
    @Override
    public PageData<VRoleDTO> page(Map<String, Object> queryParams) {
        PageUtils.initPage(queryParams);

        IPage<VRoleEntity> page = baseMapper.selectPage(
                PageUtils.initPage(queryParams),
                getWrapper(queryParams).orderByDesc(VRoleEntity::getCreateTime)
        );
        return getPageData(page, VRoleDTO.class);
    }

    /**
     * Description:【 列表条件查询 】
     *
     * @param queryParams Map参数
     * @return
     * @author : ※狗尾巴草
     * @email : "m962464oo@gmail.com"
     * @date : 2021-01-16 14:58:33
     */
    @Override
    public List<VRoleDTO> list(Map<String, Object> queryParams) {
        List<VRoleEntity> entityList = baseMapper.selectList(getWrapper(queryParams));

        return ConvertUtils.sourceToTarget(entityList, VRoleDTO.class);
    }

    /**
     * Description:【 通过ID获取单条数据 】
     *
     * @param id
     * @return
     * @author : ※狗尾巴草
     * @email : "m962464oo@gmail.com"
     * @date : 2021-01-16 14:58:33
     */
    @Override
    public VRoleDTO get(Long id) {
        VRoleDTO dto = baseMapper.queryOneById(id);

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
     * @date : 2021-01-16 14:58:33
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(VRoleDTO dto) {
        VRoleEntity entity = ConvertUtils.sourceToTarget(dto, VRoleEntity.class);

        return save(entity);
    }

    /**
     * Description:【 修改数据 】
     *
     * @param dto 数据
     * @return
     * @author : ※狗尾巴草
     * @email : "m962464oo@gmail.com"
     * @date : 2021-01-16 14:58:33
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean update(VRoleDTO dto) {
        VRoleEntity entity = ConvertUtils.sourceToTarget(dto, VRoleEntity.class);


        return updateById(entity);
    }

    /**
     * Description:【 通过ID删除数据 】
     *
     * @param id 部门ID
     * @return
     * @author : ※狗尾巴草
     * @email : "m962464oo@gmail.com"
     * @date : 2021-01-16 14:58:33
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
     * @date : 2021-01-16 14:58:33
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteBatch(Long[] ids) {


        //删除数据
        return removeByIds(Arrays.asList(ids));
    }


    // ==================== 其他 ====================


    /**
     * Description: 【 初始化查询条件 】
     *
     * @param queryParams
     * @author : ※狗尾巴草
     * @email : "m962464oo@gmail.com"
     * @date : 2021-01-16 14:58:33
     */
    private LambdaQueryWrapper<VRoleEntity> getWrapper(Map<String, Object> queryParams) {
        LambdaQueryWrapper<VRoleEntity> wrapper = new LambdaQueryWrapper<>();

        // 关键字
        //String searchWord = MapUtils.getString(queryParams, "searchWord", "");
        //wrapper.and(StringUtils.isNotBlank(searchWord.trim()), s -> s.like(VRoleEntity::getName, searchWord.trim()));

        // 状态
//        Integer searchStatus = MapUtils.getInteger(queryParams, "status", null);
//        wrapper.eq(searchStatus != null, VRoleEntity::getStatus, searchStatus);

        // others

        return wrapper;
    }


}

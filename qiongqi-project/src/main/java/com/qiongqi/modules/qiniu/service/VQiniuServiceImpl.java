package com.qiongqi.modules.qiniu.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qiongqi.config.MybatisPlus.service.BaseServiceImpl;
import com.qiongqi.modules.qiniu.mapper.VQiniuMapper;
import com.qiongqi.modules.qiniu.model.dto.VQiniuDTO;
import com.qiongqi.modules.qiniu.model.entity.VQiniuEntity;
import com.qiongqi.utils.ConvertUtils;
import com.qiongqi.utils.page.PageData;
import com.qiongqi.utils.page.PageUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Description: 【 七牛云 Service接口实现类 】
 *
 * @author : ※狗尾巴草
 * @version : 1.0.0
 * @email : "m962464oo@gmail.com"
 * @date : 2021-06-29 00:03:34
 */
@Service
public class VQiniuServiceImpl extends BaseServiceImpl<VQiniuMapper, VQiniuEntity> implements VQiniuService {

    // ==================== 查询 ====================

    /**
     * Description:【 分页条件查询 】
     *
     * @param queryParams 查询参数
     * @return
     * @author : ※狗尾巴草
     * @email : "m962464oo@gmail.com"
     * @date : 2021-06-29 00:03:34
     */
    @Override
    public PageData<VQiniuDTO> page(Map<String, Object> queryParams) {
        PageUtils.initPage(queryParams);

        IPage<VQiniuEntity> page = baseMapper.selectPage(
                PageUtils.initPage(queryParams),
                getWrapper(queryParams).orderByDesc(VQiniuEntity::getCreateTime)
        );
        return getPageData(page, VQiniuDTO.class);
    }

    /**
     * Description:【 列表条件查询 】
     *
     * @param queryParams Map参数
     * @return
     * @author : ※狗尾巴草
     * @email : "m962464oo@gmail.com"
     * @date : 2021-06-29 00:03:34
     */
    @Override
    public List<VQiniuDTO> list(Map<String, Object> queryParams) {
        List<VQiniuEntity> entityList = baseMapper.selectList(getWrapper(queryParams));

        return ConvertUtils.sourceToTarget(entityList, VQiniuDTO.class);
    }

    /**
     * Description:【 通过ID获取单条数据 】
     *
     * @param id
     * @return
     * @author : ※狗尾巴草
     * @email : "m962464oo@gmail.com"
     * @date : 2021-06-29 00:03:34
     */
    @Override
    public VQiniuDTO get(Long id) {
        VQiniuDTO dto = baseMapper.queryOneById(id);

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
     * @date : 2021-06-29 00:03:34
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(VQiniuDTO dto) {
        VQiniuEntity entity = ConvertUtils.sourceToTarget(dto, VQiniuEntity.class);

        return save(entity);
    }

    /**
     * Description:【 修改数据 】
     *
     * @param dto 数据
     * @return
     * @author : ※狗尾巴草
     * @email : "m962464oo@gmail.com"
     * @date : 2021-06-29 00:03:34
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean update(VQiniuDTO dto) {
        VQiniuEntity entity = ConvertUtils.sourceToTarget(dto, VQiniuEntity.class);


        return updateById(entity);
    }

    /**
     * Description:【 通过ID删除数据 】
     *
     * @param id 部门ID
     * @return
     * @author : ※狗尾巴草
     * @email : "m962464oo@gmail.com"
     * @date : 2021-06-29 00:03:34
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
     * @date : 2021-06-29 00:03:34
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteBatch(Long[] ids) {


        //删除数据
        return removeByIds(Arrays.asList(ids));
    }

    /**
     * 描述: 【 修改状态:0:禁用;1启用 】
     *
     * @param id
     * @param status
     * @author : ※狗尾巴草
     * @email : "m962464oo@gmail.com"
     * @date : 2021-06-29 00:03:34
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean changeStatus(Long id, Integer status) {
        VQiniuEntity entity = baseMapper.selectById(id);
        entity.setStatus(status);
        return updateById(entity);
    }

    @Override
    public VQiniuDTO getByStatus(int status) {
        return baseMapper.getByStatus(status);
    }

    // ==================== 其他 ====================


    /**
     * Description: 【 初始化查询条件 】
     *
     * @param queryParams
     * @author : ※狗尾巴草
     * @email : "m962464oo@gmail.com"
     * @date : 2021-06-29 00:03:34
     */
    private LambdaQueryWrapper<VQiniuEntity> getWrapper(Map<String, Object> queryParams) {
        LambdaQueryWrapper<VQiniuEntity> wrapper = new LambdaQueryWrapper<>();

        //时间查询
        JSONObject dateTime = JSON.parseObject(MapUtils.getString(queryParams, "dateTime"));
        if (dateTime != null && dateTime.size() > 0) {
            String startTime = dateTime.getString("startTime");
            String endTime = dateTime.getString("endTime");
            wrapper.ge(StringUtils.isNotBlank(startTime), VQiniuEntity::getCreateTime, startTime);
            wrapper.le(StringUtils.isNotBlank(endTime), VQiniuEntity::getCreateTime, endTime);
        }

        // 空间名
        String bucketName = MapUtils.getString(queryParams, "bucketName", "");
        wrapper.and(StringUtils.isNotBlank(bucketName.trim()), s -> s.like(VQiniuEntity::getBucketName, bucketName.trim()));

        // 地区搜索
        String zone = MapUtils.getString(queryParams, "zone", "");
        wrapper.and(StringUtils.isNotBlank(zone.trim()), s -> s.eq(VQiniuEntity::getZone, zone.trim()));

        // 状态
        Integer searchStatus = MapUtils.getInteger(queryParams, "status", null);
        wrapper.eq(searchStatus != null, VQiniuEntity::getStatus, searchStatus);

        // others

        return wrapper;
    }


}

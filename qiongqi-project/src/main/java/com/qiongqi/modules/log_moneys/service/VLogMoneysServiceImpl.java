package com.qiongqi.modules.log_moneys.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qiongqi.config.MybatisPlus.service.BaseServiceImpl;
import com.qiongqi.modules.log_moneys.mapper.VLogMoneysMapper;
import com.qiongqi.modules.log_moneys.model.dto.VLogMoneysDTO;
import com.qiongqi.modules.log_moneys.model.entity.VLogMoneysEntity;
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
 * Description: 【 资金流水表 Service接口实现类 】
 *
 * @author : ※狗尾巴草
 * @version : 1.0.0
 * @email : "m962464oo@gmail.com"
 * @date : 2021-10-12 01:30:01
 */
@Service
public class VLogMoneysServiceImpl extends BaseServiceImpl<VLogMoneysMapper, VLogMoneysEntity> implements VLogMoneysService {

    // ==================== 查询 ====================

    /**
     * Description:【 分页条件查询 】
     *
     * @param queryParams 查询参数
     * @return
     * @author : ※狗尾巴草
     * @email : "m962464oo@gmail.com"
     * @date : 2021-10-12 01:30:01
     */
    @Override
    public PageData<VLogMoneysDTO> page(Map<String, Object> queryParams) {
        PageUtils.initPage(queryParams);

        IPage<VLogMoneysEntity> page = baseMapper.selectPage(
                PageUtils.initPage(queryParams),
                getWrapper(queryParams).orderByDesc(VLogMoneysEntity::getCreateTime)
        );
        return getPageData(page, VLogMoneysDTO.class);
    }

    /**
     * Description:【 列表条件查询 】
     *
     * @param queryParams Map参数
     * @return
     * @author : ※狗尾巴草
     * @email : "m962464oo@gmail.com"
     * @date : 2021-10-12 01:30:01
     */
    @Override
    public List<VLogMoneysDTO> list(Map<String, Object> queryParams) {
        List<VLogMoneysEntity> entityList = baseMapper.selectList(getWrapper(queryParams));

        return ConvertUtils.sourceToTarget(entityList, VLogMoneysDTO.class);
    }

    /**
     * Description:【 通过ID获取单条数据 】
     *
     * @param id
     * @return
     * @author : ※狗尾巴草
     * @email : "m962464oo@gmail.com"
     * @date : 2021-10-12 01:30:01
     */
    @Override
    public VLogMoneysDTO get(Long id) {
        VLogMoneysDTO dto = baseMapper.queryOneById(id);

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
     * @date : 2021-10-12 01:30:01
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(VLogMoneysDTO dto) {
        VLogMoneysEntity entity = ConvertUtils.sourceToTarget(dto, VLogMoneysEntity.class);

        return save(entity);
    }

    /**
     * Description:【 修改数据 】
     *
     * @param dto 数据
     * @return
     * @author : ※狗尾巴草
     * @email : "m962464oo@gmail.com"
     * @date : 2021-10-12 01:30:01
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean update(VLogMoneysDTO dto) {
        VLogMoneysEntity entity = ConvertUtils.sourceToTarget(dto, VLogMoneysEntity.class);


        return updateById(entity);
    }

    /**
     * Description:【 通过ID删除数据 】
     *
     * @param id 部门ID
     * @return
     * @author : ※狗尾巴草
     * @email : "m962464oo@gmail.com"
     * @date : 2021-10-12 01:30:01
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
     * @date : 2021-10-12 01:30:01
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
     * @date : 2021-10-12 01:30:01
     */
    private LambdaQueryWrapper<VLogMoneysEntity> getWrapper(Map<String, Object> queryParams) {
        LambdaQueryWrapper<VLogMoneysEntity> wrapper = new LambdaQueryWrapper<>();

        //时间查询
        JSONObject dateTime = JSON.parseObject(MapUtils.getString(queryParams, "dateTime"));
        if (dateTime != null && dateTime.size() > 0) {
            String startTime = dateTime.getString("startTime");
            String endTime = dateTime.getString("endTime");
            wrapper.ge(StringUtils.isNotBlank(startTime), VLogMoneysEntity::getCreateTime, startTime);
            wrapper.le(StringUtils.isNotBlank(endTime), VLogMoneysEntity::getCreateTime, endTime);
        }

        // 关键字
        //String searchWord = MapUtils.getString(queryParams, "searchWord", "");
        //wrapper.and(StringUtils.isNotBlank(searchWord.trim()), s -> s.like(VLogMoneysEntity::getName, searchWord.trim()));

        // 状态
        //Integer searchStatus = MapUtils.getInteger(queryParams, "status", null);
        //wrapper.eq(searchStatus != null, VLogMoneysEntity::getStatus, searchStatus);

        // others

        return wrapper;
    }


}

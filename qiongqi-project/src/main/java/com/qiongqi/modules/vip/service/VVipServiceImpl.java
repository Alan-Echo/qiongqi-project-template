package com.qiongqi.modules.vip.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qiongqi.config.MybatisPlus.service.BaseServiceImpl;
import com.qiongqi.modules.vip.mapper.VVipMapper;
import com.qiongqi.modules.vip.model.dto.VVipDTO;
import com.qiongqi.modules.vip.model.entity.VVipEntity;
import com.qiongqi.modules.vip.model.vo.VVipVO;
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
 * Description: 【 vip会员 Service接口实现类 】
 *
 * @author : ※狗尾巴草
 * @version : 1.0.0
 * @email : "m962464oo@gmail.com"
 * @date : 2021-09-15 22:25:15
 */
@Service
public class VVipServiceImpl extends BaseServiceImpl<VVipMapper, VVipEntity> implements VVipService {

    // ==================== 查询 ====================

    /**
     * Description:【 分页条件查询 】
     *
     * @param queryParams 查询参数
     * @return
     * @author : ※狗尾巴草
     * @email : "m962464oo@gmail.com"
     * @date : 2021-09-15 22:25:15
     */
    @Override
    public PageData<VVipDTO> page(Map<String, Object> queryParams) {
        PageUtils.initPage(queryParams);

        IPage<VVipEntity> page = baseMapper.selectPage(
                PageUtils.initPage(queryParams),
                getWrapper(queryParams).orderByDesc(VVipEntity::getCreateTime)
        );
        return getPageData(page, VVipDTO.class);
    }

    /**
     * Description:【 列表条件查询 】
     *
     * @param queryParams Map参数
     * @return
     * @author : ※狗尾巴草
     * @email : "m962464oo@gmail.com"
     * @date : 2021-09-15 22:25:15
     */
    @Override
    public List<VVipVO> list(Map<String, Object> queryParams) {
        List<VVipEntity> entityList = baseMapper.selectList(getWrapper(queryParams));

        return ConvertUtils.sourceToTarget(entityList, VVipVO.class);
    }

    /**
     * Description:【 通过ID获取单条数据 】
     *
     * @param id
     * @return
     * @author : ※狗尾巴草
     * @email : "m962464oo@gmail.com"
     * @date : 2021-09-15 22:25:15
     */
    @Override
    public VVipDTO get(Long id) {
        VVipDTO dto = baseMapper.queryOneById(id);

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
     * @date : 2021-09-15 22:25:15
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(VVipDTO dto) {
        VVipEntity entity = ConvertUtils.sourceToTarget(dto, VVipEntity.class);

        return save(entity);
    }

    /**
     * Description:【 修改数据 】
     *
     * @param dto 数据
     * @return
     * @author : ※狗尾巴草
     * @email : "m962464oo@gmail.com"
     * @date : 2021-09-15 22:25:15
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean update(VVipDTO dto) {
        VVipEntity entity = ConvertUtils.sourceToTarget(dto, VVipEntity.class);


        return updateById(entity);
    }

    /**
     * Description:【 通过ID删除数据 】
     *
     * @param id 部门ID
     * @return
     * @author : ※狗尾巴草
     * @email : "m962464oo@gmail.com"
     * @date : 2021-09-15 22:25:15
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
     * @date : 2021-09-15 22:25:15
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
     * @date : 2021-09-15 22:25:15
     */
    private LambdaQueryWrapper<VVipEntity> getWrapper(Map<String, Object> queryParams) {
        LambdaQueryWrapper<VVipEntity> wrapper = new LambdaQueryWrapper<>();

        //时间查询
        JSONObject dateTime = JSON.parseObject(MapUtils.getString(queryParams, "dateTime"));
        if (dateTime != null && dateTime.size() > 0) {
            String startTime = dateTime.getString("startTime");
            String endTime = dateTime.getString("endTime");
            wrapper.ge(StringUtils.isNotBlank(startTime), VVipEntity::getCreateTime, startTime);
            wrapper.le(StringUtils.isNotBlank(endTime), VVipEntity::getCreateTime, endTime);
        }

        // 关键字
        //String searchWord = MapUtils.getString(queryParams, "searchWord", "");
        //wrapper.and(StringUtils.isNotBlank(searchWord.trim()), s -> s.like(VVipEntity::getName, searchWord.trim()));

        // 状态
        //Integer searchStatus = MapUtils.getInteger(queryParams, "status", null);
        //wrapper.eq(searchStatus != null, VVipEntity::getStatus, searchStatus);

        // others

        return wrapper;
    }


}

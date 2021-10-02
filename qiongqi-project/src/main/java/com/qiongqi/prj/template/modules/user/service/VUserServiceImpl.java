package com.qiongqi.prj.template.modules.user.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qiongqi.prj.template.config.MybatisPlus.service.BaseServiceImpl;
import com.qiongqi.prj.template.modules.user.dto.VUserDTO;
import com.qiongqi.prj.template.modules.user.entity.VUserEntity;
import com.qiongqi.prj.template.modules.user.mapper.VUserMapper;
import com.qiongqi.prj.template.utils.ConvertUtils;
import com.qiongqi.prj.template.utils.page.PageData;
import com.qiongqi.prj.template.utils.page.PageUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Description: 【 用户表 Service接口实现类 】
 *
 * @author : ※狗尾巴草
 * @version : 1.0.0
 * @email : "m962464oo@gmail.com"
 * @date : 2021-01-16 14:22:50
 */
@Service
public class VUserServiceImpl extends BaseServiceImpl<VUserMapper, VUserEntity> implements VUserService {

    // ==================== 查询 ====================

    /**
     * Description:【 分页条件查询 】
     *
     * @param queryParams 查询参数
     * @return
     * @author : ※狗尾巴草
     * @email : "m962464oo@gmail.com"
     * @date : 2021-01-16 14:22:50
     */
    @Override
    public PageData<VUserDTO> page(Map<String, Object> queryParams) {
        PageUtils.initPage(queryParams);

        IPage<VUserEntity> page = baseMapper.selectPage(
                PageUtils.initPage(queryParams),
                getWrapper(queryParams).orderByDesc(VUserEntity::getCreateTime)
        );
        return getPageData(page, VUserDTO.class);
    }

    /**
     * Description:【 列表条件查询 】
     *
     * @param queryParams Map参数
     * @return
     * @author : ※狗尾巴草
     * @email : "m962464oo@gmail.com"
     * @date : 2021-01-16 14:22:50
     */
    @Override
    public List<VUserDTO> list(Map<String, Object> queryParams) {
        List<VUserEntity> entityList = baseMapper.selectList(getWrapper(queryParams));

        return ConvertUtils.sourceToTarget(entityList, VUserDTO.class);
    }

    /**
     * Description:【 通过ID获取单条数据 】
     *
     * @param id
     * @return
     * @author : ※狗尾巴草
     * @email : "m962464oo@gmail.com"
     * @date : 2021-01-16 14:22:50
     */
    @Override
    public VUserDTO get(Long id) {
        VUserDTO dto = baseMapper.queryOneById(id);

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
     * @date : 2021-01-16 14:22:50
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public VUserEntity save(VUserDTO dto) {
        VUserEntity entity = ConvertUtils.sourceToTarget(dto, VUserEntity.class);
        save(entity);
        return entity;
    }

    /**
     * Description:【 修改数据 】
     *
     * @param dto 数据
     * @return
     * @author : ※狗尾巴草
     * @email : "m962464oo@gmail.com"
     * @date : 2021-01-16 14:22:50
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean update(VUserDTO dto) {
        VUserEntity entity = ConvertUtils.sourceToTarget(dto, VUserEntity.class);


        return updateById(entity);
    }

    /**
     * Description:【 通过ID删除数据 】
     *
     * @param id 部门ID
     * @return
     * @author : ※狗尾巴草
     * @email : "m962464oo@gmail.com"
     * @date : 2021-01-16 14:22:50
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
     * @date : 2021-01-16 14:22:50
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteBatch(Long[] ids) {


        //删除数据
        return removeByIds(Arrays.asList(ids));
    }

    @Override
    public VUserDTO getByWxIdAndRobotId(String wxId,Long robotId) {
        return baseMapper.getByWxIdAndRobotId(wxId,robotId);
    }

    @Override
    public List<VUserDTO> getByWxId(String wxId) {
        return baseMapper.getByWxId(wxId);
    }

    @Override
    public VUserDTO getByOpenId(String openId) {
        return baseMapper.getByOpenId(openId);
    }

    @Override
    public List<VUserDTO> getByUserName(String userName) {
        return baseMapper.getByUserName(userName);
    }


    // ==================== 其他 ====================


    /**
     * Description: 【 初始化查询条件 】
     *
     * @param queryParams
     * @author : ※狗尾巴草
     * @email : "m962464oo@gmail.com"
     * @date : 2021-01-16 14:22:50
     */
    private LambdaQueryWrapper<VUserEntity> getWrapper(Map<String, Object> queryParams) {
        LambdaQueryWrapper<VUserEntity> wrapper = new LambdaQueryWrapper<>();

        //时间查询
        JSONObject dateTime = JSON.parseObject(MapUtils.getString(queryParams, "dateTime"));
        if (dateTime!=null&&dateTime.size()>0){
            String startTime = dateTime.getString("startTime");
            String endTime = dateTime.getString("endTime");
            wrapper.ge(StringUtils.isNotBlank(startTime), VUserEntity::getCreateTime,startTime);
            wrapper.le(StringUtils.isNotBlank(endTime),VUserEntity::getCreateTime,endTime);
        }
        // 用户名查询
        String userName = MapUtils.getString(queryParams, "userName", "");
        wrapper.and(StringUtils.isNotBlank(userName.trim()),s->s.like(VUserEntity::getUserName,userName.trim()));

        // 所属群 查询
        String groupName = MapUtils.getString(queryParams, "groupName", "");
        wrapper.and(StringUtils.isNotBlank(groupName.trim()),s->s.like(VUserEntity::getGroupName,groupName.trim()));

        // 所属群wxid 查询
        String groupWxId = MapUtils.getString(queryParams, "groupWxId", "");
        wrapper.and(StringUtils.isNotBlank(groupWxId.trim()),s->s.like(VUserEntity::getGroupWxId,groupWxId.trim()));


        //微信id 查询
        String wxId = MapUtils.getString(queryParams, "wxId", "");
        wrapper.eq(StringUtils.isNotBlank(wxId.trim()),VUserEntity::getWxId,wxId);

        //机器人Id
        Long robotId = MapUtils.getLong(queryParams, "robotId", null);
        wrapper.eq(robotId!=null,VUserEntity::getRobotId,robotId);

 // others

        return wrapper;
    }


}

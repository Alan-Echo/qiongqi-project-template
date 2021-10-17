package com.qiongqi.controller.rear.applet;

import com.qiongqi.config.MybatisPlus.group.DefaultGroup;
import com.qiongqi.config.MybatisPlus.group.UpdateGroup;
import com.qiongqi.modules.applet.model.dto.VAppletDTO;
import com.qiongqi.modules.applet.service.VAppletService;
import com.qiongqi.utils.ValidatorUtils;
import com.qiongqi.controller.base.BaseController;
import com.qiongqi.utils.response.data.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Description: 【 小程序配置 后台Controller控制器 】
 *
 * @author : ※狗尾巴草
 * @version : 1.0.0
 * @email : "m962464oo@gmail.com"
 * @date : 2021-03-22 18:16:06
 */
@RestController
@RequestMapping("/admin/applet/")
public class RearVAppletController extends BaseController {

    @Autowired
    private VAppletService vAppletService;

    // ==================== 查询 ====================

    /**
     * Description: 【 通过ID查询单一数据 】
     *
     * @author : ※狗尾巴草
     * @email : "m962464oo@gmail.com"
     * @date : 2021-03-22 18:16:06
     */
    @GetMapping("get")
    public ResponseData<VAppletDTO> get() {
        VAppletDTO data = vAppletService.get();

        return new ResponseData<VAppletDTO>().success(data);
    }

    // ==================== 增、改、删 ====================

    /**
     * Description: 【 新增保存 】
     *
     * @param dto
     * @author : ※狗尾巴草
     * @email : "m962464oo@gmail.com"
     * @date : 2021-03-22 18:16:06
     */
    @PostMapping("save")
    public ResponseData save(@RequestBody VAppletDTO dto)  {

        //已存在记录-就进行更新操作
        if(dto.getId()!=null){
            vAppletService.update(dto);
        }else {
            //不存在记录-就进行保存新增记录操作
            //检查数据库中的记录条数-
            int count = vAppletService.count();
            if(count>0){
                //如果记录数大于0,证明数据库此时已有数据,就删除原来的,保存新的数据
                vAppletService.removeAll(); //删除所有旧的数据
            }
            vAppletService.save(dto); //保存当前新数据
        }
        return new ResponseData().success();
    }

    /**
     * Description: 【 修改 】
     *
     * @param dto
     * @author : ※狗尾巴草
     * @email : "m962464oo@gmail.com"
     * @date : 2021-03-22 18:16:06
     */
    @PutMapping("update")
    public ResponseData update(@RequestBody VAppletDTO dto) throws Exception {
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        vAppletService.update(dto);

        return new ResponseData().success();
    }

    // ==================== 其他 ====================

}

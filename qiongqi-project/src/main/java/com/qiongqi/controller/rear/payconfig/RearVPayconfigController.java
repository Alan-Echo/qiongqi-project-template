package com.qiongqi.controller.rear.payconfig;

import com.qiongqi.config.MybatisPlus.group.DefaultGroup;
import com.qiongqi.config.MybatisPlus.group.UpdateGroup;
import com.qiongqi.modules.payconfig.model.dto.VPayconfigDTO;
import com.qiongqi.modules.payconfig.service.VPayconfigService;
import com.qiongqi.utils.ValidatorUtils;
import com.qiongqi.utils.controller.BaseController;
import com.qiongqi.utils.response.data.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Description: 【 支付相关配置 后台Controller控制器 】
 *
 * @author : ※狗尾巴草
 * @version : 1.0.0
 * @email : "m962464oo@gmail.com"
 * @date : 2021-10-09 23:57:31
 */
@RestController
@RequestMapping("/admin/vPayconfig/")
public class RearVPayconfigController extends BaseController {

    @Autowired
    private VPayconfigService vPayconfigService;

    // ==================== 查询 ====================

//    /**
//     * Description: 【 分页条件查询 】
//     *
//     * @param queryParams
//     * @author : ※狗尾巴草
//     * @email : "m962464oo@gmail.com"
//     * @date : 2021-10-09 23:57:31
//     */
//    @GetMapping("page")
//    public ResponseData<PageData<VPayconfigDTO>> pageGet(@RequestParam Map<String, Object> queryParams) {
//        PageData<VPayconfigDTO> page = vPayconfigService.page(queryParams);
//
//        return new ResponseData<PageData<VPayconfigDTO>>().success(page);
//    }

    /**
     * Description:【 列表条件查询 】
     *
     * @param queryParams
     * @return
     * @author : ※狗尾巴草
     * @email : "m962464oo@gmail.com"
     * @date : 2021-10-09 23:57:31
     */
    @GetMapping("get")
    public ResponseData<VPayconfigDTO> listGet(@RequestParam Map<String, Object> queryParams) {
        List<VPayconfigDTO> data = vPayconfigService.list(queryParams);
        return new ResponseData<VPayconfigDTO>().success(data.size()>0?data.get(0):null);
    }
//
//    /**
//     * Description: 【 通过ID查询单一数据 】
//     *
//     * @param id
//     * @author : ※狗尾巴草
//     * @email : "m962464oo@gmail.com"
//     * @date : 2021-10-09 23:57:31
//     */
//    @GetMapping("{id}")
//    public ResponseData<VPayconfigDTO> get(@PathVariable("id") Long id) {
//        VPayconfigDTO data = vPayconfigService.get(id);
//
//        return new ResponseData<VPayconfigDTO>().success(data);
//    }

    // ==================== 增加 ====================
//
//    /**
//     * Description: 【 新增保存 】
//     *
//     * @param dto
//     * @author : ※狗尾巴草
//     * @email : "m962464oo@gmail.com"
//     * @date : 2021-10-09 23:57:31
//     */
//    @PostMapping("save")
//    public ResponseData save(@RequestBody VPayconfigDTO dto) throws Exception {
//        //效验数据
//        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);
//
//        vPayconfigService.save(dto);
//
//        return new ResponseData();
//    }

    // =================== 修改 ============================

    /**
     * Description: 【 修改/更新 】
     *
     * @param dto
     * @author : ※狗尾巴草
     * @email : "m962464oo@gmail.com"
     * @date : 2021-10-09 23:57:31
     */
    @PostMapping("update")
    public ResponseData update(@RequestBody VPayconfigDTO dto) throws Exception {
        //效验数据
        if(dto.getId()!=null){
            ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);
            vPayconfigService.update(dto);
        }else {
            vPayconfigService.save(dto);
        }
        return new ResponseData();
    }


    // ============================= 删除 ===============================
//
//    /**
//     * Description:【 通过ID删除单条数据 】
//     *
//     * @param id
//     * @return
//     * @author : ※狗尾巴草
//     * @email : "m962464oo@gmail.com"
//     * @date : 2021-10-09 23:57:31
//     */
//    @DeleteMapping("{id}")
//    public ResponseData delete(@PathVariable("id") Long id) {
//        //效验数据
//        AssertUtil.isNotNull(id, "参数不能为空");
//
//        vPayconfigService.delete(id);
//
//        return new ResponseData();
//    }
//
//    /**
//     * Description: 【 批量删除 】
//     *
//     * @param ids
//     * @author : ※狗尾巴草
//     * @email : "m962464oo@gmail.com"
//     * @date : 2021-10-09 23:57:31
//     */
//    @DeleteMapping("delete")
//    public ResponseData delete(@RequestBody Long[] ids) {
//        //效验数据
//        // AssertUtil.isNotNull(id, "参数不能为空");
//
//        vPayconfigService.deleteBatch(ids);
//
//        return new ResponseData();
//    }

    // ==================== 导出 ====================

    //   /**
    //    * Description: 【 导出为Excel 】
    //    *
    //    * @author : ※狗尾巴草
    //    * @email : "m962464oo@gmail.com"
    //    * @date : 2021-10-09 23:57:31
    //    *
    //    * @param queryParams 查询条件
    //    */
    //  @GetMapping("export")
    //  public void export(@RequestParam Map<String, Object> queryParams, HttpServletResponse response) throws Exception {
    //      List<VPayconfigDTO> list = vPayconfigService.list(queryParams);
//
    //       ExcelUtils.exportExcelToTarget(response, null, list, VPayconfigExcel.class);
    //   }

    // ======================== 其他 ==============================

}

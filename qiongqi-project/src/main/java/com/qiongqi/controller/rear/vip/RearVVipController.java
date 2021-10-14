package com.qiongqi.controller.rear.vip;

import com.qiongqi.config.MybatisPlus.group.AddGroup;
import com.qiongqi.config.MybatisPlus.group.DefaultGroup;
import com.qiongqi.config.MybatisPlus.group.UpdateGroup;
import com.qiongqi.modules.vip.model.dto.VVipDTO;
import com.qiongqi.modules.vip.service.VVipService;
import com.qiongqi.utils.AssertUtil;
import com.qiongqi.utils.ValidatorUtils;
import com.qiongqi.utils.controller.BaseController;
import com.qiongqi.utils.page.PageData;
import com.qiongqi.utils.response.data.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Description: 【 vip会员 后台Controller控制器 】
 *
 * @author : ※狗尾巴草
 * @version : 1.0.0
 * @email : "m962464oo@gmail.com"
 * @date : 2021-09-15 22:25:15
 */
@RestController
@RequestMapping("/admin/vVip/")
public class RearVVipController extends BaseController {

    @Autowired
    private VVipService vVipService;

    // ==================== 查询 ====================

    /**
     * Description: 【 分页条件查询 】
     *
     * @param queryParams
     * @author : ※狗尾巴草
     * @email : "m962464oo@gmail.com"
     * @date : 2021-09-15 22:25:15
     */
    @GetMapping("page")
    public ResponseData<PageData<VVipDTO>> pageGet(@RequestParam Map<String, Object> queryParams) {
        PageData<VVipDTO> page = vVipService.page(queryParams);

        return new ResponseData<PageData<VVipDTO>>().success(page);
    }
//
//    /**
//     * Description:【 列表条件查询 】
//     *
//     * @param queryParams
//     * @return
//     * @author : ※狗尾巴草
//     * @email : "m962464oo@gmail.com"
//     * @date : 2021-09-15 22:25:15
//     */
//    @GetMapping("list")
//    public ResponseData<List<VVipDTO>> listGet(@RequestParam Map<String, Object> queryParams) {
//        List<VVipDTO> data = vVipService.list(queryParams);
//
//        return new ResponseData<List<VVipDTO>>().success(data);
//    }

    /**
     * Description: 【 通过ID查询单一数据 】
     *
     * @param id
     * @author : ※狗尾巴草
     * @email : "m962464oo@gmail.com"
     * @date : 2021-09-15 22:25:15
     */
    @GetMapping("{id}")
    public ResponseData<VVipDTO> get(@PathVariable("id") Long id) {
        VVipDTO data = vVipService.get(id);

        return new ResponseData<VVipDTO>().success(data);
    }

    // ==================== 增加 ====================

    /**
     * Description: 【 新增保存 】
     *
     * @param dto
     * @author : ※狗尾巴草
     * @email : "m962464oo@gmail.com"
     * @date : 2021-09-15 22:25:15
     */
    @PostMapping("save")
    public ResponseData save(@RequestBody VVipDTO dto) throws Exception {
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);
        dto.setSales(0);
        vVipService.save(dto);

        return new ResponseData();
    }

    // =================== 修改 ============================

    /**
     * Description: 【 修改 】
     *
     * @param dto
     * @author : ※狗尾巴草
     * @email : "m962464oo@gmail.com"
     * @date : 2021-09-15 22:25:15
     */
    @PutMapping("update")
    public ResponseData update(@RequestBody VVipDTO dto) throws Exception {
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        vVipService.update(dto);

        return new ResponseData();
    }


    // ============================= 删除 ===============================

    /**
     * Description:【 通过ID删除单条数据 】
     *
     * @param id
     * @return
     * @author : ※狗尾巴草
     * @email : "m962464oo@gmail.com"
     * @date : 2021-09-15 22:25:15
     */
    @DeleteMapping("{id}")
    public ResponseData delete(@PathVariable("id") Long id) {
        //效验数据
        AssertUtil.isNotNull(id, "参数不能为空");

        vVipService.delete(id);

        return new ResponseData();
    }

    /**
     * Description: 【 批量删除 】
     *
     * @param ids
     * @author : ※狗尾巴草
     * @email : "m962464oo@gmail.com"
     * @date : 2021-09-15 22:25:15
     */
    @DeleteMapping("delete")
    public ResponseData delete(@RequestBody Long[] ids) {
        //效验数据
        // AssertUtil.isNotNull(id, "参数不能为空");

        vVipService.deleteBatch(ids);

        return new ResponseData();
    }

    // ==================== 导出 ====================

    //   /**
    //    * Description: 【 导出为Excel 】
    //    *
    //    * @author : ※狗尾巴草
    //    * @email : "m962464oo@gmail.com"
    //    * @date : 2021-09-15 22:25:15
    //    *
    //    * @param queryParams 查询条件
    //    */
    //  @GetMapping("export")
    //  public void export(@RequestParam Map<String, Object> queryParams, HttpServletResponse response) throws Exception {
    //      List<VVipDTO> list = vVipService.list(queryParams);
//
    //       ExcelUtils.exportExcelToTarget(response, null, list, VVipExcel.class);
    //   }

    // ======================== 其他 ==============================

}

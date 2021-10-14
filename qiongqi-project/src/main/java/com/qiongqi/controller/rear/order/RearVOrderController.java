package com.qiongqi.controller.rear.order;

import com.qiongqi.config.MybatisPlus.group.AddGroup;
import com.qiongqi.config.MybatisPlus.group.DefaultGroup;
import com.qiongqi.config.MybatisPlus.group.UpdateGroup;
import com.qiongqi.modules.order.model.dto.VOrderDTO;
import com.qiongqi.modules.order.service.VOrderService;
import com.qiongqi.utils.AssertUtil;
import com.qiongqi.utils.ValidatorUtils;
import com.qiongqi.utils.controller.BaseController;
import com.qiongqi.utils.page.PageData;
import com.qiongqi.utils.response.data.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Description: 【 订单 后台Controller控制器 】
 *
 * @author : ※狗尾巴草
 * @version : 1.0.0
 * @email : "m962464oo@gmail.com"
 * @date : 2021-10-09 23:57:42
 */
@RestController
@RequestMapping("/admin/vOrder/")
public class RearVOrderController extends BaseController {

    @Autowired
    private VOrderService vOrderService;

    // ==================== 查询 ====================

    /**
     * Description: 【 分页条件查询 】
     *
     * @param queryParams
     * @author : ※狗尾巴草
     * @email : "m962464oo@gmail.com"
     * @date : 2021-10-09 23:57:42
     */
    @GetMapping("page")
    public ResponseData<PageData<VOrderDTO>> pageGet(@RequestParam Map<String, Object> queryParams) {
        PageData<VOrderDTO> page = vOrderService.page(queryParams);
        List<VOrderDTO> list = page.getList();

        return new ResponseData<PageData<VOrderDTO>>().success(page);
    }

    /**
     * Description:【 列表条件查询 】
     *
     * @param queryParams
     * @return
     * @author : ※狗尾巴草
     * @email : "m962464oo@gmail.com"
     * @date : 2021-10-09 23:57:42
     */
    @GetMapping("list")
    public ResponseData<List<VOrderDTO>> listGet(@RequestParam Map<String, Object> queryParams) {
        List<VOrderDTO> data = vOrderService.list(queryParams);

        return new ResponseData<List<VOrderDTO>>().success(data);
    }

    /**
     * Description: 【 通过ID查询单一数据 】
     *
     * @param id
     * @author : ※狗尾巴草
     * @email : "m962464oo@gmail.com"
     * @date : 2021-10-09 23:57:42
     */
    @GetMapping("{id}")
    public ResponseData<VOrderDTO> get(@PathVariable("id") Long id) {
        VOrderDTO data = vOrderService.get(id);

        return new ResponseData<VOrderDTO>().success(data);
    }

    // ==================== 增加 ====================

    /**
     * Description: 【 新增保存 】
     *
     * @param dto
     * @author : ※狗尾巴草
     * @email : "m962464oo@gmail.com"
     * @date : 2021-10-09 23:57:42
     */
    @PostMapping("save")
    public ResponseData save(@RequestBody VOrderDTO dto) throws Exception {
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        vOrderService.save(dto);

        return new ResponseData();
    }

    // =================== 修改 ============================

    /**
     * Description: 【 修改 】
     *
     * @param dto
     * @author : ※狗尾巴草
     * @email : "m962464oo@gmail.com"
     * @date : 2021-10-09 23:57:42
     */
    @PutMapping("update")
    public ResponseData update(@RequestBody VOrderDTO dto) throws Exception {
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        vOrderService.update(dto);

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
     * @date : 2021-10-09 23:57:42
     */
    @DeleteMapping("{id}")
    public ResponseData delete(@PathVariable("id") Long id) {
        //效验数据
        AssertUtil.isNotNull(id, "参数不能为空");

        vOrderService.delete(id);

        return new ResponseData();
    }

    /**
     * Description: 【 批量删除 】
     *
     * @param ids
     * @author : ※狗尾巴草
     * @email : "m962464oo@gmail.com"
     * @date : 2021-10-09 23:57:42
     */
    @DeleteMapping("delete")
    public ResponseData delete(@RequestBody Long[] ids) {
        //效验数据
        // AssertUtil.isNotNull(id, "参数不能为空");

        vOrderService.deleteBatch(ids);

        return new ResponseData();
    }

    // ==================== 导出 ====================

    //   /**
    //    * Description: 【 导出为Excel 】
    //    *
    //    * @author : ※狗尾巴草
    //    * @email : "m962464oo@gmail.com"
    //    * @date : 2021-10-09 23:57:42
    //    *
    //    * @param queryParams 查询条件
    //    */
    //  @GetMapping("export")
    //  public void export(@RequestParam Map<String, Object> queryParams, HttpServletResponse response) throws Exception {
    //      List<VOrderDTO> list = vOrderService.list(queryParams);
//
    //       ExcelUtils.exportExcelToTarget(response, null, list, VOrderExcel.class);
    //   }

    // ======================== 其他 ==============================

}

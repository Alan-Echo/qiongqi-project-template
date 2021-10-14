package com.qiongqi.controller.rear.qiniu;

import com.qiongqi.config.MybatisPlus.group.AddGroup;
import com.qiongqi.config.MybatisPlus.group.DefaultGroup;
import com.qiongqi.config.MybatisPlus.group.UpdateGroup;
import com.qiongqi.modules.qiniu.model.dto.VQiniuDTO;
import com.qiongqi.modules.qiniu.service.VQiniuService;
import com.qiongqi.utils.AssertUtil;
import com.qiongqi.utils.ValidatorUtils;
import com.qiongqi.utils.page.PageData;
import com.qiongqi.utils.response.data.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Description: 【 七牛云 后台Controller控制器 】
 *
 * @author : ※狗尾巴草
 * @version : 1.0.0
 * @email : "m962464oo@gmail.com"
 * @date : 2021-06-29 00:03:34
 */
@RestController
@RequestMapping("/admin/vQiniu/")
public class RearVQiniuController {

    @Autowired
    private VQiniuService vQiniuService;

    // ==================== 查询 ====================

    /**
     * Description: 【 分页条件查询 】
     *
     * @param queryParams
     * @author : ※狗尾巴草
     * @email : "m962464oo@gmail.com"
     * @date : 2021-06-29 00:03:34
     */
    @GetMapping("page")
    public ResponseData<PageData<VQiniuDTO>> pageGet(@RequestParam Map<String, Object> queryParams) {
        PageData<VQiniuDTO> page = vQiniuService.page(queryParams);

        return new ResponseData<PageData<VQiniuDTO>>().success(page);
    }

    /**
     * Description:【 列表条件查询 】
     *
     * @param queryParams
     * @return
     * @author : ※狗尾巴草
     * @email : "m962464oo@gmail.com"
     * @date : 2021-06-29 00:03:34
     */
    @GetMapping("list")
    public ResponseData<List<VQiniuDTO>> listGet(@RequestParam Map<String, Object> queryParams) {
        List<VQiniuDTO> data = vQiniuService.list(queryParams);

        return new ResponseData<List<VQiniuDTO>>().success(data);
    }

    /**
     * Description: 【 通过ID查询单一数据 】
     *
     * @param id
     * @author : ※狗尾巴草
     * @email : "m962464oo@gmail.com"
     * @date : 2021-06-29 00:03:34
     */
    @GetMapping("{id}")
    public ResponseData<VQiniuDTO> get(@PathVariable("id") Long id) {
        VQiniuDTO data = vQiniuService.get(id);

        return new ResponseData<VQiniuDTO>().success(data);
    }

    // ==================== 增加 ====================

    /**
     * Description: 【 新增保存 】
     *
     * @param dto
     * @author : ※狗尾巴草
     * @email : "m962464oo@gmail.com"
     * @date : 2021-06-29 00:03:34
     */
    @PostMapping("save")
    public ResponseData save(@RequestBody VQiniuDTO dto) throws Exception {
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        vQiniuService.save(dto);

        return new ResponseData();
    }

    // =================== 修改 ============================

    /**
     * Description: 【 修改 】
     *
     * @param dto
     * @author : ※狗尾巴草
     * @email : "m962464oo@gmail.com"
     * @date : 2021-06-29 00:03:34
     */
    @PutMapping("update")
    public ResponseData update(@RequestBody VQiniuDTO dto) throws Exception {
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        vQiniuService.update(dto);

        return new ResponseData();
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
    @GetMapping("status/{id}")
    public ResponseData changeStatusGet(@PathVariable("id") Long id, @RequestParam("status") Integer status) {

        vQiniuService.changeStatus(id, status);

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
     * @date : 2021-06-29 00:03:34
     */
    @DeleteMapping("{id}")
    public ResponseData delete(@PathVariable("id") Long id) {
        //效验数据
        AssertUtil.isNotNull(id, "参数不能为空");

        vQiniuService.delete(id);

        return new ResponseData();
    }

    /**
     * Description: 【 批量删除 】
     *
     * @param ids
     * @author : ※狗尾巴草
     * @email : "m962464oo@gmail.com"
     * @date : 2021-06-29 00:03:34
     */
    @DeleteMapping("delete")
    public ResponseData delete(@RequestBody Long[] ids) {
        //效验数据
        // AssertUtils.isArrayEmpty(ids, "id");

        vQiniuService.deleteBatch(ids);

        return new ResponseData();
    }

    // ==================== 导出 ====================

    //   /**
    //    * Description: 【 导出为Excel 】
    //    *
    //    * @author : ※狗尾巴草
    //    * @email : "m962464oo@gmail.com"
    //    * @date : 2021-06-29 00:03:34
    //    *
    //    * @param queryParams 查询条件
    //    */
    //  @GetMapping("export")
    //  public void export(@RequestParam Map<String, Object> queryParams, HttpServletResponse response) throws Exception {
    //      List<VQiniuDTO> list = vQiniuService.list(queryParams);
//
    //       ExcelUtils.exportExcelToTarget(response, null, list, VQiniuExcel.class);
    //   }

    // ======================== 其他 ==============================

}

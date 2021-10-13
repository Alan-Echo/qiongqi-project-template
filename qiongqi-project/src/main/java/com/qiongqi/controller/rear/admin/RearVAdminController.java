package com.qiongqi.controller.rear.admin;

import com.qiongqi.modules.admin.dto.VAdminDTO;
import com.qiongqi.modules.admin.service.VAdminService;
import com.qiongqi.utils.page.PageData;
import com.qiongqi.utils.response.data.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Description: 【 管理员 后台Controller控制器 】
 *
 * @author : ※狗尾巴草
 * @version : 1.0.0
 * @email : "m962464oo@gmail.com"
 * @date : 2021-01-16 14:22:49
 */
@RestController
@RequestMapping("/admin/")
public class RearVAdminController {

    @Autowired
    private VAdminService vAdminService;

    // ==================== 查询 ====================

    /**
     * Description: 【 分页条件查询 】
     *
     * @param queryParams
     * @author : ※狗尾巴草
     * @email : "m962464oo@gmail.com"
     * @date : 2021-01-16 14:22:49
     */
    @GetMapping("page")
    public ResponseData<PageData<VAdminDTO>> pageGet(@RequestParam Map<String, Object> queryParams) {
        PageData<VAdminDTO> page = vAdminService.page(queryParams);

        return new ResponseData<PageData<VAdminDTO>>().success(page);
    }

    /**
     * Description:【 列表条件查询 】
     *
     * @param queryParams
     * @return
     * @author : ※狗尾巴草
     * @email : "m962464oo@gmail.com"
     * @date : 2021-01-16 14:22:49
     */
    @GetMapping("list")
    public ResponseData<List<VAdminDTO>> listGet(@RequestParam Map<String, Object> queryParams) {
        List<VAdminDTO> data = vAdminService.list(queryParams);

        return new ResponseData<List<VAdminDTO>>().success(data);
    }

    /**
     * Description: 【 通过ID查询单一数据 】
     *
     * @param
     * @author : ※狗尾巴草
     * @email : "m962464oo@gmail.com"
     * @date : 2021-01-16 14:22:49
     */
    @GetMapping("info")
    public ResponseData<VAdminDTO> get() {
        VAdminDTO data = vAdminService.get();
        return new ResponseData<VAdminDTO>().success(data);
    }

    // ==================== 增、改、删 ====================

    /**
     * Description: 【 新增保存 】
     *
     * @param dto
     * @author : ※狗尾巴草
     * @email : "m962464oo@gmail.com"
     * @date : 2021-01-16 14:22:49
     */
    @PostMapping
    public ResponseData save(@RequestBody VAdminDTO dto) throws Exception {
        //效验数据
//        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        vAdminService.save(dto);

        return new ResponseData();
    }

    /**
     * Description: 【 修改 】
     *
     * @param dto
     * @author : ※狗尾巴草
     * @email : "m962464oo@gmail.com"
     * @date : 2021-01-16 14:22:49
     */
    @PutMapping
    public ResponseData update(@RequestBody VAdminDTO dto) throws Exception {
        //效验数据
//        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        vAdminService.update(dto);

        return new ResponseData();
    }


    /**
     * Description:【 通过ID删除单条数据 】
     *
     * @param id
     * @return
     * @author : ※狗尾巴草
     * @email : "m962464oo@gmail.com"
     * @date : 2021-01-16 14:22:49
     */
    @DeleteMapping("{id}")
    public ResponseData delete(@PathVariable("id") Long id) {
        //效验数据
//        AssertUtils.isNull(id, "id");

        vAdminService.delete(id);

        return new ResponseData();
    }

    /**
     * Description: 【 批量删除 】
     *
     * @param ids
     * @author : ※狗尾巴草
     * @email : "m962464oo@gmail.com"
     * @date : 2021-01-16 14:22:49
     */
    @DeleteMapping
    public ResponseData delete(@RequestBody Long[] ids) {
        //效验数据
        // AssertUtils.isArrayEmpty(ids, "id");

        vAdminService.deleteBatch(ids);

        return new ResponseData();
    }

    // ==================== 其他 ====================



}

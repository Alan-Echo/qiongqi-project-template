package com.qiongqi.controller.rear.user;

import com.qiongqi.modules.user.model.dto.VUserDTO;
import com.qiongqi.modules.user.service.VUserService;
import com.qiongqi.controller.base.BaseController;
import com.qiongqi.utils.page.PageData;
import com.qiongqi.utils.response.data.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @description: 说明
 * @author: ※狗尾巴草
 * @date: 2021-03-22 20:56
 **/
@RestController
@RequestMapping("/admin/user")
public class RearUserController extends BaseController {

    @Autowired
    private VUserService vUserService;
    /**
     * Description: 【 分页条件查询 】
     *
     * @param queryParams
     * @author : ※狗尾巴草
     * @email : "m962464oo@gmail.com"
     * @date : 2021-01-16 14:22:49
     */
    @GetMapping("page")
    public ResponseData<PageData<VUserDTO>> pageGet(@RequestParam Map<String, Object> queryParams) {
        PageData<VUserDTO> page = vUserService.page(queryParams);
        return new ResponseData<PageData<VUserDTO>>().success(page);
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

        vUserService.delete(id);

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
    @DeleteMapping("delete")
    public ResponseData delete(@RequestBody Long[] ids) {
        //效验数据
        // AssertUtils.isArrayEmpty(ids, "id");

        vUserService.deleteBatch(ids);

        return new ResponseData();
    }






}

<template>

  <el-dialog :visible.sync="visible" :title="dataId ? '更新' : '添加'" :close-on-click-modal="false" :before-close="chooseModel" :close-on-press-escape="false">

    <el-form :model="dataForm" :rules="rules" ref="dataForm" @keyup.enter.native="dataFormSubmitHandle()">

      <el-row>
        <el-col :span="8">
          <el-form-item label="订单金额 : " prop="total">
            <el-input v-model="dataForm.total" clearable placeholder="订单金额"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="8" :offset="4">
          <el-form-item label="用户ID : " prop="userId">
            <el-select v-model="dataForm.userId" placeholder="请选择" class="model-select">
              <el-option v-for="item in userList" :key="item.id" :label="item.name" :value="item.id">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="8">
          <el-form-item label="商品ID : " prop="goodsId">
            <el-select v-model="dataForm.goodsId" placeholder="请选择" class="model-select">
              <el-option v-for="item in goodsList" :key="item.id" :label="item.name" :value="item.id">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8" :offset="4">
          <el-form-item label="是否支付;1:是;0:否 : " prop="isPay">
            <el-input-number v-model="dataForm.isPay" :min="1" label="是否支付;1:是;0:否"></el-input-number>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>

    <template slot="footer">
      <el-button @click="chooseModel">取消</el-button>
      <el-button type="primary" @click="dataFormSubmitHandle()">确定</el-button>
    </template>

  </el-dialog>

</template>

<script>
export default {
  data() {
    return {
      goodsList: [],
      dataForm: {
        total: "",
        userId: "",
        goodsId: "",
        isPay: 1,
      },
      rules: {
        createTime: [
          // 创建时间 校验
          { required: true, message: "创建时间不能为空", trigger: "blur" },
        ],
        modifyTime: [
          // 修改时间 校验
          { required: true, message: "修改时间不能为空", trigger: "blur" },
        ],
        total: [
          // 订单金额 校验
          { required: true, message: "订单金额不能为空", trigger: "blur" },
        ],
        userId: [
          // 用户ID 校验
          { required: true, message: "用户ID不能为空", trigger: "blur" },
        ],
        goodsId: [
          // 商品ID 校验
          { required: true, message: "商品ID不能为空", trigger: "blur" },
        ],
        isPay: [
          // 是否支付;1:是;0:否 校验
          {
            required: true,
            message: "是否支付;1:是;0:否不能为空",
            trigger: "blur",
          },
        ],
      },
    };
  },
  props: {
    visible: {
      type: Boolean,
      default: false,
    },
    dataId: {
      type: String,
      default: "",
    },
  },
  created() {
    // 获取goods下拉框数据
    this.getgoodsList();
    if (this.dataId) {
      this.getInfo(); //查询数据详情
    }
  },
  methods: {
    //文件上传成功
    //获取详情
    getInfo() {
      this.$http
        .get("/admin/vOrder/" + this.dataId)
        .then((resp) => {
          this.dataForm = resp.data;
        })
        .catch((err) => {});
    },
    // 获取goods下拉框数据
    getgoodsList() {
      this.$http
        .get("/admin/goods/list")
        .then((resp) => {
          this.goodsList = resp.data;
        })
        .catch((err) => {});
    },
    // 表单提交
    dataFormSubmitHandle() {
      this.$refs["dataForm"].validate((valid) => {
        if (!valid) {
          return false;
        }
        this.$http[this.dataId ? "put" : "post"](
          "/admin/vOrder/" + (this.dataId ? "update" : "save"),
          this.dataForm
        )
          .then((res) => {
            if (res.status !== 200) {
              return this.$message.error(res.msg);
            }
            this.$message({
              message: res.msg,
              type: "success",
              duration: 500,
              onClose: () => {
                this.chooseModel();
              },
            });
          })
          .catch(() => {});
      });
    },
    //============关闭弹窗===========
    chooseModel() {
      this.$emit("chooseModel");
    },
  },
};
</script>

<style lang="scss" scoped>
.model-select {
  display: block;
}
.mod-order_vOrder {
  .vOrder-list {
    .el-input__inner,
    .el-input__suffix {
      cursor: pointer;
    }
  }
}
</style>

<style>
</style>



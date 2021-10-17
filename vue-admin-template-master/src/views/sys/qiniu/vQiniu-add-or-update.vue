<template>

  <el-dialog :visible.sync="visible" :title="dataId ? '更新' : '添加'" :close-on-click-modal="false" :show-close="false" :close-on-press-escape="false">

    <el-form :model="dataForm" :rules="rules" ref="dataForm" @keyup.enter.native="dataFormSubmitHandle()">

      <el-row>
        <el-col :span="8">
          <el-form-item label="地区 : " prop="zone">
            <!-- <el-input v-model="dataForm.zone" clearable placeholder="地址"></el-input> -->
            <el-select v-model="dataForm.zone" placeholder="请选择">
                <el-option
                    v-for="item in zoneList"
                    :key="item"
                    :label="item"
                    :value="item">
                </el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8" :offset="4">
          <el-form-item label="空间名 : " prop="bucketName">
            <el-input v-model="dataForm.bucketName" clearable placeholder="空间名"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="8">
          <el-form-item label="AK密码 : " prop="ak">
            <el-input v-model="dataForm.ak" clearable placeholder="AK密码"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="8" :offset="4">
          <el-form-item label="SK密钥 : " prop="sk">
            <el-input v-model="dataForm.sk" clearable placeholder="SK密钥"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="8">
          <el-form-item label="域名 : " prop="doMain">
            <el-input v-model="dataForm.doMain" clearable placeholder="域名"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="8" :offset="4">
          <el-form-item label="状态 : " prop="status">
            <!-- <el-input v-model="dataForm.status" clearable placeholder="状态:0:禁用;1启用"></el-input> -->
              <el-radio-group v-model="dataForm.status" size="small">
                <el-radio-button :label="1">启用</el-radio-button>
                <el-radio-button :label="0">禁用</el-radio-button>
              </el-radio-group>
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
        zoneList:['华东','华北','华南','北美','东南亚'],
      dataForm: {
        zone: "",
        bucketName: "",
        ak: "",
        sk: "",
        doMain: "",
        status: 1,
      },
      rules: {
        zone: [// 地区 校验
            { required: true, message: "地址不能为空", trigger: "blur" }], 
        bucketName: [ // 空间名 校验
          { required: true, message: "空间名不能为空", trigger: "blur" },
        ], 
        ak: [ // AK密码 校验
            { required: true, message: "AK密码不能为空", trigger: "blur" }], 
        sk: [// SK密钥 校验
            { required: true, message: "SK密钥不能为空", trigger: "blur" }], 
        doMain: [// 域名 校验
            { required: true, message: "域名不能为空", trigger: "blur" }], 
        status: [// 状态:0:禁用;1启用 校验
          {required: true,message: "状态不能为空",trigger: "blur",}
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
    if (this.dataId) {
      this.getInfo(); //查询数据详情
    }
  },
  methods: {
    //文件上传成功
    //获取详情
    getInfo() {
      this.$http
        .get("/admin/vQiniu/" + this.dataId)
        .then((resp) => {
          this.dataForm = resp.data;
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
          "/admin/vQiniu/" + (this.dataId ? "update" : "save"),
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
.mod-qiniu_vQiniu {
  .vQiniu-list {
    .el-input__inner,
    .el-input__suffix {
      cursor: pointer;
    }
  }
}
</style>

<style>
</style>




<template>
  <el-container class="app-container" style="height:auto;">
    <el-header style="height: auto;text-align: right;">
        <el-button type="success" @click="dataFormSubmitHandle">保存</el-button>
    </el-header>
    <el-main>
      <div class="data-box" v-loading="loading" element-loading-text="拼命加载中" element-loading-spinner="el-icon-loading">
        <el-form ref="dataForm" :model="dataForm" >
            <el-row>
                <el-col :span="6">
                    <el-form-item prop="notifyUrl" label="支付通知回调域名">
                        <el-input v-model="dataForm.notifyUrl" clearable></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="6" :offset="1">
                    <el-form-item prop="payExTime" label="支付有效期">
                        <el-input-number v-model="dataForm.payExTime" :min="1" :max="120" clearable></el-input-number>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="10">
                    <el-form-item prop="aliAppId" label="支付宝APPID">
                        <el-input v-model="dataForm.aliAppId" clearable></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="10" :offset="4">
                    <el-form-item prop="wxAppId" label="微信APPID">
                        <el-input v-model="dataForm.wxAppId" clearable></el-input>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="10">
                    <el-form-item prop="aliMerchantPrivateKey" label="支付宝商户私钥">
                        <el-input v-model="dataForm.aliMerchantPrivateKey" clearable></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="10" :offset="4">
                    <el-form-item prop="wxPrivateKey" label="商户的key【API密匙】">
                        <el-input v-model="dataForm.wxPrivateKey" clearable></el-input>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="10">
                    <el-form-item prop="aliPublicKey" label="支付宝公钥">
                        <el-input v-model="dataForm.aliPublicKey" clearable></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="10" :offset="4">
                    <el-form-item prop="wxMchId" label="商户号">
                        <el-input v-model="dataForm.wxMchId" clearable></el-input>
                    </el-form-item>
                </el-col>
            </el-row>
        </el-form>
      </div>
    </el-main>
  </el-container>
</template>

<script>
export default {
  inject: ["reload"], //注入App里的reload方法
  data() {
    return {
      loading: false,
      dataForm: {
          payExTime:30
      },
    };
  },
  mounted() {
    this.getDataList();
  },
  methods: {
    //获取数据
    getDataList() {
      this.loading = true;
      this.$http
        .get(`/admin/vPayconfig/get`)
        .then((resp) => {
            if(resp.data){
                this.dataForm = resp.data;
            }
            this.loading = false;
            console.log('dataForm',this.dataForm);
          
        })
        .catch(() => {
          this.loading = false;
        });
    },
    // 表单提交
    dataFormSubmitHandle() {
      this.$refs["dataForm"].validate((valid) => {
        if (!valid) {
          return false;
        }
        this.$http.post("/admin/vPayconfig/update",this.dataForm).then((res) => {
            if (res.status !== 200) {
              return this.$message.error(res.msg);
            }
            this.$message({
              message: res.msg,
              type: "success",
              duration: 500
            });
          })
          .catch(() => {});
      });
    },
  },
};
</script>

<style lang="scss" scoped>
.app-container {
  .type-select {
    height: 130px;
    width: 100%;
    .box-select {
      margin-left: 20px;
    }
  }
  .data-box {
    width: 80%;
    margin: 0 auto;
  }

}
.user-avatar {
  text-align: center;
}
.u-avatar {
  width: 80px;
  height: 80px;
  border-radius: 50%;
}
.seach-box {
  text-align: center;
}
.btn-cla-box {
  text-align: center;
  .btn-clas {
    margin: 2px;
    display: block;
    margin: 0 auto;
  }
}
</style>

<style  scoped>
.el-input /deep/ .el-input__inner {
  height: 30px;
  line-height: 30px;
  font-size: 20px;
}
::v-deep .el-form th,
::v-deep .el-form td {
  text-align: center !important;
}
.el-input /deep/ .el-input__inner {
  height: 40px;
  font-weight: bold;
  line-height: 40px;
  font-size: 15px;
}
.el-form--inline /deep/ .el-form-item__label,
.el-form--inline >>> .el-form-item__content {
  line-height: 40px;
}
</style>

<style lang="scss">
  .data-box {
    .el-form{
        .el-row{
            .el-col{
                .el-form-item{
                    & .el-form-item__label{
                        text-align:left;
                        //加下面两条就行
                        float: none; // 取消浮动
                        word-break: break-word; // 支持单词截断换行
                    }
                }
            }
        }
    }

  }
</style>

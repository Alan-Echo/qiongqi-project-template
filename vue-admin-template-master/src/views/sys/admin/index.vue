<template>
  <div class="page-container">
    <el-card shadow="hover">
        <div slot="header" class="clearfix">
            <span>管理员</span>
        </div>
        <div class="user-name">
          <div class="data-item">
            <el-input v-model="USER.userName" placeholder="请输入用户名">
              <template slot="append">
                <el-button @click="updateUserName" type="success">确定</el-button>
              </template>
            </el-input>
          </div>
          <div class="data-item">
            <el-input v-model="USER.password" show-password placeholder="请输入密码">
              <template slot="append">
                <el-button @click="updateUserName" type="success">确定</el-button>
              </template>
            </el-input>
          </div>
          <div class="data-item">
            <el-input v-model="USER.mail" placeholder="请输入邮箱">
              <template slot="append">
                <el-button @click="updateUserName" type="success">确定</el-button>
              </template>
            </el-input>
          </div>
        </div>
    </el-card>
  </div>
</template>

<script>
export default {
  data(){
    return{
        USER:{
          userName:"",
          password:"",
          mail:""
        }
    }
  },
  created(){

  },
  methods:{
    updateUserName(){
      this.$http.post("/admin/update/userInfo",this.USER).then(resp=>{
        this.$message({
          message: '修改成功',
          type: 'success'
        });
      }).catch(err=>{})
    },
  },
}
</script>

<style lang="scss" scoped>
  .page-container{
      .user-name{
        width: 50%;
        margin: 0 auto;
        .data-item{
          margin: 20px;
        }
      }
  }
</style>
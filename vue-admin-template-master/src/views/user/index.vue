<template>
  <el-container class="app-container" v-loading="loading"
    element-loading-text="拼命加载中"
    element-loading-spinner="el-icon-loading">
    <el-header class="seach-box" style="height:auto;">
      <el-form :inline="true" :model="pageForm" class="demo-form-inline">
        <el-form-item label="注册时间:">
          <picker v-model="pageForm.dateTime"></picker>
        </el-form-item>
        <el-form-item label="昵称:">
          <el-input v-model="pageForm.nickName" placeholder="用户名"></el-input>
        </el-form-item>
        <el-form-item label="用户openId:">
          <el-input v-model="pageForm.openId" placeholder="用户ID"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="seachSubmit">查询</el-button>
        </el-form-item>
        <el-form-item>
          <el-button type="danger" :loading="loading" @click="deleteBach">批量删除</el-button>
        </el-form-item>
      </el-form>
    </el-header>
    <el-main>
      <div class="data-box" v-loading="loading" element-loading-text="拼命加载中" element-loading-spinner="el-icon-loading">
        <el-table :data="dataList" @selection-change="handleSelectionChange" border style="width: 100%" :cell-style="{height: '80px'}" :row-style="{height: '80px'}">
        <el-table-column type="selection" width="55">
        </el-table-column>
          <el-table-column fixed label="序号"  type="index" width="60"> </el-table-column>
          <el-table-column prop="avatar" fixed fit  label="头像" class="user-avatar">
              <template slot-scope="scope">
                  <img class="u-avatar" v-lazy="scope.row.avatar" />
              </template>
          </el-table-column>
          <el-table-column prop="createTime" fit label="注册时间" > </el-table-column>
          <el-table-column prop="openId" fit label="用户openId" > </el-table-column>
          <el-table-column prop="nickName" fit label="昵称" > </el-table-column>

          <el-table-column prop="gender" fit label="性别" > 
            <template slot-scope="scope">
              <div v-if="scope.row.gender==0">未知</div>
              <div v-if="scope.row.gender==1">男</div>
              <div v-if="scope.row.gender==2">女</div>
            </template>
          </el-table-column>
          <el-table-column prop="province" fit label="省" > </el-table-column>
          <el-table-column prop="city" fit label="城市" > </el-table-column>
          <el-table-column fixed="right" fit label="操作"  >
            <template slot-scope="scope">
              <div class="btn-cla-box">
                <el-button
                  class="btn-clas"
                  size="mini"
                  type="danger"
                  @click="handleDelete(scope.$index, scope.row)">删除</el-button>
              </div>
            </template>
          </el-table-column>
        </el-table>
      </div>
    <div class="pagination"  v-show="!loading">
      <el-pagination
        background
        @size-change="pageLimitChange"
        @current-change="handleCurrentChange"
        :current-page="pageForm.index"
        :page-sizes="[10, 20, 50, 100]"
        :page-size="pageForm.limit"
        layout="total, sizes, prev, pager, next, jumper"
        :total="pageForm.total">
      </el-pagination>
    </div>
    </el-main>
  </el-container>
</template>

<script>
import picker from '../../components/date/picker'
export default {
    inject:['reload'], //注入App里的reload方法
  components:{picker},
  data() {
    return {
      loading:false,
      dataList:[],
      deleteIdList:[],
      currentPage:1,
      pageForm:{
        type:1,
        index:1,
        pageTotal:0,
        total:0,
        limit:10,
        dateTime:{},
        nickName:"",
        id:"",
      },
    }
  },
  mounted(){
    this.getDataList();
  },
  methods:{
      //获取用户数据
    getDataList(){
      this.loading=true;
      this.$http.get("/admin/user/page",{params:this.pageForm}).then(resp=>{
          if(resp.status!=200){
              this.$message.error(resp.msg)
              return false
          }
          this.dataList=resp.data.list
          this.pageForm.total=resp.data.total
          this.pageForm.index=resp.data.index
          this.pageForm.pageTotal=resp.data.pageTotal
          this.loading=false
      }).catch(()=>{
        this.loading=false
      });
    },
    //删除操作
    handleDelete(index,row){
      this.$confirm('确认删除？').then(_ => {
        this.loading=true
          this.$http.delete("/admin/user/"+row.id).then(resp=>{
            if(resp.status==200){
              this.$message({
                type:'success',
                message:resp.msg
              })
              this.dataList.splice(index,1)
              this.loading=false
            }
          }).catch(err=>{
            this.loading=false
          })
      })
    },
    //批量删除
    deleteBach(){
        var that = this;
        if(this.deleteIdList.length>0){
            this.loading = true;
            this.$http.delete('/admin/user/delete',{data:this.deleteIdList}).then(resp=>{
                if(resp.status!=200){
                    this.$message.error(resp.msg)
                }else{
                    this.$message({
                        type:'success',
                        message:resp.msg
                    })
                    this.reload()
                }
                this.loading = false;
            }).catch(err=>{
                this.loading = false;
            })
        }
    },
    // ===============多选 ===================
    handleSelectionChange(selectedList){
        console.log(selectedList);
        for(var i=0;i<selectedList.length;i++){
            this.deleteIdList[i] = selectedList[i].id
        };
    },
    // ======= 搜索 ================
    seachSubmit(){
      this.pageForm.index=1;
      this.getDataList()
    },
    //  ================ 分页--改变每页显示条数 ============
    pageLimitChange(val) {
      this.pageForm.limit=val
      this.getDataList()
    },
    // ===================== 分页--翻页 ==============
    handleCurrentChange(val) {
      this.pageForm.index=val
      this.getDataList()
    },
    toCopy(postId){
      let that =this;
      this.$copyText(postId).then(function (e) {
          that.$message({type:'success',message:"复制成功"})
      }, function (e) {
          that.$message.error('复制失败,请手动复制');
          that.$alert(postId, '手动复制', {
            confirmButtonText: '确定',
            callback: action => {}
          });
      })
    },
  }
}
</script>

<style lang="scss" scoped>
.app-container{
  .type-select{
    height: 130px;
    width: 100%;
    .box-select{
      margin-left: 20px;
    }
  }
  .data-box{
    width: 100%;
  }
  .pagination{
    text-align: center;
    margin-top: 30px;
    font-size: 20px;
  }
}
.user-avatar{
    text-align: center;
}
.u-avatar{
    width: 80px;
    height: 80px;
    border-radius: 50%;
}
.seach-box{
  text-align: center;
}
.btn-cla-box{
  text-align: center;
  .btn-clas{
    margin: 2px;
    display: block;
    margin: 0 auto;
  }
}
</style>

<style  scoped>
  .el-input /deep/ .el-input__inner{
      height: 30px;
      line-height: 30px;
      font-size: 20px;
  }
    ::v-deep .el-table th,
    ::v-deep .el-table td {
        text-align: center !important;
    }
  .el-input /deep/ .el-input__inner{
      height: 40px;
      font-weight: bold;
      line-height: 40px;
      font-size: 15px;
  }
  .el-form--inline /deep/ .el-form-item__label,.el-form--inline >>> .el-form-item__content{
    line-height: 40px;
  }
</style>



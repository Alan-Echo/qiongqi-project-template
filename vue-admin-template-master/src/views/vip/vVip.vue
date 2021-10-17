
<template>
  <el-container class="app-container" style="height:auto;">
    <el-header style="height: auto;">
      <el-row>
        <el-form :inline="true" :model="pageForm" class="demo-form-inline">
          <el-form-item label="添加时间:">
            <picker v-model="pageForm.dateTime"></picker>
          </el-form-item>
          <el-form-item label="名称:">
            <el-input v-model="pageForm.name" clearable placeholder="名称"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="seachSubmit">查询</el-button>
          </el-form-item>
        </el-form>
      </el-row>
      <el-row>
        <el-form :inline="true" class="demo-form-inline">
          <el-form-item>
            <el-button type="primary" @click="addData">添加</el-button>
          </el-form-item>
          <el-form-item>
            <el-button type="danger" :loading="loading" @click="deleteBach">批量删除</el-button>
          </el-form-item>
        </el-form>
      </el-row>
    </el-header>
    <el-main>
      <div class="data-box" v-loading="loading" element-loading-text="拼命加载中" element-loading-spinner="el-icon-loading">
        <el-table :data="dataList" @selection-change="handleSelectionChange" border style="width: 100%" :cell-style="{height: '80px'}" :row-style="{height: '80px'}">
          <el-table-column type="selection" width="55"></el-table-column>
          <el-table-column fixed label="序号" type="index" width="60"> </el-table-column>
          <el-table-column prop="name" label="名称" header-align="center" align="center"></el-table-column>
          <el-table-column prop="img" label="图片" header-align="center" align="center">
              <template slot-scope="scope">
                  <img :src="scope.row.img" style="width:50px;height:50px;" />
              </template>
          </el-table-column>
          <el-table-column prop="total" label="价格" header-align="center" align="center"></el-table-column>

          <el-table-column prop="type" label="类型" header-align="center" align="center">
            <template slot-scope="scope">{{ scope.row.type==1?'周卡':scope.row.type==2?'月卡':'年卡' }}
            </template>
          </el-table-column>

          <el-table-column prop="remark" label="说明" header-align="center" align="center">
              <template slot-scope="scope">
                  <span>{{scope.row.remark.substring(0,10)}}</span>
              </template>
          </el-table-column>

          <el-table-column fixed="right" fit label="操作" width="200">
            <template slot-scope="scope">
              <el-button class="btn-clas" size="mini" type="primary" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
              <el-button class="btn-clas" size="mini" type="danger" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
      </div>
      <div class="pagination" v-show="!loading">
        <el-pagination background @size-change="pageLimitChange" @current-change="handleCurrentChange" :current-page="pageForm.index" :page-sizes="[10, 20, 50, 100]" :page-size="pageForm.limit" layout="total, sizes, prev, pager, next, jumper" :total="pageForm.total">
        </el-pagination>
      </div>
    </el-main>
    <addorUpdate v-if="showAddOrUpdate" @chooseModel="chooseModel" :visible="showAddOrUpdate" :dataId="dataId"></addorUpdate>
  </el-container>
</template>

<script>
import picker from "@/components/date/picker";
import addorUpdate from "./vVip-add-or-update";
export default {
  inject: ["reload"], //注入App里的reload方法
  components: { picker, addorUpdate },
  data() {
    return {
      loading: false,
      dataList: [],
      deleteIdList: [],
      currentPage: 1,
      dataId: "",
      showAddOrUpdate: false,
      pageForm: {
        type: 1,
        index: 1,
        pageTotal: 0,
        total: 0,
        limit: 10,
        dateTime: {},
        name: "",
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
        .get(`/admin/vVip/page`, { params: this.pageForm })
        .then((resp) => {
          if (resp.status != 200) {
            this.$message.error(resp.msg);
            return false;
          }
          this.dataList = resp.data.list;
          this.pageForm.total = resp.data.total;
          this.pageForm.index = resp.data.index;
          this.pageForm.pageTotal = resp.data.pageTotal;
          this.loading = false;
        })
        .catch(() => {
          this.loading = false;
        });
    },
    //=============编辑=============
    handleEdit(index, row) {
      this.dataId = row.id;
      this.showAddOrUpdate = true;
    },
    //删除操作
    handleDelete(index, row) {
      this.$confirm("确认删除？").then((_) => {
        this.loading = true;
        this.$http
          .delete(`/admin/vVip/` + row.id)
          .then((resp) => {
            if (resp.status == 200) {
              this.$message({
                type: "success",
                message: resp.msg,
              });
              this.dataList.splice(index, 1);
              this.loading = false;
            }
          })
          .catch((err) => {
            this.loading = false;
          });
      });
    },
    //批量删除
    deleteBach() {
      var that = this;
      if (this.deleteIdList.length > 0) {
        this.$confirm("确认删除？").then((_) => {
          this.loading = true;
          this.$http
            .delete(`/admin/vVip/delete`, { data: this.deleteIdList })
            .then((resp) => {
              if (resp.status != 200) {
                this.$message.error(resp.msg);
              } else {
                this.$message({
                  type: "success",
                  message: resp.msg,
                });
                this.reload();
              }
              this.loading = false;
            })
            .catch((err) => {
              this.loading = false;
            });
        });
      }
    },
    // ===============多选 ===================
    handleSelectionChange(selectedList) {
      console.log(selectedList);
      for (var i = 0; i < selectedList.length; i++) {
        this.deleteIdList[i] = selectedList[i].id;
      }
    },
    // ======= 搜索 ================
    seachSubmit() {
      this.pageForm.index = 1;
      this.getDataList();
    },
    //  ================ 分页--改变每页显示条数 ============
    pageLimitChange(val) {
      this.pageForm.limit = val;
      this.getDataList();
    },
    // ===================== 分页--翻页 ==============
    handleCurrentChange(val) {
      this.pageForm.index = val;
      this.getDataList();
    },
    //==========添加数据==================
    addData() {
      this.showAddOrUpdate = true;
      this.dataId = "";
    },
    //==============关闭弹窗=============
    chooseModel() {
      this.showAddOrUpdate = false;
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
    width: 100%;
  }
  .pagination {
    text-align: center;
    margin-top: 30px;
    font-size: 20px;
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
::v-deep .el-table th,
::v-deep .el-table td {
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

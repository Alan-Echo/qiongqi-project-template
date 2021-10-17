<template>
  <div :style="styles" class="123">
    <!-- 上传文件组件 -->
    <el-upload
        class="avatar-uploader"
      ref="upload"
      :action="getActionUrl"
      :list-type="(uploadType=='1'||uploadType==1)?'text':'picture-card'"
      :multiple="multiple"
      :limit="limit"
      :headers="myHeaders"
      :file-list="fileList"
      :on-exceed="handleExceed"
      :on-preview="handleUploadPreview"
      :on-remove="handleRemove"
      :on-success="handleUploadSuccess"
      :on-error="handleUploadErr"
      :show-file-list="showFileList?true:false"
      :before-upload="handleBeforeUpload"
    >
        <span v-if="fileUrlList.length==0">
            <span v-if="(uploadType=='1'||uploadType==1)">
                <el-button  type="primary">点击上传</el-button> 
            </span>
            <span v-else>
                <i class="el-icon-plus avatar-uploader-icon"></i>
            </span>
        </span>
        <img v-else v-for="(item,i) in fileUrlList" :src="item" :key="i" style="width:100%;height:100%;">
        <div slot="tip" class="el-upload__tip" style="color:#838fa1;">{{tip}}</div>
    </el-upload>
    <el-dialog v-if="(uploadType!='1'||uploadType!=1)" :visible.sync="dialogVisible" size="tiny" append-to-body>
      <img width="100%" :src="dialogImageUrl" alt>
    </el-dialog>
  </div>
</template>
<script>
import {getToken} from "@/utils/auth";
export default {
  data() {
    return {
      // 查看大图
      dialogVisible: false,
      // 查看大图
      dialogImageUrl: "",
      // 组件渲染图片的数组字段，有特殊格式要求
      fileList: [],
      fileUrlList: [],
      myHeaders:{}
    };
  },
  props: ["tip", "action", "limit", "multiple", "fileUrls","uploadType","styles","showFileList"],
  mounted() {
    this.init();
    this.myHeaders= {
      'X-Token':getToken()
    }
  },
  watch: {
    fileUrls: function(val, oldVal) {
      //   console.log("new: %s, old: %s", val, oldVal);
      this.init();
    }
  },
  computed: {
    // 计算属性的 getter
    getActionUrl: function() {
        var api=""
        if(this.action){
            api=this.$bassAPI+"/"+ this.action;
        }else{
            api=this.$bassAPI+"/upload/file"
        }
      return api;
    }
  },
  methods: {
    // 初始化
    init() {
      //   console.log(this.fileUrls);
      if (this.fileUrls) {
        this.fileUrlList = this.fileUrls.split(",");
        console.log('组件-init',this.fileUrls);
        console.log('组件-init',this.fileUrlList);
        let fileArray = [];
        this.fileUrlList.forEach(function(item, index) {
          var url = item;
          var name = index;
          var file = {
            name: name,
            url: url
          };
          fileArray.push(file);
        });
        this.setFileList(fileArray);
      }
    },
    handleBeforeUpload(file) {
	
    },
    // 上传文件成功后执行
    handleUploadSuccess(res, file, fileList) {
      if (res && res.status == 200) {
        fileList[fileList.length - 1]["url"] =  res.data;
        // fileList[fileList.length - 1]["url"] = this.$bassAPI + "/upload/" + file.response.file;
        this.setFileList(fileList);
        console.log("成功",this.fileUrlList)
        this.$emit("change", this.fileUrlList.join(","));
      } else {
        this.$message.error(res.msg);
      }
    },
    // 图片上传失败
    handleUploadErr(err, file, fileList) {
      this.$message.error("文件上传失败");
    },
    // 移除图片
    handleRemove(file, fileList) {
      this.setFileList(fileList);
      this.$emit("change", this.fileUrlList.join(","));
    },
    // 查看大图
    handleUploadPreview(file) {
      this.dialogImageUrl = file.url;
      this.dialogVisible = true;
    },
    // 限制图片数量
    handleExceed(files, fileList) {
      this.$message.warning(`最多上传${this.limit}张图片`);
    },
    // 重新对fileList进行赋值
    setFileList(fileList) {
      var fileArray = [];
      var fileUrlArray = [];
      // 有些图片不是公开的，所以需要携带token信息做权限校验
    //   var token = getToken();
      fileList.forEach(function(item, index) {
        var url = item.url.split("?")[0];
        var name = item.name;
        var file = {
          name: name,
          url: url 
        };
        fileArray.push(file);
        fileUrlArray.push(url);
      });
      this.fileList = fileArray;
      this.fileUrlList = fileUrlArray;
    }
  }
};
</script>
<style lang="scss" scoped>

</style>

<template>

  <el-dialog :visible.sync="visible" width="80%" :title="dataId ? '更新' : '添加'" :close-on-click-modal="false" :before-close="chooseModel" :close-on-press-escape="false">

    <el-form :model="dataForm" :rules="rules" ref="dataForm" style="margin-bottom:100px;">

      <el-row>
        <el-col :span="6">
          <el-form-item label="名称 : " prop="name">
            <el-input v-model="dataForm.name" clearable placeholder="名称"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="5" :offset="1">
          <el-form-item label="价格 : " prop="total">
            <el-input v-model="dataForm.total" type="number" :min="0.5" clearable placeholder="价格"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="4" :offset="1">
          <el-form-item label="类型 : " prop="type">
            <el-select v-model="dataForm.type" style="display:block;">
                <el-option label="周卡" :value="1"></el-option>
                <el-option label="月卡" :value="2"></el-option>
                <el-option label="年卡" :value="3"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="6" :offset="1">
          <el-form-item label="类型 : " prop="img">
            <file-upload :action="uploadApi" :limit="1" :fileUrls="dataForm.img" @change="uploadSuccess" ></file-upload>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col>
          <el-form-item label="说明 : " prop="remark">
            <div class="edit_container">
                <quill-editor 
                    v-model="dataForm.remark" 
                    ref="myQuillEditor" 
                    :options="editorOption" 
                    @blur="onEditorBlur($event)" @focus="onEditorFocus($event)"
                    @change="onEditorChange($event)"
                    class="edit_container">
                </quill-editor>
            </div>
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

import { quillEditor } from "vue-quill-editor"; //调用编辑器
import 'quill/dist/quill.core.css';
import 'quill/dist/quill.snow.css';
import 'quill/dist/quill.bubble.css';
import fileUpload from '@/components/upload/file-upload.vue'
export default {
  data() {
    return {
      dataForm: {
        name: "",
        total: "",
        type: 1,
        remark: "",
        img:""
      },
      uploadApi:"upload/file",
      rules: {
        createTime: [
          // 创建时间 校验
          { required: true, message: "创建时间不能为空", trigger: "blur" },
        ],
        modifyTime: [
          // 修改时间 校验
          { required: true, message: "修改时间不能为空", trigger: "blur" },
        ],
        name: [
          // 名称 校验
          { required: true, message: "名称不能为空", trigger: "blur" },
        ],
        total: [
          // 价格 校验
          { required: true, message: "价格不能为空", trigger: "blur" },
        ],
        expired: [
          // 有效期 校验
          { required: true, message: "有效期不能为空", trigger: "blur" },
        ],
        remark: [
          // 说明 校验
          { required: true, message: "说明不能为空", trigger: "blur" },
        ],
      },
      content:'',
        editorOption: {
            placeholder: "请在这里输入",
            modules:{
                toolbar:[
                    ['bold', 'italic', 'underline', 'strike'],    //加粗，斜体，下划线，删除线
                    ['blockquote', 'code-block'],     //引用，代码块
                    [{ 'header': 1 }, { 'header': 2 }],        // 标题，键值对的形式；1、2表示字体大小
                    [{ 'list': 'ordered'}, { 'list': 'bullet' }],     //列表
                    [{ 'script': 'sub'}, { 'script': 'super' }],   // 上下标
                    [{ 'indent': '-1'}, { 'indent': '+1' }],     // 缩进
                    [{ 'direction': 'rtl' }],             // 文本方向
                    [{ 'size': ['small', false, 'large', 'huge'] }], // 字体大小
                    [{ 'header': [1, 2, 3, 4, 5, 6, false] }],     //几级标题
                    [{ 'color': [] }, { 'background': [] }],     // 字体颜色，字体背景颜色
                    [{ 'font': [] }],     //字体
                    [{ 'align': [] }],    //对齐方式
                    ['clean'],    //清除字体样式
                    ['image','video']    //上传图片、上传视频
                ]
            }
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
  components:{quillEditor,fileUpload},
  created() {
    if (this.dataId) {
      this.getInfo(); //查询数据详情
    }
  },
    computed: {
        editor() {
            return this.$refs.myQuillEditor.quill;
        },
    },
  methods: {
    //把实体格式字符串转义成HTML格式的字符串
    escapeStringHTML(str) {
        str = str.replace(/&lt;/g,'<');
        str = str.replace(/&gt;/g,'>');
        return str;
    },
    onEditorReady(editor) { // 准备编辑器

    },
    onEditorBlur(){}, // 失去焦点事件
    onEditorFocus(){}, // 获得焦点事件
    onEditorChange(){}, // 内容改变事件
    //文件上传成功
    //获取详情
    getInfo() {
      this.$http
        .get("/admin/vVip/" + this.dataId)
        .then((resp) => {
          this.dataForm = resp.data;
        })
        .catch((err) => {});
    },
    uploadSuccess(urls){
        this.dataForm.img=urls
        console.log('上传成功',this.dataForm);
        
    },
    // 表单提交
    dataFormSubmitHandle() {
      this.$refs["dataForm"].validate((valid) => {
        if (!valid) {
          return false;
        }
        this.$http[this.dataId ? "put" : "post"](
          "/admin/vVip/" + (this.dataId ? "update" : "save"),
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
.el-input__suffix{
    padding-top: 40px;
}
</style>





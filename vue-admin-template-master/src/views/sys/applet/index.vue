<template>
    <el-container class="box-contents">
        <el-main class="box-main" v-loading="loading"
            element-loading-text="拼命加载中"
            element-loading-spinner="el-icon-loading"
            element-loading-background="rgba(0, 0, 0, 0.8)">
            <el-form :rules="rules" :model="dataFrom" label-width="100px">
                <el-form-item prop="appId" label="AppID(小程序ID)">
                    <el-input v-model="dataFrom.appId"></el-input>
                </el-form-item>
                <el-form-item prop="appSecret" label="AppSecret(小程序密钥)">
                    <el-input v-model="dataFrom.appSecret"></el-input>
                </el-form-item>
                <el-form-item >
                    <el-button type="primary" @click="onSubmit">更新</el-button>
                </el-form-item>
            </el-form>
        </el-main>
    </el-container>
</template>

<script>
    export default {
        data(){
            return{
                //后端数据
                dataFrom:{
                    appId:"",
                    appSecret:""
                },
                loading:false,
                //表单的校验规则
                rules: {
                    appId: [
                        { required: true, message: '请输入小程序appId', trigger: 'blur' },
                    ],
                    appSecret: [
                        { required: true, message: '请输入小程序appSecret密钥', trigger: 'blur' },
                    ],
                }
            }
        },
        created(){
            this.getData();
        },
        methods:{
            getData(){
                this.loading=true
                this.$http.get('/admin/applet/get').then(resp=>{
                    if(resp.status!=200){
                        this.loading=false
                        return
                    }
                    if(resp.data){
                        this.dataFrom = resp.data
                    }else{
                        this.$message({type:'success',message:'数据为空'})
                    }
                    this.loading=false
                }).catch(err=>{
                    this.loading=false
                })
            },
            onSubmit(){
                this.$http.post('/admin/applet/save',this.dataFrom).then(resp=>{
                    if(resp.status!=200){
                        this.$message.error(resp.msg)
                    }else{
                        this.$message({type:'success',message:resp.msg})
                    }
                }).catch(err=>{})
            },
        }
    }
</script>

<style lang="scss" scoped>
    .box-contents{
        .box-main{
            max-width: 60%;
            margin: 0 auto;
        }
    }
</style>
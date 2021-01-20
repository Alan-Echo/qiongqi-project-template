<template>
  <div class="login-container">
    <el-form ref="loginForm" :model="loginForm" :rules="loginRules" class="login-form" auto-complete="on" label-position="left">

      <div class="title-container">
        <h3 class="title">后台管理系统</h3>
      </div>

      <el-form-item prop="username">
        <span class="svg-container">
          <svg-icon icon-class="user" />
        </span>
        <el-input
          clearable
          ref="userName"
          v-model="loginForm.username"
          placeholder="请输入账号"
          name="userName"
          type="text"
          tabindex="1"
          auto-complete="on"
        />
      </el-form-item>

      <el-form-item prop="password">
        <span class="svg-container">
          <svg-icon icon-class="passWord" />
        </span>
        <el-input
          clearable
          :key="passWordType"
          ref="passWord"
          v-model="loginForm.password"
          :type="passWordType"
          placeholder="请输入密码"
          name="passWord"
          tabindex="2"
          auto-complete="on"
          @keyup.enter.native="handleLogin"
        />
        <span class="show-pwd" @click="showPwd">
          <svg-icon :icon-class="passWordType === 'passWord' ? 'eye' : 'eye-open'" />
        </span>
      </el-form-item>
      <el-form-item prop="verCode">
        <div class="ver-code">
          <div class="ver-box">
            <el-input ref="verCode" tabindex="3" name="verCode" v-model="loginForm.verCode" placeholder="请输入验证码" @keyup.enter.native="handleLogin"></el-input>
          </div>
          <div class="ver-img" @click="refVerCode" >
            <img :src="verCodeUrl"/>
          </div>
        </div>
      </el-form-item>

      <el-button :loading="loading" type="primary" style="width:100%;margin-bottom:30px;" @click.native.prevent="handleLogin">登录</el-button>

    </el-form>
  </div>
</template>

<script>

export default {
  name: 'Login',
  data() {
    const validateuserName = (rule, value, callback) => {
      if (value.length==0) {
        callback(new Error('请输入正确的用户名'))
      } else {
        callback()
      }
    }
    const validatepassWord = (rule, value, callback) => {
      if (value.length < 6) {
        callback(new Error('密码长度不小于6位'))
      } else {
        callback()
      }
    }
    const validateVerCode = (rule, value, callback) => {
      if (value.length != 4) {
        callback(new Error('验证码错误'))
      } else {
        callback()
      }
    }
    const validateTime = (rule, value, callback) => {
      if (!value.length) {
        callback(new Error())
      } else {
        callback()
      }
    }
    return {
      verCodeUrl:"",
        _t:"",
      loginForm: {
        username: '',
        password: '',
        verCode:""
      },
      loginRules: {
        username: [{ required: true, trigger: 'blur', validator: validateuserName }],
        password: [{ required: true, trigger: 'blur', validator: validatepassWord }],
        verCode: [{ required: true, trigger: 'blur', validator: validateVerCode }]
      },
      loading: false,
      passWordType: 'passWord',
      redirect: undefined
    }
  },
  watch: {
    $route: {
      handler: function(route) {
        this.redirect = route.query && route.query.redirect
      },
      immediate: true
    }
  },
  created(){
    this.refVerCode()
  },
  methods: {
    showPwd() {
      if (this.passWordType === 'passWord') {
        this.passWordType = ''
      } else {
        this.passWordType = 'passWord'
      }
      this.$nextTick(() => {
        this.$refs.passWord.focus()
      })
    },
    handleLogin() {
      this.$refs.loginForm.validate(valid => {
        if (valid) {
          this.loading = true
          this.loginForm._t=this._t
          this.$store.dispatch('user/login', this.loginForm).then(resp => {
            if(resp.status!=200){
              this.$message.error(resp.msg)
              this.loading = false
              return
            }
             this.loading = false
            this.$router.push({ path: this.redirect || '/' })
          }).catch(error => {
            this.$message.error(error)
            this.loading = false
          })
        } else {
          return false
        }
      })
    },
    refVerCode(){
      this._t=new Date().getTime()
      this.verCodeUrl=this.$bassAPI+'/app/common/verCode/imgCode/'+this._t+"?font=40&w=120&h=52"
    },
  }
}
</script>

<style lang="scss" scoped>
.login-container{
  // background-image: url('../../assets/bg.jpg');
  background-repeat: no-repeat;
  background-size: 100% 100%;
}
  .ver-code{
    height: 52px;
    .ver-box,.ver-img{
      height: 52px;
      vertical-align: top;
      display: inline-block;
    }
    .ver-img{
      float: right;
      cursor: pointer;
      img{
        width: 120px;
        height: 52px;
      }
    }

  }
</style>

<style lang="scss">
    .ver-box{
      .el-input{
        height: 65px;
        input{
          height: 65px;
        }
      }
    }
</style>

<style lang="scss">
/* 修复input 背景不协调 和光标变色 */
/* Detail see https://github.com/PanJiaChen/vue-element-admin/pull/927 */

$bg:#283443;
$light_gray:#fff;
$cursor: #fff;

@supports (-webkit-mask: none) and (not (cater-color: $cursor)) {
  .login-container .el-input input {
    color: $cursor;
  }
}

/* reset element-ui css */
.login-container {
  .el-input {
    display: inline-block;
    height: 47px;
    width: 85%;

    input {
      background: transparent;
      border: 0px;
      -webkit-appearance: none;
      border-radius: 0px;
      padding: 12px 5px 12px 15px;
      color: $light_gray;
      caret-color: $cursor;

      &:-webkit-autofill {
        // box-shadow: 0 0 0px 1000px $bg inset !important;
        -webkit-text-fill-color: $cursor !important;
      }
    }
  }

  .el-form-item {
    border: 1px solid rgba(255, 255, 255, 0.1);
    background: rgba(0, 0, 0, 0.1);
    border-radius: 5px;
    color: #454545;
  }
}
</style>

<style lang="scss" scoped>
$bg:#2d3a4b;
$dark_gray:#889aa4;
$light_gray:#eee;

.login-container {
  min-height: 100%;
  width: 100%;
  background-color: $bg;
  overflow: hidden;

  .login-form {
    position: relative;
    width: 520px;
    max-width: 100%;
    padding: 160px 35px 0;
    margin: 0 auto;
    overflow: hidden;
  }

  .tips {
    font-size: 14px;
    color: #fff;
    margin-bottom: 10px;

    span {
      &:first-of-type {
        margin-right: 16px;
      }
    }
  }

  .svg-container {
    padding: 6px 5px 6px 15px;
    color: $dark_gray;
    vertical-align: middle;
    width: 30px;
    display: inline-block;
  }

  .title-container {
    position: relative;

    .title {
      font-size: 26px;
      color: $light_gray;
      margin: 0px auto 40px auto;
      text-align: center;
      font-weight: bold;
    }
  }

  .show-pwd {
    position: absolute;
    right: 10px;
    top: 7px;
    font-size: 16px;
    color: $dark_gray;
    cursor: pointer;
    user-select: none;
  }
}
</style>

<style>
  input:-webkit-autofill , textarea:-webkit-autofill, select:-webkit-autofill {
    -webkit-text-fill-color:transparent !important;
    /* -webkit-box-shadow: 0 0 0px 1000px transparent  inset !important; */
      background-color:transparent;
      background-image: none;
      /* //背景色透明  生效时长  过渡效果  启用时延迟的时间 */
      transition: background-color 50000s ease-in-out 0s; 
  }
  input {
    background-color:transparent;
  }
  .login-container /deep/ .el-input /deep/ input:-webkit-autofill{
    /* -webkit-box-shadow: none;
    box-shadow: none;
    -webkit-text-fill-color:none; */
  }
</style>

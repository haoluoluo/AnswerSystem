<template>
  <div class="login-form" >
    <header>
      <h1 class="login-h1">Hi,尊贵的用户！</h1>
      <p>欢迎登录答题系统</p>
    </header>
    <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" class="demo-ruleForm" label-position="left"  @submit.native.prevent>
      <el-form-item label="" prop="email">
        <el-input type="text" v-model="ruleForm.email" autocomplete="off" placeholder="邮箱"></el-input>
      </el-form-item>
      <el-form-item label="" prop="pass">
        <el-input type="password" v-model="ruleForm.pass" autocomplete="off" placeholder="密码"></el-input>
      </el-form-item>
      <div class="login-form-link">
        <router-link :to="{ name: 'register'}">注册新账号</router-link>
        <router-link :to="{ name: 'forgotpassword'}">忘记密码?</router-link>
      </div>
      <el-button type="primary" native-type="submit" v-bind:disabled="siDisabled" @keyup.enter.native="submitForm('ruleForm')" v-on:click="submitForm('ruleForm')" style="width:100%;">{{message}}</el-button>
    </el-form>
  </div>
</template>
<script>
// const md5 = require('md5-node')
export default {
  data () {
    // 验证手机号 邮箱
    let checkPhone = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('邮箱不能为空'))
      } else {
        // const regTel = /^(\+?86)?1[0-9]\d{9}$/
        const regEmail = /^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\.[a-zA-Z0-9-]+)*\.[a-zA-Z0-9]{2,6}$/
        // 验证手机
        // if (regTel.test(value) || regEmail.test(value)) {
        if (regEmail.test(value)) {
          callback()
        } else {
          return callback(new Error('邮箱格式不正确'))
        }
      }
    }
    return {
      message: '立即登录',
      siDisabled: false,
      ruleForm: {
        email: '',
        pass: ''
      },
      rules: {
        email: [{ validator: checkPhone, trigger: ['blur', 'change'], required: true }],
        pass: [{required: true, message: '密码不能为空', trigger: ['blur', 'change']}]
      }
    }
  },
  methods: {
    submitForm (formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          this.message = '正在登录...'
          this.siDisabled = true
          // let data = { account: this.ruleForm.email, pwd: md5(this.ruleForm.pass) }
          let data = { account: this.ruleForm.email, pwd: this.ruleForm.pass }

          this.axios.post('/api/user/login', this.$qs.stringify(data))
            .then(jsondata => { // 成功
              if (jsondata.data.code == 200) {
                this.siDisabled = false
                let data = jsondata.data.data
                localStorage.setItem('email', data.email)
                localStorage.setItem('userId', data.userno)
                localStorage.setItem('token', data.userno)
                sessionStorage.setItem('email',data.email);
                sessionStorage.setItem('userId',data.userno);
                sessionStorage.setItem("token",data.userno);
                // 登录成功 - 跳转
                this.$router.push({path: '/home'})
              } else {
                this.$message.error(jsondata.data.hint)
                this.message = '立即登录'
                this.siDisabled = false
              }
            })
            .catch(e => { // 失败
              this.$message.error('服务器异常，请稍后再试！')
              this.siDisabled = false
              this.message = '立即登录'
            })
        } else {
          return false
        }
      })
    }
  }
}
</script>
<style lang="scss">
  .login-form{
    .el-form-item{margin-bottom:35px;}
    .el-input__inner{height:49px;line-height:49px;}
    .el-input__inner::-webkit-input-placeholder{font-size:14px;}
    .el-button{cursor: pointer;height:45px;font-size:16px;}
  }
</style>

<style lang="scss" scoped>
  header{padding-bottom:.3rem;
    h1{font-weight:normal;}
    p{font-size:16px;padding:.2rem 0;}
  }
  .login-form {
    width: 17rem;
    margin:0 auto;
    a{color:#409eff;cursor: pointer}
  }
  .login-h1 {
    margin-bottom: 0.2rem;
    color: #1f2f3d;
  }
  .login-form-link {
    height: 0.2rem;
    line-height: 0.2rem;
    margin-bottom: 1.3rem;
    a:nth-of-type(1){
      float: left;
    };
    a:nth-of-type(2) {
      float: right;
    }
  }
  @media screen and (max-width: 1080px){
    .login-form{width:90%;position:relative;}
  }
</style>

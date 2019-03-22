<template>
  <div class="register">
        <div class="register-wrapp">
            <header>
                <h1 class="register-h1">欢迎注册</h1>
            </header>
            <!--注册第一步 手机、邮箱-->
            <div class="registerInfo">
                <el-row :gutter="80">
                    <el-col :xs="24" :sm="16" :md="16" :lg="16" :xl="16" class="registerLine">
                        <div class="grid-content bg-purple">
                            <div class="login-form tell-form">
                                <el-form :model="registerForm" :rules="rulesRegForm" ref="registerForm" status-icon label-width="100px">
                                   <el-form-item label="用户名：" prop="username">
                                        <el-input type="text" v-model="registerForm.username" :clearable="true" autocomplete="off"  placeholder="用户名"></el-input>
                                    </el-form-item>
                                    <el-form-item label="邮箱：" prop="email">
                                        <el-input type="text" v-model="registerForm.email" :clearable="true" autocomplete="off"  placeholder="邮箱"></el-input>
                                    </el-form-item>
                                    <el-form-item label="设置密码：" prop="password">
                                        <el-input type="password" v-model="registerForm.password" autocomplete="off" placeholder="请设置密码"></el-input>
                                    </el-form-item>
                                    <el-form-item label="确认密码：" prop="checkPassword" style="margin-bottom:15px;">
                                        <el-input type="password" v-model="registerForm.checkPassword" autocomplete="off" placeholder="请确认密码"></el-input>
                                    </el-form-item>
                                    <el-form-item label="">
                                        <label>
                                            <el-checkbox @change="handleCheckChange" v-model="checked"></el-checkbox> 我已确认并同意
                                        </label> <a href="javascript:;">《答题系统服务协议》</a>
                                    </el-form-item>
                                    <el-form-item label="" label-width="0">
                                        <el-button type="primary" :disabled="isDisabled" style="width:100%;" @keyup.enter.native="gettellphoneCode('registerForm')" v-on:click="gettellphoneCode('registerForm')">下一步</el-button>
                                    </el-form-item>
                                </el-form>
                            </div>
                        </div>
                    </el-col>
                    <el-col class="text-center" :xs="24" :sm="8" :md="8" :lg="8" :xl="8">
                        <div class="grid-content bg-purple-light">
                            <router-link :to="{ path: '/login'}">已有账号？去登录</router-link>
                        </div>
                    </el-col>
                </el-row>
            </div>
            <!--&lt;!&ndash;注册第二步 邮箱验证码&ndash;&gt;-->
            <div class="login-form tellCode">
                <el-form :model="codeForm" :rules="rulesCodeForm" ref="codeForm" status-icon label-width="120px">
                    <!--<el-form-item label="微主页名称：" prop="micPageName">-->
                        <!--<el-input type="text" v-model="codeForm.micPageName" autocomplete="off" placeholder="微主页名称"></el-input>-->
                    <!--</el-form-item>-->
                    <p class="register-p">验证码已发送到邮箱：<em class="red">{{registerForm.email}}</em></p>
                    <el-form-item label="验证码：" prop="code">
                        <el-input type="code" v-model="codeForm.code" maxlength="6" onkeyup='this.value=this.value.replace(/\D/gi,"")' autocomplete="off" placeholder="验证码"></el-input>
                    </el-form-item>
                    <el-form-item label="" align="center">
                        <el-button type="primary" style="width:100%;" @keyup.enter.native="register('codeForm')" v-on:click="register('codeForm')">立即注册</el-button>
                        <el-button style="width:100%;" @click="prev">上一步</el-button>
                    </el-form-item>
                </el-form>
            </div>
        </div>
  </div>
</template>
<script>
import $ from 'jquery'

const md5 = require('md5-node')
export default {
  data () {
    // 验证手机号
    let checkPhone = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('请输入邮箱'))
      } else {
        // const regTel = /^(\+?86)?1[0-9]\d{9}$/
        const regEmail = /^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\.[a-zA-Z0-9-]+)*\.[a-zA-Z0-9]{2,6}$/
        if (regEmail.test(value)) {
          callback()
        } else {
          return callback(new Error('请输入正确的邮箱'))
        }
      }
    }
    // 验证密码
    let valPassword = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请设置密码'))
      } else {
        if (this.registerForm.checkPassword !== '') {
          this.$refs.registerForm.validateField('checkPassword')
        }
        callback()
      }
    }
    // 验证密码
    let valCheckPassword = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请确认密码'))
      } else if (value !== this.registerForm.password) {
        callback(new Error('两次输入密码不一致!'))
      } else {
        callback()
      }
    }
    return {
      uuid: '', // 注册必须参数uuid
      checked: false, // 是否同意注册协议 否：注册提交按钮不可用
      isDisabled: true, // 注册提交按钮是否可用
      // 步骤1
      registerForm: {
        username: '',
        email: '', // 手机号
        password: '', // 设置密码
        checkPassword: '' // 确认密码
      },
      rulesRegForm: {
        username: [{required: true,trigger: 'blur', message:'用户名不能为空'}],
        email: [{ validator: checkPhone, trigger: ['blur', 'change'], required: true }],
        password: [{ validator: valPassword, trigger: ['blur', 'change'], required: true }],
        checkPassword: [{ validator: valCheckPassword, trigger: ['blur', 'change'], required: true }]
      },
      //步骤2
      codeForm: {
        micPageName: '',
        code: ''
      },
      rulesCodeForm: {
        micPageName: [{required: true, message: '微主页名称不能为空', trigger: 'blur'}],
        code: [{ required: true, message: '验证码不能为空' }]
      }
    }
  },
  mounted () {
  },
  methods: {
    handleCheckChange () {
      // 判断是否同意注册协议
      if (!this.checked) { // 不同意
        this.isDisabled = true
      } else {
        this.isDisabled = false
      }
    },
    gettellphoneCode: function (formName) {
      // 注册验证
      this.$refs[formName].validate(valid => {
        if (valid) {
          // 禁用注册按钮
          this.isDisabled = true
          let data = {username: this.registerForm.username, account: this.registerForm.email, pwd: this.registerForm.password}
          this.axios.post('/api/user/registerStep1', this.$qs.stringify(data))
            .then(jsondata => {
              if (jsondata.data.code == 200) {
                $('.registerInfo').hide()
                $('.tellCode').show()
                // 获取uuid
                this.uuid = jsondata.data.data
                this.$message.info(jsondata.data.hint)
              } else if (jsondata.data.code == 400) {
                this.$message.warning(jsondata.data.hint)
              } else {
                this.$message.error(jsondata.data.hint)
              }
              // 解除禁用
              this.isDisabled = false
            })
            .catch(e => { // 失败
              this.$message.error('服务器异常，请稍后再试！')
              this.isDisabled = false
              console.log(e)
            })
        } else {
          return false
        }
      })
    },
    resetForm (formName) {
      this.$refs[formName].resetFields()
    },
    // 注册第二步
    register (formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          let data = { account: this.registerForm.tellphone, pwd: md5(this.registerForm.password) }
          this.axios.get('/api/user/registerStep2?code=' + this.codeForm.code + '&uuid=' + this.uuid, this.$qs.stringify(data))
            .then(jsondata => { // 成功
              if (jsondata.data.code == 200) {
                this.$message.success(jsondata.data.hint)
                // 成功跳转到登录
                this.$router.push({path: '/iLogin/login'})
              } else if (jsondata.data.code === 500) {
                this.$message.error(jsondata.data.hint)
              } else if (jsondata.data.code == 400) {
                this.$message.warning(jsondata.data.hint)
              }
            })
            .catch(e => { // 失败
              this.$message.error('服务器异常，请稍后再试！')
              console.log(e)
            })
        } else {
          return false
        }
      })
    },
    // 返回上一步
    prev: function () {
      $('.registerInfo').show()
      $('.tellCode').hide()
    }
  }
}
</script>
<style lang="scss">
    .register-wrapp{
        .el-tabs__nav{width:100%;text-align:center;}
        .el-tabs__item{width:50%;font-size:16px;}
        .el-tabs__active-bar{height:1px;}
        .el-tabs__nav-wrap::after{height:1px;}
        .input-with-select .el-input-group__prepend { background-color: #fff; }
        .el-input-group__append, .el-input-group__prepend{background:none;}
        .el-tabs__header{margin-bottom:50px;}
        .el-row{margin:0!important;
            .el-col:last-child{padding:0!important;}
            .registerLine{padding:0 50px 0 0!important;}
        }
        .el-button+.el-button{margin:20px 0 0}
    }
</style>
<style lang="scss" scoped>
  header{padding-bottom:.6rem;
    h1{font-weight:normal;}
  }
.register {
  width: 100%;
  height: 100%;
  position:relative;
  box-sizing: border-box;
}
.register-wrapp{
    width:767px;
    margin:0 auto;
    .registerLine{border-right:solid 1px #EEE;}
    .el-row{width:100%;}
    .tellCode{width:560px;margin:0 auto;display:none;}
}
a{color:#409eff;cursor: pointer}
.register-p {
  margin-bottom: 0.2rem;
  line-height: 1.5;
    text-align: center;
  color: #1f2f3d;
  span {
    margin: 0 0.05rem;
    color: #409eff;
  }
}
.register-form-link {
  height: 0.2rem;
  line-height: 0.2rem;
  margin-bottom: 0.2rem;
  a:nth-of-type(2) {
    float: right;
  }
}
.register-form2{
  display: none;
}
label{cursor:pointer;}
@media screen and (max-width:768px){
  .register-wrapp{width:80%;
      .el-row{margin:0!important;
        .el-col{padding:0!important;}
      }
      header{text-align: center}
      .registerLine{border:0;}}
}
@media screen and (max-width:567px){
  .register-wrapp{width:90%;}
}
</style>

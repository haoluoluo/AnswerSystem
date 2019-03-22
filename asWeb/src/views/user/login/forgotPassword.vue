<template>
  <div class="forgot">
    <div class="forgot-wrapp">
      <header>
        <h1 class="forgot-h1">{{fogotH1}}</h1>
      </header>
      <div class="forgotInfo">
      <!--邮箱、手机找回密码-->
        <el-tabs v-model="activeName">
          <!--<el-tab-pane name="tellGet">-->
            <!--<span slot="label"><i class="el-icon-mobile-phone"></i> 手机号码找回</span>-->
            <!--<div class="login-form tell-form">-->
              <!--<el-form :model="tellForm" :rules="rulesTellForm" ref="tellForm" status-icon label-width="100px">-->
                <!--<el-form-item label="手机号码：" prop="tellphone">-->
                  <!--<el-input type="text" v-model="tellForm.tellphone" maxlength="11" onkeyup='this.value=this.value.replace(/\D/gi,"")' autocomplete="off" placeholder="请输入手机号码"></el-input>-->
                <!--</el-form-item>-->
                <!--<el-button type="primary" @click="tellSubForm('tellForm')" style="width:100%;">下一步</el-button>-->
                <!--<div class="login-form-link text-center">-->
                  <!--<router-link :to="{ path: '/ilogin/login'}">放弃找回？去登录</router-link>-->
                <!--</div>-->
              <!--</el-form>-->
            <!--</div>-->
          <!--</el-tab-pane>-->

          <el-tab-pane name="emaillGet">
            <span slot="label"><i class="el-icon-message"></i>  邮箱找回</span>
            <div class="login-form">
              <el-form :model="emailForm" status-icon :rules="rulesEmailForm" ref="emailForm" label-width="100px">
                <el-form-item label="邮箱地址：" prop="email">
                  <el-input type="email" v-model="emailForm.email" autocomplete="off" placeholder="请输入注册邮箱"></el-input>
                </el-form-item>
                <el-button type="primary" @click="emailSubForm('emailForm')" style="width:100%;">下一步</el-button>
                <div class="login-form-link text-center">
                  <router-link :to="{ path: '/ilogin/login'}">放弃找回？去登录</router-link>
                </div>
              </el-form>
            </div>
          </el-tab-pane>
        </el-tabs>
      </div>
      <!--手机找回 验证码-->
      <!--<div class="forgot-tell">-->
        <!--<p class="forgot-p">验证码已发送到手机号：<em class="red">{{tellForm.tellphone}}</em></p>-->
        <!--<el-form :model="tellFindReForm" :rules="rulesTellFindReForm" ref="tellFindReForm" class="login-form" status-icon label-width="100px">-->
          <!--<el-form-item label="验证码：" prop="code">-->
            <!--<el-input type="text" v-model="tellFindReForm.code" maxlength="6" autocomplete="off" placeholder="验证码"></el-input>-->
          <!--</el-form-item>-->
          <!--<el-form-item label="设置密码：" prop="newPwd">-->
            <!--<el-input type="password" v-model="tellFindReForm.newPwd" autocomplete="off" placeholder="设置密码"></el-input>-->
          <!--</el-form-item>-->
          <!--<el-form-item label="确认密码：" prop="cfmPwd">-->
            <!--<el-input type="password" v-model="tellFindReForm.cfmPwd" autocomplete="off" placeholder="确认密码"></el-input>-->
          <!--</el-form-item>-->
          <!--<el-button type="primary" @click="tellResetForm('tellFindReForm')" style="width:100%;">立即重置</el-button>-->
        <!--</el-form>-->
        <!--<div class="login-form-link text-center">-->
          <!--<router-link :to="{ path: '/ilogin/login'}">密码已重置？去登录</router-link>-->
        <!--</div>-->
      <!--</div>-->

      <!--邮箱找回 发送链接-->
      <div class="forgot-email">
        <p class="forgot-p">更改密码的链接已经发送到注册邮箱中，请去点击链接重置密码。</p>
        <div class="login-form-link text-center">
          <router-link :to="{ path: '/ilogin/login'}">密码已重置？去登录</router-link>
        </div>
      </div>

    </div>
  </div>
</template>
<script>
const md5 = require('md5-node');
export default {
  data() {
      // 验证手机号
      let checkPhone = (rule, value, callback) => {
          if (!value) {
              return callback(new Error('请输入手机号'));
          } else {
              const regTel = /^(\+?86)?1[0-9]\d{9}$/;
              //验证手机
              if (regTel.test(value)) {
                  callback();
              } else {
                  return callback(new Error('手机号格式不正确'));
              }
          }
      };
      let checkEmail = (rule, value, callback) => {
          if (!value) {
              return callback(new Error('请输入邮箱地址'));
          } else {
              const regEmail = /^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\.[a-zA-Z0-9-]+)*\.[a-zA-Z0-9]{2,6}$/;
              //验证邮箱
              if (regEmail.test(value)) {
                  callback();
              } else {
                  return callback(new Error('邮箱格式不正确'));
              }
          }
      };

      // 验证密码
      let valPwd = (rule, value, callback) => {
          if (value === "") {
              callback(new Error("请设置密码"))
          } else {
              if (this.tellFindReForm.newPwd !== "") {
                  this.$refs.tellFindReForm.validateField("cfmPwd")
              }
              callback()
          }
      };
      // 验证密码
      let valCheckPwd = (rule, value, callback) => {
          if (value === "") {
              callback(new Error("请确认密码"))
          } else if (value !== this.tellFindReForm.newPwd) {
              callback(new Error("两次输入密码不一致!"))
          } else {
              callback();
          }
      };
      return {
          //手机找回验证
          tellForm:{
              tellphone:''
          },
          rulesTellForm:{
              tellphone:[{validator: checkPhone, trigger: ["blur", "change"], required: true }]
          },
          //邮箱找回验证
          emailForm:{
              email:''
          },
          rulesEmailForm:{
              email: [{ validator: checkEmail, trigger: ["blur", "change"], required: true }]
          },
          //手机找回重置密码
          tellFindReForm:{
                code:'',
                newPwd:'',
                cfmPwd:''
          },
          rulesTellFindReForm:{
              code:[{ required: true, message: '请输入手机验证码', trigger: 'blur' }],
              newPwd: [{ validator: valPwd, trigger: ["blur", "change"], required: true }],
              cfmPwd: [{ validator: valCheckPwd, trigger: ["blur", "change"], required: true }]
          },

          activeName: 'emaillGet',
          token:'',

          //
          fogotH1:'找回密码'
      }
  },
  methods: {
      /**
       * @api {get} /customer/findPwdStep1 找回密码第一步
       * @apiDescription 找回密码第一步，发送修改密码地址到客户填写的邮件地址
       * @apiGroup customer
       * @apiParam {Integer}   type   Y 找回密码所用的类型 （10-用电话号码找回，20-用邮箱找回）
       * @apiParam {String}   account   Y 账号
       * @apiVersion 1.0.0
       * @apiUse REQUEST_SUCCESS
       * @apiUse REQUEST_FAIL
       */
      //手机找回
      tellSubForm(formName) {
          this.$refs[formName].validate((valid) => {
              if (valid) {
                  let data = { account: this.tellForm.tellphone,type:10};
                  this.axios.get('/api/user/findPwdStep1', {params:data})
                      .then(jsondata => { //成功
                          if (jsondata.data.code == 200) {
                              $('.forgotInfo').hide();
                              $('.forgot-tell').show();
                              this.fogotH1 = '找回密码 - 手机';
                              //获取token
                              this.token = jsondata.data.hint;
                              this.$message.success('验证码发送成功')
                          } else{
                              this.$message.error(jsondata.data.hint);
                          }
                      })
                      .catch(e => { //失败
                          console.log(e)
                      })
              } else {
                  return false
              }
          })
      },
      emailSubForm(formName) {
          this.$refs[formName].validate((valid) => {
              if (valid) {
                  let data = { account: this.emailForm.email,type:20 };
                  this.axios.get('/api/user/findPwdStep1', {params:data})
                      .then(jsondata => { //成功
                          if (jsondata.data.code == 200) {
                              $('.forgotInfo').hide();
                              $('.forgot-email').show();
                              this.fogotH1 = '找回密码 - 邮箱';
                              this.$message.success(jsondata.data.hint)
                          } else{
                              this.$message.error(jsondata.data.hint);
                          }
                      })
                      .catch(e => { //失败
                          this.$message.error('服务器异常，请稍后再试！');
                          console.log(e)
                      })
              } else {
                  return false
              }
          })
      },
      tellResetForm(formName) {
          this.$refs[formName].validate((valid) => {
              if (valid) {
                  let data = {token: this.token, pwd: md5(this.tellFindReForm.newPwd)};
                  this.axios.post('/customer/findPwdStep2', this.$qs.stringify(data))
                      .then(jsondata => { //成功
                          if (jsondata.data.code == 200) {
                              this.$message.success('密码重置成功！');
                              this.$router.push({path: '/ilogin/login'})
                          } else {
                              this.$message.error('密码重置失败！')
                          }
                      })
                      .catch(e => { //失败
                          this.$message.error('服务器异常，请稍后重试！');
                          console.log(e)
                      })
              } else {
                  return false;
              }
          });
      }
  }
}
</script>
<style lang="scss">
  .forgot-wrapp{
    .el-tabs__nav{width:100%;text-align:center;}
    .el-tabs__item{width:50%;font-size:16px;}
    .el-tabs__active-bar{height:1px;}
    .el-tabs__nav-wrap::after{height:1px;}
    .input-with-select .el-input-group__prepend { background-color: #fff; }
    .el-input-group__append, .el-input-group__prepend{background:none;}
    .el-tabs__header{margin-bottom:50px;}
  }
</style>

<style lang="scss" scoped>
  .forgot-wrapp{
    width: 20rem;
    margin:0 auto;
    .forgotInfo{
      .el-form-item{margin-bottom:100px;}
    }
    .forgot-tell,.forgot-email{display:none;}
  }
.forgot {
  width: 100%;
  height: 100%;
}
.forgot-form,
.forgot-form2 {
  width: 4.8rem;
  margin:0 auto;
}
.login-form-link {
  height: 4.2rem;
  line-height: 0.2rem;
  margin-top: 1.2rem;
  a{color:#409eff;cursor: pointer; padding:0.2rem 0;}
}
.forgot-h1 {
  margin-bottom: 0.5rem;
  font-weight:normal;
  color: #1f2f3d;
}
.forgot-p {
  margin-bottom: 0.2rem;
  line-height: 1.5;
  color: #1f2f3d;
  text-align:center;
}
.forgot-form2 {
  display: none;
}
@media screen and (max-width: 479px){
  .forgot-form,
  .forgot-form2{width:90%;position:relative;margin:0 auto;}
}
</style>

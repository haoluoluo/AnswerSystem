<template>
  <div class="register">
    <div class="login-form register-form">
      <el-form :model="registerForm" status-icon :rules="registerRules" ref="registerForm">
        <h1 class="register-h1">重置密码</h1>
        <el-form-item label="" prop="pass">
          <el-input type="password" v-model="registerForm.pass" autocomplete="off" placeholder="设置密码"></el-input>
        </el-form-item>
        <el-form-item label="" prop="checkPass">
          <el-input type="password" v-model="registerForm.checkPass" autocomplete="off" placeholder="确认密码"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitForm('registerForm')" style="width: 100%">立即重置</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>
<script>
const md5 = require('md5-node');
export default {
  data() {
    let validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'));
      } else {
        if (this.registerForm.checkPass !== '') {
          this.$refs.registerForm.validateField('checkPass');
        }
        callback();
      }
    };
    let validatePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'));
      } else if (value !== this.registerForm.pass) {
        callback(new Error('两次输入密码不一致!'));
      } else {
        callback();
      }
    };
    return {
      registerForm: {
        pass: '',
        checkPass: '',
      },
      registerRules: {
        pass: [
          { validator: validatePass, trigger: 'blur', required: true, }
        ],
        checkPass: [
          { validator: validatePass2, trigger: 'blur', required: true, }
        ]
      }
    }
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
            let token = this.$route.query.token;
            // let data = { token: token, pwd: md5(this.registerForm.pass) };
          let data = { token: token, pwd: this.registerForm.pass };
            this.axios.post('/api/user/findPwdStep2', this.$qs.stringify(data))
            .then(jsondata => { //成功
                if (jsondata.data.code == 200) {
                    this.$message.success('密码重置成功！');

                    this.$router.push({path: '/ilogin/login'})
                } else{
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
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    }
  }
}
</script>
<style lang="scss" scoped>
.register {
  width: 100%;
  height: 100%;
}
.register-form {
  width: 20.8rem;
  padding: 0.4rem 0;
  margin:0 auto;
}
.register-h1 {
  margin-bottom: 0.4rem;
  font-weight:normal;
  color: #1f2f3d;
}
.register-p {
  margin-bottom: 0.2rem;
  line-height: 1.5;
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
@media screen and (max-width: 479px){
  .register-form{width:90%;position:relative;margin:0 auto;}
}
</style>

<template>
  <div class="content">
    <div class="loginArea">
    <h1>答题系统管理后台</h1>
    <el-form style="margin-top: .2rem;" :model="formModel" :rules="rules" ref="formModel" label-width="80px">
    <el-form-item label="用户名" prop="userName">
      <el-input size="small" v-model="formModel.userName"></el-input>
    </el-form-item>
    <el-form-item label="密码" prop="password">
      <el-input type="password" v-model="formModel.password"></el-input>
    </el-form-item>
      <el-button @click="submitForm('formModel')" @keyup.enter.native="submitForm('formModel')" type="success">登录</el-button>
  </el-form>
    </div>
  </div>
</template>

<script>
export default {
  name: 'login',
  data () {
    return {
      formModel: {
        userName: '',
        password: ''
      },
      rules: {
        userName: [
          { required: true, message: '请输入用户名', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    submitForm (formName) {
      var self = this
      this.$refs[formName].validate((valid) => {
        if (valid) {
          var param = {
            userName: self.formModel.userName,
            pwd: self.formModel.password
          }
          self.webrequest('post-form', '/admins/login', param, function (data) {
            // self.$cookieHandle.setCookie('adminId', data.id, 7)
            self.$router.push({ name: 'businessMgr', path: '/admin/businessMgr' })
          }, function (error) {
            self.$message({
              showClose: true,
              message: error.hint,
              type: 'error'
            })
          })
        } else {
          return false
        }
      })
    },
    login: function () {

    }
  }
}
</script>

<style scoped>
.content{
  color: #fff;
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
}
  .loginArea{
    background-color: #409EFF;
    padding: 20px;
  }
</style>

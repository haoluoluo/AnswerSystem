<template>
  <el-form style="margin-top: .2rem;max-width: 24rem;" :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px">
    <el-form-item label="考生名" prop="username">
      <el-input v-model="ruleForm.username"></el-input>
    </el-form-item>
    <el-form-item label="考生密码" prop="password">
      <el-input type="type" v-model="ruleForm.password"></el-input>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="submitForm('ruleForm')" v-html="buttonName"></el-button>
      <el-button @click="resetForm('ruleForm')">重置</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
export default {
  name: 'userUpdate',
  data () {
    return {
      buttonName: '立即添加',
      ruleForm: {
        username: '',
        password: ''
      },
      rules: {
        username: [
          { required: true, message: '请输入用户名', trigger: 'blur' }
          // { type:'email', message: '请输入正确的邮箱', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请填密码', trigger: 'blur'}
          // {min:6,max:16,message:'请填写6-16位密码',trigger:'blur'}
        ]
      }
    }
  },
  created: function () {
    if (this.$route.params.userDetail) {
      this.buttonName = '更新'
      this.ruleForm = this.$route.params.userDetail
    }
  },
  methods: {
    submitForm (formName) {
      var self = this
      this.$refs[formName].validate((valid) => {
        if (valid) {
          // 提交表单
          if (this.buttonName == '立即添加') {
            self.webrequest('put', '/user/addUserInfo', self.ruleForm, function (data) {
              self.$router.back()
            }, function (error) {

            })
          } else if (this.buttonName == '更新') {
            self.webrequest('post', '/user/updateUserInfo', self.ruleForm, function (data) {
              self.$router.back()
            }, function (error) {

            })
          }
        } else {
          return false
        }
      })
    },
    resetForm (formName) {
      this.$refs[formName].resetFields()
    },
    goBack: function () {
      this.$router.back()
    }
  }
}
</script>

<style scoped>

</style>

<template>
  <el-form style="margin-top: .2rem;max-width: 24rem;" :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px">
    <el-form-item label="考生名" prop="username">
      <el-input v-model="ruleForm.username"  :clearable="true"></el-input>
    </el-form-item>
    <!--<el-form-item label="邮箱：" prop="email">-->
      <!--<el-input type="text" v-model="ruleForm.email" :clearable="true" autocomplete="off"  placeholder="邮箱"></el-input>-->
    <!--</el-form-item>-->
    <!--<el-form-item label="设置密码：" prop="password">-->
      <!--<el-input type="password" v-model="registerForm.password" autocomplete="off" placeholder="请设置密码"></el-input>-->
    <!--</el-form-item>-->
    <!--<el-form-item label="确认密码：" prop="checkPassword" style="margin-bottom:15px;">-->
      <!--<el-input type="password" v-model="registerForm.checkPassword" autocomplete="off" placeholder="请确认密码"></el-input>-->
    <!--</el-form-item>-->
    <el-form-item>
      <el-button type="primary" @click="submitForm('ruleForm')" v-html="buttonName"></el-button>
      <el-button @click="resetForm('ruleForm')">重置</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
  export default {
    name: "update-business",
    data(){
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
      return{
        buttonName:'更新',
        ruleForm:{
          userno:sessionStorage.getItem("userId"),
          username:'',
          email:'',
          password:'',
        },
        rules: {
          username: [
            { required: true, message: '请输入用户名', trigger: 'blur' }
          ],
          password: [
            { required: true, message: '请填密码',trigger: 'blur'}
          ],
          email: [{ validator: checkPhone, trigger: ['blur', 'change'], required: true }],
        }
      }
    },
     mounted:function () {
        this.getUserInfo()
      },
    methods: {

        getUserInfo:function () {
          var self = this
          var param = {
            userno:this.ruleForm.userno
          }
          this.webrequest('get','/user/userInfo/'+param.userno,null,function (data) {
            self.ruleForm= data
          },function (error) {

          });
        },
      submitForm(formName) {
        var self = this
        this.$refs[formName].validate((valid) => {
          if (valid) {
            //提交表单
              self.webrequest('post','/user/updateUserInfo',self.ruleForm,function (data) {
                self.$message({
                  message: '恭喜你，更新成功',
                  type: 'success'
                });
                self.$router.back()
              },function (error) {

              });
          } else {
            return false;
          }
        });
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      },
      goBack:function () {
        this.$router.back()
      }
    }
  }
</script>

<style scoped>

</style>


<template>
  <div class="contenet">
    <div class="top">
       <el-button type="primary" icon="el-icon-arrow-left" size="small" @click="goBack">返回</el-button>
    </div>
    <el-form style="margin-top: .2rem;" :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px">
      <el-form-item label="问题名称" prop="title">
        <el-input v-model="ruleForm.title"></el-input>
      </el-form-item>
      <el-form-item label="问题分数" prop="score">
        <el-input v-model="ruleForm.score"></el-input>
      </el-form-item>
      <el-form-item v-for="(lett, index) in ruleForm.items" :label="(letter[index])" :key="lett.id" :prop="'lett.'+ index +'.name'">
        <el-input v-model="lett.name" ></el-input>
      </el-form-item>
       <el-form-item label="答案" prop="answer" >
        <el-select :v-model="ruleForm.answer" placeholder="请选择正确答案" :value="ruleForm.answer">
          <!--<el-option v-for="(sel, index) in ruleForm.items" :label="letter[index]" :value="index"></el-option>-->
          <el-option label="A" :value="1" ></el-option>
          <el-option label="B" :value="2"></el-option>
          <el-option label="C" :value="4"></el-option>
          <el-option label="D" :value="8"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="理由" prop="reason">
      <el-input v-model="ruleForm.reason"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" v-html="buttonTitle" @click="submitForm('ruleForm')"></el-button>
        <el-button @click="resetForm('ruleForm')">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
    export default {
        name: "singlequestion",
      data() {
        let checkNum = (rule, value, callback) => {
          if (!value) {
            return callback(new Error('不用为空'))
          } else {
            const regNum = /^[0-9]+$/
            // 验证手机
            if (regNum.test(value)) {
              callback()
            } else {
              return callback(new Error('必须为数字'))
            }
          }
        }
        return {
           buttonTitle:'立即创建',
          letter: ['A', 'B', 'C', 'D'],
          ruleForm: {
            id: '',
            title: '',
            score: 1,
            items: [{},{},{},{}],
            answer: 1 ,
            reason: '' ,
            examination: this.$route.query.examid,
            type: 1
          },
          rules: {
            title: [
              { required: true, message: '请输入题目名称', trigger: 'blur' },
              { min: 2, max: 100, message: '长度在 3 到 100 个字符', trigger: 'blur' }
            ],
            answer:[
              {required: true,message:"请选择", trigger: 'change'}
            ],
            score: [
              { validator: checkNum, trigger: ['blur', 'change'], required: true }
              ]
          }
        };
      },
        mounted:function () {
            var tempObj = this.$route.params?this.$route.params.questionDetail:null
            if(tempObj){
                this.buttonTitle = '提交修改';
                this.ruleForm.title = tempObj.title;
                this.ruleForm.score = tempObj.score;
                this.ruleForm.items = tempObj.items;
                this.ruleForm.answer = parseInt(tempObj.answer);
                this.ruleForm.reason = tempObj.reason;
                this.ruleForm.id = tempObj.id;
                this.ruleForm.examination = tempObj.examination
            }else{

            }
        },
      methods: {
        submitForm(formName) {
          var self = this
          this.$refs[formName].validate((valid) => {
            if (valid) {
                if(self.buttonTitle == "提交修改"){
                    //提交表单
                    self.webrequest('post','/question/updateQuestion',self.ruleForm,function (data) {
                        self.$router.back()
                    },function (error) {

                    });
                }else{
                    //提交表单
                    self.webrequest('put','/question/addQuestion',self.ruleForm,function (data) {
                        self.$router.back()
                    },function (error) {

                    });
                }

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
  .contenet{
    width: 100%;
    height: 100%;
    text-align: left;
    max-width: 500px;
  }
  .top{
    margin-top: .2rem;
    text-align: left;
  }
</style>

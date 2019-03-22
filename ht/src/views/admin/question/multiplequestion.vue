<template>
  <div class="contenet">
    <div class="top">
      <el-button
        type="primary"
        icon="el-icon-arrow-left"
        size="small"
        @click="goBack"
        >返回</el-button
      >
    </div>
    <el-form
      style="margin-top: .2rem;"
      :model="ruleForm"
      :rules="rules"
      ref="ruleForm"
      label-width="100px"
    >
      <el-form-item label="问题名称" prop="title">
        <el-input v-model="ruleForm.title"></el-input>
      </el-form-item>
      <el-form-item label="问题分数" prop="score">
        <el-input v-model="ruleForm.score"></el-input>
      </el-form-item>
      <el-form-item
        v-for="(lett, index) in ruleForm.items"
        :label="letter[index]"
        :key="lett.id"
        :prop="'lett.' + index + '.name'"
      >
        <el-input v-model="lett.name"></el-input>
      </el-form-item>
      <el-form-item label="答案" prop="answer">
        <el-checkbox-group v-model="ruleForm.answer">
          <el-checkbox label="1" name="answer">A</el-checkbox>
          <el-checkbox label="2" name="answer">B</el-checkbox>
          <el-checkbox label="4" name="answer">C</el-checkbox>
          <el-checkbox label="8" name="answer">D</el-checkbox>
        </el-checkbox-group>
      </el-form-item>
      <el-form-item label="理由" prop="reason">
        <el-input v-model="ruleForm.reason"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button
          type="primary"
          v-html="buttonTitle"
          @click="submitForm('ruleForm')"
        ></el-button>
        <el-button @click="resetForm('ruleForm')">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  name: "multiplequestion",
  data() {
    let checkNum = (rule, value, callback) => {
      if (!value) {
        return callback(new Error("不用为空"));
      } else {
        const regNum = /^[0-9]+$/;
        // 验证手机
        if (regNum.test(value)) {
          callback();
        } else {
          return callback(new Error("必须为数字"));
        }
      }
    };
    return {
      buttonTitle: "立即创建",
      letter: ["A", "B", "C", "D"],
      ruleForm: {
        id: "",
        title: "",
        score: 2,
        items: [{}, {}, {}, {}],
        answer: [],
        reason: "",
        examination: this.$route.query.examid,
        type:2
      },
      rules: {
        title: [
          { required: true, message: "请输入题目名称", trigger: "blur" },
          {
            min: 2,
            max: 100,
            message: "长度在 3 到 100 个字符",
            trigger: "blur"
          }
        ],
        answer: [{ required: true, message: "请选择", trigger: "change" }],
        score: [
          { validator: checkNum, trigger: ["blur", "change"], required: true }
        ]
      }
    };
  },
  mounted: function() {
    var tempObj = this.$route.params ? this.$route.params.questionDetail : null;
    console.log(this.splitAns(tempObj.answer))
    if (tempObj) {
      this.buttonTitle = "提交修改";
      this.ruleForm.title = tempObj.title;
      this.ruleForm.score = tempObj.score;
      this.ruleForm.items = tempObj.items;
      this.ruleForm.answer = this.splitAns(tempObj.answer);
      this.ruleForm.reason = tempObj.reason;
      this.ruleForm.id = tempObj.id;
      this.ruleForm.examination = tempObj.examination;
    } else {
    }
  },
  methods: {
    submitForm(formName) {
      var self = this;
      this.$refs[formName].validate(valid => {
        if (valid) {
          self.ruleForm.answer = self.sumAns(self.ruleForm.answer)
          if (self.buttonTitle == "提交修改") {
            //提交表单
            self.webrequest(
              "post",
              "/question/updateQuestion",
              self.ruleForm,
              function(data) {
                self.$router.back();
              },
              function(error) {}
            );
          } else {
            //提交表单
            self.webrequest(
              "put",
              "/question/addQuestion",
              self.ruleForm,
              function(data) {
                self.$router.back();
              },
              function(error) {}
            );
          }
        } else {
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    goBack: function() {
      this.$router.back();
    },
    sumAns: function (answers) {
      var ret = 0;
      for(var i=0; i<answers.length;i++){
        ret += parseInt(answers[i]);
      }
      return ret;
    },
    splitAns: function (answer) {
      answer = parseInt(answer)
      var ret = [];
      var temp = 1;
      while(answer>=temp){
        if((answer & temp) != 0){
          ret.push(""+temp);
        }
        temp = temp * 2;
      }
      return ret;
    }
  }
};
</script>

<style scoped>
.contenet {
  width: 100%;
  height: 100%;
  text-align: left;
  max-width: 500px;
}
.top {
  margin-top: 0.2rem;
  text-align: left;
}
</style>

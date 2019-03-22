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
      <el-form-item label="试卷名称" prop="name">
        <el-input v-model="ruleForm.name"></el-input>
      </el-form-item>
      <el-form-item label="试卷描述" prop="describe">
        <el-input v-model="ruleForm.describe"></el-input>
      </el-form-item>
      <el-form-item label="时长(分)" prop="time">
        <el-slider
          v-model="ruleForm.time"
          :step="5"
          show-stops
          :min="0"
          :max="300"
          show-input>
        </el-slider>
      </el-form-item>
       <!--<el-form-item label="试卷类型" prop="type">-->
        <!--<el-select v-model="ruleForm.type" placeholder="请选择试卷类型">-->
          <!--<el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value"></el-option>-->
        <!--</el-select>-->
      <!--</el-form-item>-->
      <el-form-item label="状态" prop="status">
       <el-switch
          v-model="ruleForm.status"
          active-color="#13ce66"
          inactive-color="#ff4949"
          active-value="1"
          inactive-value="0">
        </el-switch>
      </el-form-item>
      <!--<el-form-item label="状态" prop="status">-->
        <!--<el-select v-model="ruleForm.status" placeholder="请选择试卷状态类型">-->
          <!--<el-option label="有效" :value="1"></el-option>-->
          <!--<el-option label="无效" :value="0"></el-option>-->
        <!--</el-select>-->
      <!--</el-form-item>-->
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
  name: "examadd",
  data() {
    return {
      buttonTitle: "立即创建",
      ruleForm: {
        name: "",
        describe: "",
        status: "0",
        time: 120
      },
      rules: {
        name: [
          { required: true, message: "请输入试卷名称", trigger: "blur" },
          { min: 2, max: 20, message: "长度在 3 到 20 个字符", trigger: "blur" }
        ],
        describe: [
          { required: true, message: "请输入试卷描述", trigger: "blur" }
        ],
        status: [{ required: true, message: "请选择", trigger: "change" }],
        time: [{ required: true, message: "请选择", trigger: "change" }],
      }
    };
  },
  mounted: function() {
    var tempObj = this.$route.params ? this.$route.params.serverDetail : null;
    if (tempObj) {
      this.buttonTitle = "提交修改";
      this.ruleForm.name = tempObj.name;
      this.ruleForm.describe = tempObj.describe;
      this.ruleForm.status = tempObj.status+"";
      this.ruleForm.id = tempObj.id;
      this.ruleForm.time = tempObj.time
    }
  },
  methods: {
    submitForm(formName) {
      var self = this;
      this.$refs[formName].validate(valid => {
        if (valid) {
          if (self.buttonTitle == "提交修改") {
            //提交表单
            self.webrequest(
              "post",
              "/exam/updateExam",
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
              "/exam/addExam",
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

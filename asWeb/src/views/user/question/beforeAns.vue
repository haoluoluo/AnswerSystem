<template>
  <div id="main">
    <div class="container">
      <h1>欢迎答题</h1>
      <div class="exam-notice">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <h2>考试须知</h2>
          </div>
          <div class="text" style="text-align: left">
            <p>一、考试为在线考试，有时间限制</p>
            <p>二、考试期间不得离开考试页面，否则离开两次将自动交卷</p>
            <p>三、考试请独立思考，不得与他人交流</p>
            <p>四、若还没开考，请耐心等待</p>
          </div>
          <el-tag>试题:</el-tag>
          <el-tag :type="info">
            {{ examName }}
          </el-tag>
          <el-row>
            <el-button
              type="primary"
              size="small"
              native-type="submit"
              v-bind:disabled="siDisabled"
              @keyup.enter.native="conf"
              v-on:click="conf"
            >确认答题</el-button
            >
            <el-button
              type="danger"
              size="small"
              native-type="submit"
              v-on:click="goBack"
              style="margin-top: 10px"
            >取消</el-button
            >
          </el-row>
        </el-card>
      </div>

    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      id: this.$route.query.id,
      examName: this.$route.query.examName
    };
  },
  beforeCreate() {
  },
  methods: {
    conf() {
      // this.$router.push({
      //   name: "question",
      //   path: "/question",
      //   params: { id: this.id, examName: this.examName }
      // });
      var path='/examCard/'+this.id+'/0'
      sessionStorage.removeItem("questions")
      sessionStorage.removeItem("startTime")
      sessionStorage.removeItem("endTime")
      this.$router.push({
        // name: "examCard",
        path: path,
        params: { examId: this.id, examName: this.examName }
      });
      //   this.$router.push({
      //   name: "home",
      //   path: "/home" ,
      //   params: { examId: this.id, examName: this.examName }
      // });
    },
    goBack: function() {
      this.$router.back();
    }
  }
};
</script>
<style lang="scss" scoped>
#main {
  text-align: center;
  margin: 0 auto;
  width: 400px;
  .container {
    margin-top: 10px;
    width: 100%;
    height: 100%;
    justify-content: center;
    align-items: center;
    border-radius: 2px;
    border: 1px solid #d7dae2;
  }
}
</style>

<template>
  <el-tabs v-model="activeName" @tab-click="handleClick">
    <!--<div class="top">-->
      <!--<el-button-->
        <!--type="primary"-->
        <!--icon="el-icon-arrow-left"-->
        <!--size="small"-->
        <!--@click="goBack"-->
        <!--&gt;返回</el-button-->
      <!--&gt;-->
    <!--</div>-->
    <el-tab-pane label="单选题" name="first">
      <single-question />
    </el-tab-pane>
    <el-tab-pane label="多选题" name="second">
      <multiple-question />
    </el-tab-pane>
    <el-tab-pane label="判断题" name="third">
      <true-false-question />
    </el-tab-pane>
    <!--<el-tab-pane label="定时任务补偿" name="fourth">定时任务补偿</el-tab-pane>-->
  </el-tabs>
</template>
<script>
  import singleQuestion from './singlequestion'
  import multipleQuestion from './multiplequestion'
  import trueFalseQuestion from './truefalsequestion'
  export default {
    name: 'questiontype',
    components: {
      singleQuestion,
      multipleQuestion,
      trueFalseQuestion
    },
    data() {
      return {
        activeName: 'first'
      };
    },
    methods: {
      handleClick(tab, event) {
        // console.log(tab, event);
      },
    goBack: function() {
      this.$router.back();
    }
    }
  };
</script>
<style lang="scss" scoped>
  .top {
    margin-top: 0.2rem;
    text-align: left;
  }
</style>

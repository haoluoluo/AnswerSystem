<template>
  <div class="leftContent">
    <el-menu style="border: none;"
             :default-active="$router.path"
             :router="true"
             class="el-menu-vertical-demo"
             text-color="#ffffff"
             background-color="#545c64"
             @open="handleOpen"
             @close="handleClose"
             :collapse="isCollapse">
      <el-menu-item index="" @click="buttonClick">
        <i class="el-icon-*">☰</i> <span slot="title">MENU</span>
      </el-menu-item>
      <el-menu-item index="/admin/userMgr">
        <i class="el-icon-menu"></i>
        <span slot="title">学生管理</span>
      </el-menu-item>
      <el-menu-item index="/admin/examMgr">
        <i class="el-icon-goods"></i>
        <span slot="title">试卷管理</span>
      </el-menu-item>
      <el-menu-item index="/admin/recordsMgr">
        <i class="el-icon-info"></i>
        <span slot="title">成绩查看</span>
      </el-menu-item>
      <!--<el-menu-item index="/orderMgr">-->
        <!--<i class="el-icon-tickets"></i>-->
        <!--<span slot="title">订单管理</span>-->
      <!--</el-menu-item>-->

    </el-menu>
  </div>
</template>


<script>
  export default {
    data() {
      return {
        isCollapse: true
      };
    },
    methods: {
      buttonClick:function () {
        this.isCollapse = !this.isCollapse
      },
      handleOpen(key, keyPath) {
        console.log(key, keyPath);
      },
      handleClose(key, keyPath) {
        console.log(key, keyPath);
      }
    }
  }
</script>

<style lang="scss">
    .leftContent{
      background-color: #545c64;
      height: 100%;
    }
    .el-menu-vertical-demo:not(.el-menu--collapse) {
      width: 150px;
      min-height: 400px;
    }
</style>

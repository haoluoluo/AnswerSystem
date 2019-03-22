<template>
  <el-container style="height: 100%;">
    <left></left>
    <el-container>
      <el-header><top></top></el-header>
      <el-main><router-view/></el-main>
    </el-container>
  </el-container>
</template>
<script>
    import left from '../../components/index/left'
    import top from '../../components/index/top'
    export default {
      name: "index",
        components:{
          left,
          top
        }
    }
</script>

<style lang="scss" scoped>

</style>

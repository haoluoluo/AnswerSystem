<template>
<el-container style="height:100%">
  <iAside></iAside>
  <el-container>
    <el-header>
      <iHeader></iHeader>
    </el-header>
    <el-main>
        <keep-alive>
          <router-view v-if="$route.meta.keepAlive"></router-view>
        </keep-alive>

        <router-view v-if="!$route.meta.keepAlive"></router-view>
    </el-main>
  </el-container>
</el-container>

</template>

<script>
import iHeader from '@/components/iindex/iHeader'
import iAside from '@/components/iindex/iAside'

export default {
  components: {
    iHeader,
    iAside
  }
}
</script>
<style lang="scss" scoped>
  .breadcrumb-container {
      line-height:55px;
    .title {
      width: 200px;
      float: left;
      color: #475669;
    }
    .breadcrumb-inner {
      float: right;
        line-height:55px;
    }
  }

  .fade-enter-active,
  .fade-leave-active {
    transition: all .2s ease;
  }
  .fade-enter,
  .fade-leave-active {
    opacity: 0;
  }
</style>

<template>
<div id="main">
  <ul>
    <li><span>试卷名称:</span><span class="spnImp">{{examName}}</span></li>
    <li><span>考生:</span><span class="spnImp">{{userName}}</span></li>
    <li><span>成绩:</span><span class="spnImp">{{scope}}</span></li>
  </ul>
  <router-link :to="{ name: 'home'}">返回首页</router-link>
</div>
</template>
<script>
  export default {
    data () {
      return {
        examName : this.$route.params.examName,
        userName : localStorage.getItem("email"),
        scope : this.$route.params.scope
      }
    }
  }
</script>

<style lang="scss" scoped>
#main{
  text-align: center;
  margin-top: 100px;
  ul {
    list-style: none;
    li {
        .spnImp{
          color: red;
        }
    }
  }
}
</style>

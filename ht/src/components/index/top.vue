<template>
  <div class="header">
    <div class="header_left">
    <h1>后台管理系统</h1>
    </div>
    <div class="header_right">
      <el-button type="primary" size="small" @click="logout">退出登录</el-button>
    </div>
  </div>
</template>

<script>
    export default {
        name: "top",
        data(){
          return{}
        },
        methods:{
          logout:function () {
            this.$confirm('是否退出?', '提示', {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'warning'
            }).then(() => {
               this.$cookieHandle.removeCookie('adminId');
               this.$router.push({name:'login'});
            }).catch(() => {

            });
          }
        }
    }
</script>

<style lang="scss">
   .header{
     margin: 0;
     padding: 20px 0;
     height: 40px;
     text-align: left;
     border-bottom: 1px solid #F5F5F5;
     display: flex;
     flex-direction: row;
   }
   .header_left{
     flex: 0 0 30%;
   }
   .header_right{
     flex: 0 0 70%;
     text-align: right;
   }
</style>

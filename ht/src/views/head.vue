<template>
  <div id="head">
    <div class="page-header navbar navbar-fixed-top">
      <!-- BEGIN HEADER INNER -->
      <div class="page-header-inner">
        <!-- BEGIN LOGO -->
        <div class="page-logo">
          <router-link :to="{ name: '/home'}">答题系统</router-link>
        </div>
        <!-- END LOGO -->
        <!-- BEGIN TOP NAVIGATION MENU -->
        <div class="top-menu">
          <ul class="nav navbar-nav pull-right">
            <!-- BEGIN USER LOGIN DROPDOWN -->
            <!-- DOC: Apply "dropdown-dark" class after below "dropdown-extended" to change the dropdown styte -->
            <li class="dropdown dropdown-user">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
                <img alt="" class="img-circle" src="/components/com_exams/assets/img/default-avatar-2.png">
                <span class="username username-hide-on-mobile">
                  {{ username }}
                </span>
                <i class="fa fa-angle-down"></i>
              </a>
            </li>
              <li class="dropdown dropdown-user">
                <router-link :to="{ name: 'resultAll'}">考试结果</router-link>
            </li>
            <li class="dropdown dropdown-user">
              <a title="退出" class="dropdown-toggle" style="cursor: pointer;">
                <i class="fa-power-off fa"></i>
                <span class="username" v-on:click="loginOut()" >退出</span>
              </a>
            </li>
          </ul>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  data () {
    return {
      username: localStorage.getItem("email")
    }
  },
  methods : {
    loginOut(){
      localStorage.removeItem("email")
      localStorage.removeItem("userId")
      localStorage.removeItem("token")
      this.$router.push({path: '/login'})
    }
  }
}
</script>
<style lang="scss" scoped>
  #head{
    .page-header {
      color: white;
      background-color: #2b3643;
      .page-logo {
        display: inline-block;
        width: 70%;
        text-align: center;
        font-size: 30px;
        a {
          color: white;
          text-decoration: none;
        }
      }
    .top-menu {
      display: inline-block;
      width: 29%;
      ul {
        list-style-type: none;
        li{
          display: inline-block;
          margin-left: 10px;
          a {
            color: white;
            text-decoration: none;
          }
        }
      }
    }
    }
  }
</style>

<template>
  <div class="i-header-div">
    <el-row style="height:55px;padding:0 .2rem">
      <el-col class="orgName" :xs="24" :sm="6" :md="6" :lg="6" :xl="6">
        <div class="grid-content bg-purple">
          <h1 class="sys_name">{{ organName }}</h1>
        </div>
      </el-col>
      <el-col :xs="24" :sm="18" :md="18" :lg="18" :xl="18" class="text-right">
        <div class="grid-content bg-purple-light">
          <div class="user">
            <!-- <el-badge  is-dot class="item"> -->
            <span
              ><a
                href="tencent://message/?uin=1416944705&Site=qq&Menu=yes"
                title="在线客服QQ"
                ><em class="red">在线客服QQ：1416944705</em></a
              ></span
            >
            <el-badge class="item">
              <i class="el-icon-bell"></i>
            </el-badge>
            <el-dropdown trigger="hover">
              <span class="el-dropdown-link userinfo-inner"
                ><em>{{ email }}</em>
                <i class="el-icon-caret-bottom el-icon--right"></i
              ></span>
              <el-dropdown-menu slot="dropdown">
                 <el-dropdown-item @click.native="goSettings">设置</el-dropdown-item>
                <el-dropdown-item divided @click.native="logout">退出登录</el-dropdown-item>
              </el-dropdown-menu>
            </el-dropdown>
          </div>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import router from "@/router/router";
export default {
  data() {
    return {
      organName: "考试系统",
      email: ""
    };
  },
  beforeCreate() {
    let customerId = sessionStorage.getItem("userId");
    let email = sessionStorage.getItem("email");

    if (
      customerId == undefined ||
      email == undefined ||
      customerId == "" ||
      email == "" ||
      customerId == null ||
      email == null
    ) {
      router.replace({
        path: "/iLogin",
        query: { redirect: router.currentRoute.fullPath }
      });
    }
  },
  mounted() {
    this.email = sessionStorage.getItem("email");

    let data = { userId: sessionStorage.getItem("userId") };
  },
  methods: {
    logout: function() {
      //
      let data = null;
      this.$confirm("确认要退出吗?", "提示", {
        type: "warning"
      })
        .then(() => {
          sessionStorage.clear();
          localStorage.clear();
          this.$router.push({ path: "/ilogin/login" });
          // //请求接口
          // this.axios.post('/auth/logout', this.$qs.stringify(data))
          // .then(jsondata => { //成功
          //     if (jsondata.data.code == 200){
          //         sessionStorage.clear();
          //         this.$router.push({ path: "/ilogin/login" });
          //     }
          // })
          // .catch(e => { //失败
          //     console.log(e)
          // });
        })
        .catch(() => {});
    },
    goSettings:function(){
      this.$router.push({ path: "/settings" });
    },
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss">
.i-header-div {
  height: 100%;
  box-sizing: border-box;
  h1.sys_name {
    width: 130px;
    line-height: 55px;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
    color: #333;
    float: left;
    font-weight: normal;
    font-size: 18px;
  }
  .tips {
    width: 100%;
    height: 44px;
    position: relative;
    line-height: 44px;
    padding: 0 0.2rem;
    background-color: #fdf6ec;
    color: #e6a23c;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
    .el-button {
      position: absolute;
      right: 20px;
      top: 8px;
    }
  }
}
.user {
  float: right;
  line-height: 55px;
  font-size: 14px;
  text-align: right;
  a {
    color: #3a8ee6;
  }
  .el-icon-bell {
    vertical-align: 2px;
  }
  .userinfo-inner {
    color: #3a8ee6;
    cursor: pointer;
    display: inline-block;
    em {
      padding: 0 0 0 0.2rem;
    }
    img {
      width: 25px;
      height: 25px;
      border-radius: 20px;
      margin-top: 17px;
      float: right;
    }
  }
  > span {
    padding: 0 0.2rem;
    color: #3a8ee6;
  }
}
.el-dropdown-menu__item {
  a {
    color: #606266;
    display: block;
  }
}
.el-dropdown-menu__item:hover {
  a {
    color: #3a8ee6;
  }
}
@media screen and (max-width: 767px) {
  .orgName {
    display: none;
  }
}
</style>

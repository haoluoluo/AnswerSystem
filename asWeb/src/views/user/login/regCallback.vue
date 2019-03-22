<template>
  <div class="register">
    <div class="register-form text-center">
      <h1 class="register-h1">注册成功！</h1>
      <p>
        恭喜，您的微主页已创建成功<br>
        您可以打开微信扫码访问您的主页
      </p>
      <p>
        <img src="http://runimg.mockplus.cn/assets/components/wireframe/qrcode.png" width="150" alt="微主页二维码">
      </p>
      <p>
        <el-button type="primary" @click="goLogin">点击登录进入到控制台</el-button>
      </p>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
        time: 3
    }
  },
  mounted() {
      //setTimeout(function () { that.$router.push({ name: "home" }) }, 2000);

      // let clock = window.setInterval(() => {
      //     this.time --;
      //     if (this.time < 1) {     //当倒计时小于0时清除定时器
      //         window.clearInterval(clock);
      //         this.time = 3;
      //         this.$router.push({path: "/home"});
      //     }
      // },1000)
  },
  methods:{
      goLogin(){
          this.$router.push({path:'/ilogin/login'})
      }
  }
}
</script>
<style lang="scss" scoped>
.register {
  width: 100%;
  height: 100%;
  box-sizing: border-box;
}
.register-form {
  width: 4.8rem;
  margin:0 auto;
  p{padding:.2rem 0}
}
.register-h1 {
  margin-bottom: 0.2rem;
  font-size: 22px;
  font-weight: 400;
  color: green;
}
.register-p {
  margin-bottom: 0.2rem;
  line-height: 1.5;
  color: #1f2f3d;
  a {
    padding: 0 10px;
    font-size: 16px;
  }
}
@media screen and (max-width: 479px){
  .register-form{width:90%;position:relative;}
}
</style>

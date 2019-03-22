<template>
    <div class="i-login">
        <loginHeader></loginHeader>
        <div class="i-login-view">
            <router-view/>
        </div>
        <footer class="copyright">
            <div class="text-center">
                <p><a href=".">首页</a> ¦ <a href=".">公司介绍</a> ¦ <a href=".">联系我们</a> ¦ <a href=".">备案号</a></p>
                <p>Copyright © 2018 XNT All Rights Reserved</p>
            </div>
        </footer>
    </div>
</template>
<script>
import loginHeader from '@/components/login/loginHeader'
export default {
    components: {
        loginHeader
    }
}
</script>
<style lang="scss">
.i-login {width: 100%;height: 100%;float:left;}
.i-login-view {
  width: 100%;
    min-height:500px;
    padding:0.9rem 0;
  margin-top:1rem;
    .el-form-item__label{line-height:49px!important;}
}
.text-center{
  text-align: center;
}
.copyright{padding:.2rem;
    p{padding:.05rem 0;color:#CCC}
    a{color: #409EFF;padding:0 .05rem}
}

</style>

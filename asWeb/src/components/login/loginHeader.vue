<template>
    <div class="login-header">
        <!--<a href="."><img src="@/assets/logo.png" alt=""><span>小牛通客户管理平台</span></a>-->

        <div class="fr">
            <!--<a href=".">反馈</a> <a href="."><i class="el-icon-mobile-phone"></i> APP</a> -->
            <!--<el-button size="mini" @click="goReg">注册</el-button> <el-button size="mini" type="primary" @click="goSign">登录</el-button>-->
        </div>
    </div>
</template>

<script>
export default {
    methods:{
        goReg(){
            this.$router.push({path:'/ilogin/register'})
        },
        goSign(){
            this.$router.push({path:'/ilogin/login'})
        }
    }
}
</script>

<style scoped lang="scss">
.login-header{width:100%; height: .8rem; background:#FFF; padding:.2rem .3rem;line-height:.4rem; position:fixed;
    top:0;left: 0;z-index:999;
    a{display: inline-block;}
    img{width: .4rem;float:left;}
    span{color: #000;padding-left:.1rem;font-size:22px;}
}
    .fr a{color:#666;font-size:14px;padding:0 .15rem;}


@media screen and (max-width: 639px){
    .fr a{display:none;}
}
@media screen and (max-width: 479px){
    .login-header span{display:none;}
}
</style>

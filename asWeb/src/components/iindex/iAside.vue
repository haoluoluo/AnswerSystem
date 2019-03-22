<template>
    <el-aside>
        <el-row class="tac">
            <el-col :span="24">
                <el-menu
                :default-active="$route.path"
                :router="true"
                class="el-menu-vertical-demo menu"
                :collapse="isCollapse"
                background-color="#222"
                text-color="#FFF"
                active-text-color="#409EFF" >
                    <el-menu-item index="" class="isClossTab" @click="isClossTabFun">
                        <i class="el-icon-*">☰</i> <span slot="title">MENU</span>
                    </el-menu-item>
                    <!--首页-->
                    <el-menu-item index="/home">
                        <i class="el-icon-menu"></i>
                        <span slot="title">首页</span>
                    </el-menu-item>
                   <el-menu-item index="/examList">
                        <i class="el-icon-mobile-phone"></i>
                        <span>考试管理</span>
                    </el-menu-item>
                    <el-menu-item index="/userRecord">
                        <i class="el-icon-document"></i>
                        <span>成绩管理</span>
                    </el-menu-item>
                   <el-menu-item index="/settings">
                        <i class="el-icon-setting"></i>
                        <span slot="title">系统设置</span>
                    </el-menu-item>
                    <!--&lt;!&ndash;产品管理&ndash;&gt;-->
                    <!--&lt;!&ndash;资讯、观点、组合、问答&ndash;&gt;-->
                    <!--<el-submenu index="/information">-->
                        <!--<template slot="title">-->
                            <!--<i class="el-icon-tickets"></i>-->
                            <!--<span>产品管理</span>-->
                        <!--</template>-->
                        <!--<el-menu-item-group>-->
                            <!--<el-menu-item index="/informationList">资讯</el-menu-item>-->
                            <!--<el-menu-item index="/developing">观点</el-menu-item>-->
                            <!--<el-menu-item index="/strategylist">组合</el-menu-item>-->
                            <!--<el-menu-item index="/question">问答</el-menu-item>-->
                        <!--</el-menu-item-group>-->
                    <!--</el-submenu>-->
                    <!--客户管理-->
                    <!--<el-menu-item index="/client">-->
                        <!--<i class="el-icon-mobile-phone"></i>-->
                        <!--<span>客户管理</span>-->
                    <!--</el-menu-item>-->
                    <!--订单管理-->
                    <!--<el-menu-item index="/developing">-->
                        <!--<i class="el-icon-document"></i>-->
                        <!--<span>订单管理</span>-->
                    <!--</el-menu-item>-->
                    <!--&lt;!&ndash;数据分析&ndash;&gt;-->
                    <!--<el-menu-item index="/developing">-->
                        <!--<i class="el-icon-news"></i>-->
                        <!--<span>数据分析</span>-->
                    <!--</el-menu-item>-->
                    <!--&lt;!&ndash;投研工具&ndash;&gt;-->
                    <!--&lt;!&ndash;资讯服务、策略选股、智能诊股&ndash;&gt;-->
                    <!--<el-submenu index="/investment">-->
                        <!--<template slot="title">-->
                            <!--<i class="el-icon-document"></i>-->
                            <!--<span>投研工具</span>-->
                        <!--</template>-->
                        <!--<el-menu-item-group>-->
                            <!--<el-menu-item index="/investmentInfo/1">资讯服务</el-menu-item>-->
                            <!--<el-menu-item index="/investmentChoice/2/index">策略选股</el-menu-item>-->
                            <!--<el-menu-item index="/intelligent">智能诊股</el-menu-item>-->
                        <!--</el-menu-item-group>-->
                    <!--</el-submenu>-->
                    <!--&lt;!&ndash;营销工具&ndash;&gt;-->
                    <!--<el-menu-item index="/marketing">-->
                        <!--<i class="el-icon-phone-outline"></i>-->
                        <!--<span>营销工具</span>-->
                    <!--</el-menu-item>-->
                    <!--&lt;!&ndash;系统设置&ndash;&gt;-->
                    <!--<el-menu-item index="/settings">-->
                        <!--<i class="el-icon-setting"></i>-->
                        <!--<span slot="title">系统设置</span>-->
                    <!--</el-menu-item>-->



                    <!--<el-menu-item index="" class="isClossTab" @click="isClossTabFun">-->
                         <!--<i class="el-icon-*">☰</i> <span slot="title">MENU</span>-->
                    <!--</el-menu-item>-->
                    <!--
                    <el-menu-item index="/">
                        <i class="el-icon-menu"></i>
                        <span slot="title">首页</span>
                    </el-menu-item>
                    <el-submenu index="/information">
                        <template slot="title">
                            <i class="el-icon-tickets"></i>
                            <span>资讯产品</span>
                        </template>
                        <el-menu-item-group>
                            <el-menu-item index="/information/informationClass">资讯产品分类</el-menu-item>
                            <el-menu-item index="/information/informationList">资讯产品列表</el-menu-item>
                        </el-menu-item-group>
                    </el-submenu>
                    <el-menu-item index="/strategylist">
                        <i class="el-icon-time"></i>
                        <span slot="title">服务管理</span>
                    </el-menu-item>
                    <el-menu-item index="/question">
                        <i class="el-icon-service"></i>
                        <span slot="title">问答管理</span>
                    </el-menu-item>
                    <el-menu-item index="/client">
                            <i class="el-icon-mobile-phone"></i>
                            <span>客户管理</span>
                    </el-menu-item>
                    <el-menu-item index="/marketing">
                            <i class="el-icon-phone-outline"></i>
                            <span>营销工具</span>
                    </el-menu-item>
                    <el-submenu index="/investment">
                        <template slot="title">
                            <i class="el-icon-document"></i>
                            <span>投研工具</span>
                        </template>
                        <el-menu-item-group>
                            <el-menu-item index="/investment/investmentInfo/1">资讯工具</el-menu-item>
                            <el-menu-item index="/investment/investmentChoice/2/index">选股工具</el-menu-item>
                            <el-menu-item index="/investment/intelligent">智能诊股</el-menu-item>
                        </el-menu-item-group>
                    </el-submenu>
                    <el-menu-item index="/settings">
                        <i class="el-icon-setting"></i>
                        <span slot="title">系统设置</span>
                    </el-menu-item>
                    -->
                </el-menu>
            </el-col>
        </el-row>
    </el-aside>
</template>
<script>
export default {
    data() {
        return {
            isCollapse: false
        };
    },
    methods:{
        isClossTabFun(){
            this.isCollapse = !this.isCollapse;
        }
    }
}
</script>
<style lang="scss">
    .el-menu-vertical-demo:not(.el-menu--collapse) {
        width: 180px;
        min-height: 400px;
    }
    .el-submenu__icon-arrow{margin-top:-5px!important;}
</style>
<style lang="scss" scoped>
    $header-height:55px;
    $background-color: #666;
    $color: #FFF;

    aside{overflow: visible; height:100%; width:auto!important; background-color: $background-color;color: $color;
         .isClossTab{ height: $header-height!important;line-height:55px!important;cursor: pointer;color:$color;background:#409EFF!important}
         .isClossTab i{color:#FFF!important;margin-top:-2px!important;}
         .menu {border-right:0; }
         .el-menu .el-menu-item{width:100%;height:55px; line-height:55px;}
         .el-submenu .el-menu-item{padding:0 0 0 50px!important;min-width:100%;height:50px;line-height:50px;}
     }
    .tac{
        width: 100%;
        height: 100%;
        overflow-x:hidden;
        overflow-y:auto;
        >.el-col{
            height: 100%;
            >.el-menu{
                height: 100%;
            }
        }
    }

</style>

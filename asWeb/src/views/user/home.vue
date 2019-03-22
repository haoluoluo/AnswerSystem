<template>
        <!-- 首页 -->
        <div class="home">
            <div class="home-header">
                <el-row class="row-bg" :gutter="28">
                    <el-col :xs="24" :sm="12" :md="8" :lg="5" :xl="5">
                        <div class="grid-content bg-purple">
                            <div class="item_conn">
                                <p>{{article_subscribes}}</p>
                                <span>资讯订阅数</span>
                            </div>
                        </div>
                    </el-col>
                    <el-col :xs="24" :sm="12" :md="8" :lg="5" :xl="5">
                        <div class="grid-content bg-purple-light">
                            <div class="item_conn">
                                <p>{{strategy_subscribes}}</p>
                                <span>组合订阅数</span>
                            </div>
                        </div>
                    </el-col>
                    <el-col :xs="24" :sm="12" :md="8" :lg="5" :xl="5">
                        <div class="grid-content bg-purple">
                            <div class="item_conn">
                                <p>{{question_answers}}</p>
                                <span>总问答数</span>
                            </div>
                        </div>
                    </el-col>
                    <el-col :xs="24" :sm="12" :md="8" :lg="5" :xl="5">
                        <div class="grid-content bg-purple-light">
                            <div class="item_conn">
                                <p>{{pay_users}}</p>
                                <span>付费客户数</span>
                            </div>
                        </div>
                    </el-col>
                </el-row>
            </div>
        </div>
</template>
<script>
export default {
    data() {
        return {
            activeName: '0',
            article_subscribes: 0,
            strategy_subscribes: 0,
            question_answers: 0,
            pay_users: 0,

            userId: sessionStorage.getItem('userId'),

            visible: false,
            visible0: false,
        };
    },
    mounted:function(){
    },
    methods: {}
}
</script>
<style lang="scss">
    .codeDiv{
        .el-tabs--border-card{border:0 none;
            .el-tabs__header{border:0 none!important;background-color:#FFF;}
            .el-tabs__content{padding:0}
        }
        .el-tabs__nav{width:100%;}
        .el-tabs__item{width:50%;font-size:13px;padding:0;margin:0!important;border:0 none!important;}
        .el-tabs__item.is-active{border:0 none!important;background-color:transparent!important;color:#409EFF!important;}
        .el-tabs__active-bar{height:1px;}
        .el-tabs__nav-wrap::after{height:1px;}
        .el-tabs__active-bar{width:50%!important;}
        .el-tabs--top .el-tabs__item.is-top:nth-child(2){padding:0;}
        .el-tabs--top .el-tabs__item.is-top:last-child{padding:0;}
    }
    .home-content {
        .el-tag{border:0 none;}
        .el-tag .el-icon-close {
            color: #FFF !important;
            top:0;
        }
        .el-collapse-item__content{color:#666;}
        .el-collapse-item__header{height:55px;line-height:55px;}
    }
</style>
<style lang="scss" scoped>
.home {
  width: 100%;
  height: 100%;
}
.home-header {
  width: 100%;
  overflow: hidden;
    .el-row{
        .el-col{margin-bottom:20px}
    }
    .grid-content{overflow: hidden;
        background: #fff;
        height: 160px;
    }
}
.home-content {min-height:calc(100% - 1.9rem); background: #fff;margin-top: 10px;padding:.2rem;
    .el-row{padding:0 0 .2rem;
       .el-tag{margin:0 15px 15px 0;background:#409EFF;color:#FFF;}
    }
    h1{color: #409eff;
        span{float:right;font-size:14px;padding-left:10px;}
    }
    .el-collapse{border:0 none!important;}
    .el-collapse span {
        float: right;
        padding-right: 0.1rem;
    }
}
.codeDiv p{
    margin-bottom:20px;
    img{vertical-align:middle;}
    button{font-size:13px;}
}
.qrcode {
  height:80px;
    float:left;
}
.wxCode{
    width:80px;
    height:90px;
    display:inline-block;
    text-align: center;
    p{font-size:12px;}
}
.item_conn {
  float: left;
  width: 100%;
  text-align:center;
  padding: 0.5rem 0.1rem;
  box-sizing: border-box;
}
.item_conn p {
  font-size: 36px;
  color: #f00;
  margin-bottom: 10px;
}
.item_conn span {
  color: #666;
}
</style>

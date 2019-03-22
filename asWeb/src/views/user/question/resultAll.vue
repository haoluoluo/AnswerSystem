<template>
  <div id="home">
    <userhead />
    <div id="main">
      <p>考试结果</p>
      <ul v-show="isVi">
        <li v-for="da in data" v-bind:key="da.id"><span>{{da.examinationName}}:{{da.scope}}</span></li>
      </ul>
      <div v-show="!isVi">
        暂无试卷信息{{this.userId}}
      </div>
    </div>
  </div>
</template>

<script>

import router from "@/router/router";
import userhead from '../../head'
export default {
  // 2.然后,在components中写入子组件
  components: {userhead},
  methods: {},
  created () {
    this.axios.get('/api/records/records/'+this.userId)
      .then(jsondata => { // 成功
        if (jsondata.data.code == 200) {
          this.data = jsondata.data.data
          this.isVi = true
        }
      })
      .catch(e => { // 失败
        this.$message.error('服务器异常，请稍后再试！')
        this.isVi = false
      })
  },
  beforeCreate(){
    let customerId = sessionStorage.getItem('userId');
    let email = sessionStorage.getItem('email');
    if (customerId == undefined || email == undefined || customerId == '' || email == '' || customerId == null || email == null) {
      router.replace({
        path: '/iLogin',
        query: {redirect: router.currentRoute.fullPath}
      })
    }
  },
  data () {
    return {
    data: [],
    isVi: false,
    userId: localStorage.getItem("userId")
  }
  }
}
</script>
<style lang="scss" scoped>
#home{
  background-color: #2b3643;
   #main{
    p{
      color: white;
      margin-left: 10px;
      font-size: 20px;
    }
    ul {
    padding: 15px 0;
    margin: 0 auto;
    width: 94%;
    li {
      list-style-type: none;
      height: 30px;
      line-height: 30px;
      border: solid 1px white;
      background-color: #d2dcf8;
      a{
        text-decoration: none;
        color: black;
        display: block;
      }
  }
  }
  }
}
</style>

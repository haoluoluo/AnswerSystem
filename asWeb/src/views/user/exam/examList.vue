<template>
  <div class="contenet">
    <div class="title">
      <div class="title_left">
      <h1>考试列表</h1>
      </div>
    </div>
    <el-table v-loading="isLoading" :data="dataList?dataList.slice((currentPage-1)*pagesize,currentPage*pagesize):null" max-height="660" border>
      <el-table-column align="center" prop="id"  label="序号" width="80"></el-table-column>
      <el-table-column align="center"  prop="name"  label="试卷名称" width="200"></el-table-column>
      <el-table-column align="center"  prop="describe"  label="描述" width="300"></el-table-column>
      <el-table-column align="center" label="操作">
        <template slot-scope="scope">
          <el-button @click="handleClickStart(scope.row,scope.$index)" type="text" size="small">开始考试</el-button>
        </template>
      </el-table-column>
    </el-table>
    <div class="block" style="margin-top: .2rem;margin-bottom:.2rem;">
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page.sync="currentPage"
        :page-sizes="[10, 15, 20, 30]"
        :page-size="pagesize"
        layout="sizes, prev, pager, next"
        :total="totalSize">
      </el-pagination>
    </div>
  </div>
</template>

<script>
    export default {
        name: "examList",
      data() {
        return {
          userId: sessionStorage.getItem('userId'),
          isLoading:true,
          currentPage: 1,
          pagesize:10,
          totalSize:20,
          dataList:null
        };
      },
      mounted:function () {
        this.getServiceList()
      },
      methods: {

        handleSizeChange(val) {

        },
        handleCurrentChange(val) {

        },
        getServiceList:function (shopType) {
          var self = this
          var param = {
            user: this.userId
          }
          this.webrequest('get','/exam/getExamInfo',param,function (data) {
            self.isLoading = false
            self.dataList = data
            self.totalSize = data.length
          },function (error) {
          });
        },

        handleClickStart:function (data,index) {
          this.$router.push({name: 'beforeAns', path: '/beforeAns', query:{id:data.id, examName: data.name}})
        },
      }
    }
</script>

<style scoped>
  .contenet{
    width: 100%;
    height: 100%;
  }
  .title{
    margin-top: .2rem;
    text-align: left;
    margin-bottom: .2rem;
    display: flex;
    flex-direction: row;
  }
  .title_left{
    flex: 0 0 10%;
    display: flex;
    align-items: center;
  }
  .title_right{
    flex: 0 0 90%;
    display: flex;
    align-items: center;
  }
</style>

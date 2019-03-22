<template>
  <div class="contenet">
    <div class="title">
      <div class="title_left">
      <h1>问题管理</h1>
      </div>
      <div class="title_right">
        <h5 style="width: 20%">试卷名称:{{examInfo.name}}</h5>
        <el-button type="primary" @click="addQuestion">新建题目</el-button>
      </div>
      <!--<div class="top">-->
      <!--<el-button type="primary" icon="el-icon-arrow-left" size="small" @click="goBack">返回</el-button>-->
    <!--</div>-->
    </div>
    <el-table v-loading="isLoading" :data="dataList?dataList.slice((currentPage-1)*pagesize,currentPage*pagesize):null" max-height="660" border>
      <el-table-column align="center" type="index" label="序号" width="80"></el-table-column>
      <!--<el-table-column align="center" prop="id"  label="序号" width="80"></el-table-column>-->
      <el-table-column align="center"  prop="title"  label="题目" width="200"></el-table-column>
      <el-table-column align="center" prop="typeparse" label="类型" width="180"></el-table-column>
      <el-table-column align="center" prop="score" label="分数" width="180"></el-table-column>
      <el-table-column align="center" label="操作">
        <template slot-scope="scope">
          <el-button @click="handleClickUpdate(scope.row,scope.$index)" type="text" size="small">修改</el-button>
          <el-button @click="handleClickDelete(scope.row,scope.$index)" type="text" size="small">删除</el-button>
          <!--<el-button @click="handleClickLook(scope.row,scope.$index)" type="text" size="small">管理试卷</el-button>-->
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
        name: "questionmanager",
      data() {
        return {
          examInfo:this.$route.params,
          isLoading:true,
          currentPage: 1,
          pagesize:10,
          totalSize:20,
          dataList:null,
          statusType:{'0':'无效','1':'有效'},
        };
      },
      mounted:function () {
        this.getQuestionList(this.examInfo.id)
      },
      methods: {

        formatterTimeType:function (type) {
            return this.timeType[type]
        },
        formatterType:function (rowData) {
          return this.serverType[rowData.shopType]
        },
        formatterStatusType: function(rowData){
          return this.statusType[rowData.status]
        },
        handleSizeChange(val) {

        },
        handleCurrentChange(val) {

        },
        formatterTimeLimit:function (row) {
           return row.duration + this.timeType[row.durationUnit];
        },
        getQuestionList:function (examId) {
          var self = this
          var param = {
            id: examId
          }
          this.webrequest('get','/question/getQuestionInfo/'+param.id,param,function (data) {
            self.isLoading = false
            self.dataList = data
            self.totalSize = data.length
          },function (error) {

          });
        },
        addQuestion:function () {
           this.$router.push({name:'questiontype',params:{examid: this.examInfo.id},query:{examid: this.examInfo.id}});
        },
        //查看
        handleClickLook:function (data,index) {
          // this.$alert('<div>序号:'+data.id+'</div>' +
          //   '<div>类型:'+this.formatterType(data)+'</div>' +
          //   '<div>sku:'+data.sku+'</div>' +
          //   '<div>标题:'+data.title+'</div>' +
          //   '<div>价格:'+data.price+'元</div>' +
          //   '<div>时限:'+data.duration+this.formatterTimeType(data.durationUnit)+'</div>', '详情', {
          //   dangerouslyUseHTMLString: true
          // });
        },
        //s删除
        handleClickDelete:function (data,index) {
          this.$confirm('此操作将删除该试卷, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            var self = this;
            var param = {
              id:data.id
            }
            this.webrequest('delete','/question/removeQuestion',param,function (data) {
              self.$message({
                type: 'success',
                message: '删除成功!'
              });
              self.getServiceList(10)
            },function (error) {

            });

          }).catch(() => {

          });
        },
          //修改
          handleClickUpdate:function (data,index) {
          if(data.type == 1){
            this.$router.push({name:'singlequestion',path: '/admin/singlequestion',params:{questionDetail:data}});
          }else if(data.type == 2){
            this.$router.push({name:'multiplequestion',path: '/admin/multiplequestion',params:{questionDetail:data}});
          }else{
            this.$router.push({name:'truefalsequestion',path: '/admin/truefalsequestion',params:{questionDetail:data}});
          }
          },
        goBack:function () {
          this.$router.back()
        }
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

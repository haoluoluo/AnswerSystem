<template>
  <div class="contenet">
    <div class="title">
      <div class="title_left">
      <h1>成绩查看</h1>
      </div>
      <!--<div class="title_right">-->
        <!--<h5 style="width: 20%">试卷名称:{{examInfo.name}}</h5>-->
        <!--<el-button type="primary" @click="addQuestion">新建题目</el-button>-->
      <!--</div>-->
      <!--<div class="top">-->
      <!--<el-button type="primary" icon="el-icon-arrow-left" size="small" @click="goBack">返回</el-button>-->
    <!--</div>-->
    </div>
    <el-table v-loading="isLoading" :data="dataList?dataList.slice((currentPage-1)*pagesize,currentPage*pagesize):null" max-height="660" border
              :default-sort = "{prop: 'scope', order: 'descending'}"
    >
      <el-table-column align="center" type="index" label="序号" width="80"></el-table-column>
      <!--<el-table-column align="center" prop="id"  label="序号" width="80"></el-table-column>-->
      <el-table-column align="center"  prop="username"  label="考生名称" width="200"></el-table-column>
      <el-table-column align="center"  prop="examinationName"  label="题目名称" width="200"></el-table-column>
      <el-table-column align="center" prop="scope" label="分数" sortable width="180"></el-table-column>
      <el-table-column align="center" prop="acnumber" label="正确数量" width="180"></el-table-column>
      <el-table-column align="center" prop="ernumber" label="错误数量" width="180"></el-table-column>
      <el-table-column align="center" :formatter="arraySpanMethod" label="正确率"   width="180"></el-table-column>
      <!--<el-table-column align="center" label="操作">-->
        <!--&lt;!&ndash;<template slot-scope="scope">&ndash;&gt;-->
          <!--&lt;!&ndash;<el-button @click="handleClickUpdate(scope.row,scope.$index)" type="text" size="small">修改</el-button>&ndash;&gt;-->
          <!--&lt;!&ndash;<el-button @click="handleClickDelete(scope.row,scope.$index)" type="text" size="small">删除</el-button>&ndash;&gt;-->
          <!--&lt;!&ndash;&lt;!&ndash;<el-button @click="handleClickLook(scope.row,scope.$index)" type="text" size="small">管理试卷</el-button>&ndash;&gt;&ndash;&gt;-->
        <!--&lt;!&ndash;</template>&ndash;&gt;-->
      <!--</el-table-column>-->
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
        name: "recordsmanager",
      data() {
        return {
          queryParam:{
            userno:this.$route.params.userno,
            examination: this.$route.params.examination
          },
          isLoading:true,
          currentPage: 1,
          pagesize:10,
          totalSize:20,
          dataList:null,
        };
      },
      mounted:function () {
        this.getRecordList()
      },
      methods: {
        formatterStatusType: function(rowData){
          return this.statusType[rowData.status]
        },
        handleSizeChange(val) {

        },
        handleCurrentChange(val) {

        },
        getRecordList:function () {
          var self = this
          var param = {
            userno: this.$route.params.userno,
            examination: this.$route.params.examination
          }
          this.webrequest('get','/records/getRecordInfo',param,function (data) {
            self.isLoading = false
            self.dataList = data
            self.totalSize = data.length
          },function (error) {

          });
        },
        // addQuestion:function () {
        //    this.$router.push({name:'questionadd'});
        // },
        // //查看
        // handleClickLook:function (data,index) {
        //   // this.$alert('<div>序号:'+data.id+'</div>' +
        //   //   '<div>类型:'+this.formatterType(data)+'</div>' +
        //   //   '<div>sku:'+data.sku+'</div>' +
        //   //   '<div>标题:'+data.title+'</div>' +
        //   //   '<div>价格:'+data.price+'元</div>' +
        //   //   '<div>时限:'+data.duration+this.formatterTimeType(data.durationUnit)+'</div>', '详情', {
        //   //   dangerouslyUseHTMLString: true
        //   // });
        // },
        // //s删除
        // handleClickDelete:function (data,index) {
        //   this.$confirm('此操作将删除该试卷, 是否继续?', '提示', {
        //     confirmButtonText: '确定',
        //     cancelButtonText: '取消',
        //     type: 'warning'
        //   }).then(() => {
        //     var self = this;
        //     var param = {
        //       id:data.id
        //     }
        //     this.webrequest('delete','/question/removeQuestion',param,function (data) {
        //       self.$message({
        //         type: 'success',
        //         message: '删除成功!'
        //       });
        //       self.getServiceList(10)
        //     },function (error) {
        //
        //     });
        //
        //   }).catch(() => {
        //
        //   });
        // },
        //   //修改
        //   handleClickUpdate:function (data,index) {
        //       this.$router.push({name:'questionadd',path: '/admin/questionadd',params:{questionDetail:data}});
        //   },
        goBack:function () {
          this.$router.back()
        },
        arraySpanMethod: function( row ) {
          return row.acnumber / ( row.acnumber + row.ernumber)
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

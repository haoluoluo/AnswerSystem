<template>
  <div class="contenet">
    <div class="title">
      <div class="title_name">
      <h1>学生管理</h1>
        <el-button style="margin-top: .2rem;" @click="addBusiness" type="primary" size="small">新增考生</el-button>
      </div>
      <div class="title_queryOne">
        <el-form :inline="true" :model="formModel" class="demo-form-inline">
          <el-form-item label="用户名">
            <el-input  placeholder="用户名" v-model="formModel.name"></el-input>
          </el-form-item>
          <!--<el-form-item label="注册时间">-->
            <!--<el-col :span="10">-->
              <!--<el-date-picker type="date" v-model="formModel.startTime" placeholder="选择日期" style="width: 100%;"></el-date-picker>-->
            <!--</el-col>-->
            <!--<el-col class="line" :span="1" :offset="1">～</el-col>-->
            <!--<el-col :span="10" :offset="1">-->
              <!--<el-date-picker type="date" v-model="formModel.endTime" placeholder="选择日期" style="width: 100%;"></el-date-picker>-->
            <!--</el-col>-->
          <!--</el-form-item>-->
          <el-form-item>
            <el-button type="primary" @click="onSubmitQuery">查询</el-button>
            <el-button type="primary" @click="getBussinessList(null)">显示全部</el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>
    <el-table v-loading="isLoading" :data="dataList?dataList.slice((currentPage-1)*pagesize,currentPage*pagesize):null" max-height="660" border>
      <el-table-column align="center" prop="userno"  label="用户ID" width="80"></el-table-column>
      <!--<el-table-column align="center"  prop="organ"  label="organ ID" width="180"></el-table-column>-->
      <el-table-column align="center"  prop="username"  label="名称" width="200"></el-table-column>
      <el-table-column align="center"  prop="email"  label="登录名称" width="300"></el-table-column>
      <el-table-column align="center"  prop="status"  label="状态" width="180"></el-table-column>
      <!--<el-table-column align="center" prop="createTime" label="注册时间" width="180"></el-table-column>-->
      <el-table-column align="center" label="操作">
        <template slot-scope="scope">
          <el-button @click="handleUpdate(scope.row,scope.$index)" type="text" size="small">修改</el-button>
          <el-button @click="handleClickDelete(scope.row,scope.$index)" type="text" size="small">删除</el-button>
          <el-button @click="handleClickLook(scope.row,scope.$index)" type="text" size="small">查看成绩</el-button>
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
        name: "usermanager",
      data() {
        return {
          isLoading:true,
          currentPage: 1,
          pagesize:10,
          totalSize:20,
          dataList:null,
          formModel:{
            startTime:'',
            endTime:'',
            name:''
          },
        };
      },
      mounted:function () {
        this.getBussinessList(null)
      },
      methods: {
        addBusiness:function () {
           this.$router.push({name:'userUpdate'})
        },
        handleSizeChange(val) {

        },
        handleCurrentChange(val) {

        },
        formatterNickName:function (row) {
          if (row.nickName){return row.nickName}
          return '未填写'
        },
        formatterRealkName:function (row) {
          if (row.userName){return row.userName}
          return '未填写'
        },
        getBussinessList:function (obj) {
          var self = this
          this.webrequest('get','/user/userInfo',obj,function (data) {
            self.isLoading = false
            self.dataList = data
            self.totalSize = data.length
          },function (error) {

          });
        },
        //查询
        onSubmitQuery:function () {
            this.getBussinessList(this.formModel)
        },
        //删除
        handleClickDelete:function (row,index) {
          var self = this;
          this.$confirm('此操作将删除该用户, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            var param = {
              id:row.userno
            }
            this.webrequest('delete','/user/deleteUser',param,function (data) {
              self.getBussinessList(null)
              self.$message({
                type: 'success',
                message: '删除成功!'
              });
            },function (error) {

            });
          }).catch(() => {

          });
        },
        //修改
        handleUpdate:function (row,index) {
          this.$router.push({name:'userUpdate',path:'/admin/userUpdate',params:{userDetail:{userno:row.userno,username:row.username,password:''}}});
        },

        handleClickLook:function (data,index) {
          this.$router.push({name: 'recordsMgr', path: '/admin/recordsMgr', params: {userno: data.userno}})
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
    margin-bottom: .1rem;
    width: 100%;
    display: flex;
    flex-direction: row;
  }
  .title_name{
    flex: 0 0 10%;
  }
  .title_queryOne{
    flex:0 0 90%;
  }
</style>

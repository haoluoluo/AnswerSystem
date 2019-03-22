<template>
  <div class="contenet">
    <div class="title">
      <div class="title_left">
        <h1>试卷管理</h1>
      </div>
      <div class="title_right">
        <el-button type="primary" @click="addServer">新建试卷</el-button>
      </div>
    </div>
    <el-table
      v-loading="isLoading"
      :data="
        dataList
          ? dataList.slice((currentPage - 1) * pagesize, currentPage * pagesize)
          : null
      "
      max-height="660"
      border
    >
      <el-table-column
        align="center"
        prop="id"
        label="序号"
        width="80"
      ></el-table-column>
      <el-table-column
        align="center"
        prop="name"
        label="试卷名称"
        width="200"
      ></el-table-column>
      <el-table-column
        align="center"
        prop="describe"
        label="描述"
        width="300"
      ></el-table-column>
       <el-table-column
        align="center"
        prop="time"
        label="考试时长(分钟)"
        width="300"
      ></el-table-column>
      <el-table-column
        align="center"
        :formatter="formatterStatusType"
        prop="status"
        label="状态"
        width="100"
      ></el-table-column>
      <el-table-column align="center" label="操作">
        <template slot-scope="scope">
          <el-button
            @click="handleClickUpdate(scope.row, scope.$index)"
            type="text"
            size="small"
            >修改</el-button
          >
          <el-button
            @click="handleClickDelete(scope.row, scope.$index)"
            type="text"
            size="small"
            >删除</el-button
          >
          <el-button
            @click="handleClickLook(scope.row, scope.$index)"
            type="text"
            size="small"
            >管理题目</el-button
          >
          <el-button
            @click="handleClickLookRecord(scope.row, scope.$index)"
            type="text"
            size="small"
            >查看成绩</el-button
          >
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
        :total="totalSize"
      >
      </el-pagination>
    </div>
  </div>
</template>

<script>
export default {
  name: "servermanager",
  data() {
    return {
      isLoading: true,
      currentPage: 1,
      pagesize: 10,
      totalSize: 20,
      dataList: null,
      statusType: { "0": "无效", "1": "有效" }
      // serverType:{'10':'B端用户','20':'C端用户'},
      // timeType:{'10':'年','20':'个月','30':'日'}
    };
  },
  mounted: function() {
    this.getServiceList();
  },
  methods: {
    formatterTimeType: function(type) {
      return this.timeType[type];
    },
    formatterType: function(rowData) {
      return this.serverType[rowData.shopType];
    },
    formatterStatusType: function(rowData) {
      return this.statusType[rowData.status];
    },
    handleSizeChange(val) {},
    handleCurrentChange(val) {},
    formatterTimeLimit: function(row) {
      return row.duration + this.timeType[row.durationUnit];
    },
    getServiceList: function(shopType) {
      var self = this;
      // var param = {
      //   shopType:shopType
      // }
      this.webrequest(
        "get",
        "/exam/getExamAllInfo",
        null,
        function(data) {
          self.isLoading = false;
          self.dataList = data;
          self.totalSize = data.length;
        },
        function(error) {}
      );
    },
    addServer: function() {
      this.$router.push({ name: "examadd" });
    },
    //查看
    handleClickLook: function(data, index) {
      this.$router.push({
        name: "questionMgr",
        path: "/admin/questionMgr",
        params: data
      });
    },
    //查看考试情况
    handleClickLookRecord: function(data, index) {
      this.$router.push({
        name: "recordsMgr",
        path: "/admin/recordsMgr",
        params: { examination: data.id }
      });
    },
    //s删除
    handleClickDelete: function(data, index) {
      this.$confirm("此操作将删除该试卷, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          var self = this;
          var param = {
            id: data.id
          };
          this.webrequest(
            "delete",
            "/exam/removeExamById",
            param,
            function(data) {
              self.$message({
                type: "success",
                message: "删除成功!"
              });
              self.getServiceList(10);
            },
            function(error) {}
          );
        })
        .catch(() => {});
    },
    //修改
    handleClickUpdate: function(data, index) {
      this.$router.push({ name: "examadd", params: { serverDetail: data } });
    }
  }
};
</script>

<style scoped>
.contenet {
  width: 100%;
  height: 100%;
}
.title {
  margin-top: 0.2rem;
  text-align: left;
  margin-bottom: 0.2rem;
  display: flex;
  flex-direction: row;
}
.title_left {
  flex: 0 0 10%;
  display: flex;
  align-items: center;
}
.title_right {
  flex: 0 0 90%;
  display: flex;
  align-items: center;
}
</style>

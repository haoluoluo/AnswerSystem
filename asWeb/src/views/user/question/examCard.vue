<template lang="html">
  <div class="exam-card">
    <div class="card-header clearfix">
      <div class="time f-l">
        <i class="el-icon-time"></i>
        <span v-cloak>{{ hour }}:{{ min }}:{{ sec }}</span>
      </div>
      <div class="answer-card f-l m-l-20" @click="dialogVisible = true">
        <i class="el-icon-date"></i>
        <span>答题卡</span>
      </div>
      <div class="submit-paper f-r">
        <el-button type="primary" round @click="submitPaper" :disabled="disSubmit">交卷</el-button>
      </div>
    </div>
    <div class="card-content">
      <el-card class="box-card">
        <div slot="header" class="clearfix">
          <span>第{{ questionId+1 }}题（{{ questionTypeText }}）</span>
          <el-button-group style="float: right;">
          <el-button
            type="primary"
            :disabled="disabledPre"
            icon="el-icon-arrow-left"
            @click="preQuestion"
          >上一题</el-button>
           <el-button
            type="primary"
            @click="nextQuestion"
            :disabled="disabledNext"
          >下一题<i class="el-icon-arrow-right el-icon--right"></i>
          </el-button>
          </el-button-group>
        </div>
        <el-form :model="form" ref="numberValidateForm" label-width="100px" >
        <div>
          <!-- 单选 -->
          <div class="q-single" v-show="questionType == 1">
            <div class="question">
              {{ currentQuestion.title }}
            </div>
            <div class="answer">
              <el-radio-group v-model="form.radio">
                <el-radio
                  v-for="(item, index) in currentQuestion.items" :label="questionIndex[index]"
                  style="display:block; margin-top: 10px"
                >{{transformChar[index]}}.{{item.name}}</el-radio>
              </el-radio-group>
            </div>
          </div>

          <!-- 多选 -->
          <div class="q-multiple" v-show="questionType == 2">
            <div class="question">
              {{ currentQuestion.title }}
            </div>
            <div class="answer">
              <el-checkbox-group v-model="form.checked">
                <el-checkbox
                  v-for="(item, index) in currentQuestion.items" :label="questionIndex[index]"
                  style="display:block; margin-top: 10px">
                  {{transformChar[index]}}.{{item.name}}
                </el-checkbox>
              </el-checkbox-group>
            </div>
          </div>
          <div class="q-true-or-false" v-show="questionType == 3">
            <div class="question">
              {{ currentQuestion.title }}
            </div>
            <div class="answer">
              <el-radio-group v-model="form.radioTF">
              <div class="answer-item">
                <el-radio label="1">正确</el-radio>
              </div>
              <div class="answer-item">
                <el-radio label="2">错误</el-radio>
              </div>
              </el-radio-group>
            </div>
          </div>

          <!-- 填空 -->
          <!--<div class="q-fill-in" v-show="questionType == 4">-->
            <!--<div class="question">-->
              <!--{{ currentQuestion.title}}-->
            <!--</div>-->
            <!--<div class="answer">-->
              <!--<div class="answer-item">-->
                <!--<el-input-->
                  <!--type="textarea"-->
                  <!--:rows="5"-->
                  <!--placeholder="请输入答案"-->
                  <!--v-model="textarea"-->
                <!--&gt;-->
                <!--</el-input>-->
              <!--</div>-->
            <!--</div>-->
          <!--</div>-->

          <!-- 简答 -->
          <!--<div class="q-short-answer" v-show="questionType == 5">-->
            <!--<div class="question">-->
              <!--{{ currentQuestion.title}}-->
            <!--</div>-->
            <!--<div class="answer">-->
              <!--<div class="answer-item">-->
                <!--<el-input-->
                  <!--type="textarea"-->
                  <!--:rows="15"-->
                  <!--placeholder="请输入答案"-->
                  <!--v-model="textarea"-->
                <!--&gt;-->
                <!--</el-input>-->
              <!--</div>-->
            <!--</div>-->
          <!--</div>-->

          <!-- 程序 -->
          <!--<div class="q-program" v-show="questionType == 6">-->
            <!--<div class="question">-->
              <!--{{ currentQuestion.title}}-->
            <!--</div>-->
            <!--<div class="answer">-->
              <!--<div class="answer-item">-->
                <!--<el-input-->
                  <!--type="textarea"-->
                  <!--:rows="15"-->
                  <!--placeholder="请输入答案"-->
                  <!--v-model="textarea"-->
                <!--&gt;-->
                <!--</el-input>-->
              <!--</div>-->
            <!--</div>-->
          <!--</div>-->
        </div>
        </el-form>
      </el-card>
    </div>
    <el-dialog title="答题卡" :visible.sync="dialogVisible" width="50%">
      <el-button
        v-for="(item, index) in questionList"
        key="index"
        type="primary"
        :class="{'stu-answer-item':true, 'answered':checkAnswered[index]}"
        @click="clickAnswerCard(index)"
        >{{ index + 1 }}</el-button
      >
    </el-dialog>
    <el-dialog
  title="考试结果"
  :visible.sync="dialogResult"
  width="50%"
  @close="closeResult"
  center>
      <el-card class="box-card">
        <div slot="header" class="clearfix">
          <span>考试结果</span>
        </div>
        <div class="text item">
         考卷名称:  {{examName}}
        </div>
        <div class="text item">
          考生:  {{username}}
        </div>
        <div class="text item">
          分数:  {{resultDate.scope}}
        </div>
      </el-card>
</el-dialog>
  </div>
</template>

<script>
import { Message, MessageBox } from "element-ui";
import httpServer from "@/util/httpServer/httpServer.js";

export default {
  name:'examCard',
  data() {
    return {
      examName:this.$route.params.examName,
      disabledPre:false,
      disabledNext:false,
      disSubmit: false,
      dialogResult:false,
      dialogVisible: false,
      currentQuestion: {title:''},
      questionList: null,
      questionType: 1,
      form:{
        textarea: '',
        radio: '' ,
        checked: [],
        radioTF:''
      },
      checkAnswered:[],
      questionId: 0,
      examId: this.$route.params.examId,
      userId:localStorage.getItem("userId"),
      username:localStorage.getItem("email"),
      instId: 0,
      startTime: '',
      endTime: '',
      resultDate:{
        score:0,
      },
      questionIndex:[1,2,4,8,16,32,64,128,256,512,1024],
      transformChar: [
        'A',
        'B',
        'C',
        'D',
        'E',
        'F',
        'G',
        'H',
        'I',
        'J',
        'K',
        'L',
        'M',
        'N',
        'O',
        'P',
        'Q',
        'R',
        'S',
        'T',
        'U',
        'V',
        'W',
        'X',
        'Y',
        'Z'
      ],
      hour: 0,
      min: 0,
      sec: 0,
      timer: 0,
      onblurTime: 0 //记录时间
    };
  },
  computed: {
    questionTypeText: function() {
      switch (this.questionType) {
        case 4: //填空
          return "填空题";
          break;
        case 5: //简答
          return "简答题";
          break;
        case 6: //程序
          return "程序题";
          break;
        case 1: //选择
          return "单选题";
          break;
        case 3: //判断
          return "判断题";
          break;
        case 2: //多选
          return "多选题";
          break;
      }
    }
  },
  mounted:{

  },
  methods: {
    initExam() {
      let that = this;
      window.onblur = function() {
        that.onblurTime++;
        if (that.onblurTime == 2) {
          that.submitPaper();
          MessageBox("你已交卷", "提示", {
            confirmButtonText: "确定",
            type: "error"
          });
        } else {
          MessageBox(
            "考试期间不能离开此页面哦~这一次只是提醒，下次离开就会直接交卷了哦",
            "提示",
            {
              confirmButtonText: "确定",
              type: "error"
            }
          );
        }
      };
      this.timer = window.setInterval(() => {
        let timeLast = this.endTime - new Date().getTime();
        let hour = parseInt(timeLast / 3600000);
        let min = parseInt((timeLast % (3600000)) / (60000));
        let sec = parseInt(
          ((timeLast % (3600000)) % (60000)) / 1000
        );
        this.hour = hour < 10 ? "0" + hour : hour;
        this.min = min < 10 ? "0" + min : min;
        this.sec = sec < 10 ? "0" + sec : sec;

        if (this.hour == 0 && this.min == 0 && this.sec == 0) {
          window.clearInterval(this.timer);
          this.submitPaper();
        }
      }, 1000);

      this.questionId = parseInt(this.$route.params.questionId); //当前题号
      this.currentQuestion = this.questionList[this.$route.params.questionId];

      this.questionType = parseInt(this.currentQuestion.type);

      if (typeof this.currentQuestion.answer != "undefined") {
        this.setAnswer(this.questionType, this.currentQuestion.answer);
      }
    },
    submitPaper() {
      this.disSubmit=true
      window.onblur = null;
      this.saveAnswer(false);
      let answer = [];
      for (let i = 0; i < this.questionList.length; i++) {
        var temp = {question:'', answer:''};
        temp.question=this.questionList[i].id
        switch (this.questionList[i].type) {
          case 1: //选择
            temp.answer = this.questionList[i].answer
            break;
          case 2: //多选
            temp.answer = this.sumAns(this.questionList[i].answer)
            break;
          case 3: //判断
            if (this.questionList[i].answer == "1") {
              temp.answer = "1";
            } else {
              temp.answer = "2";
            }
            break;
          // case 4: //填空
          // case 5:
          // case 6:
          //   answer[i] = this.questionList[i].answer;
          //   break;
        }
        answer[i]=temp
      }
      var self = this
      var param = {
        examId: this.examId,
        user:this.user,
        username:this.username,
        answer:answer
      }
      self.webrequest('post','/exam/checkExam/',param,function (data) {
        self.resultDate = data
        self.dialogResult=true
        sessionStorage.removeItem("questions")
        sessionStorage.removeItem("startTime")
        sessionStorage.removeItem("endTime")
      },function (error) {
        self.$message.error('提交失败,请稍后提交');
        self.disSubmit=false
      });
      // httpServer({
      //   url : '/exam/submit_paper'
      // },{
      //   instId : this.instId,
      //   answer :answer
      // })
      // .then((res)=>{
      //   if(res.data.respCode == '1') {
      //     this.$router.push(`//homepage`);
      //   }
      // })
    },
    clickAnswerCard(index) {
      this.saveAnswer(false);
      this.dialogVisible = false;
      this.$router.push(`/examCard/${this.$route.params.examId}/${index}`);
    },
    saveAnswer(type) {
      let questionId = parseInt(this.$route.params.questionId);
      this.checkAnswered[questionId] = this.myCheckAnswered(questionId)
      let answer = "";
      switch (this.questionType) {
        case 1: //选择
          answer = this.form.radio;
          if (this.questionList.length - 1 != questionId && type) {
            this.form.radio = "";
            // this.checkAnswered[questionId] = false
          }
          break;
        case 2: //多选
          answer = this.form.checked;
          if (this.questionList.length - 1 != questionId && type) {
            this.form.checked = [];
            // this.checkAnswered[questionId] = false
          }
          break;
        case 3: //判断
          answer = ""+this.form.radioTF;
          if (this.questionList.length - 1 != questionId && type) {
            this.form.radio = "";
          }
          break;
        // case 4: //填空
        // case 5: //简答
        // case 6: //程序
        //   answer = this.textarea;
        //   if (this.questionList.length - 1 != questionId && type) {
        //     this.textarea = "";
        //   }
        //   break;
      }

      this.questionList[questionId].answer = answer;
    },
    nextQuestion() {
      let questionId = parseInt(this.$route.params.questionId);

      if (this.questionList.length - 1 == questionId) {
        Message.success({
          showClose: true,
          message: "这是最后一题啦",
          type: "success"
        });
        this.saveAnswer(true);
        return;
      }
      this.saveAnswer(true);
      questionId++;
      this.$router.push(
        `/examCard/${this.$route.params.examId}/${questionId}`
      );
    },
    preQuestion() {
      let questionId = parseInt(this.$route.params.questionId);

      if (0 == questionId) {
        Message.success({
          showClose: true,
          message: "这是第一题",
          type: "success"
        });
        return;
      }
      this.saveAnswer(true);
      questionId--;
      if (typeof this.questionList[questionId].answer != "undefined" && this.questionList[questionId].answer != null) {
        this.setAnswer(this.questionType, this.currentQuestion.answer);
      }
      this.$router.push(
        `/examCard/${this.$route.params.examId}/${questionId}`
      );
    },
    setAnswer(questionType, answer) {
      switch (questionType) {
        case 1: //选择
          this.form.radio = parseInt(answer);
          break;
        case 2: //多选
          this.form.checked = answer;
          break;
        case 3: //判断
          this.form.radioTF = answer;
          break;
        // case 4: //填空
        // case 5: //简答
        // case 6: //程序
        //   this.textarea = answer;
        //   break;
      }
    },
    sumAns: function (answers) {
      var ret = 0;
      for(var i=0; i<answers.length;i++){
          ret += answers[i];
      }
      return ret;
    },
    // splitAns: function (answer) {
    //   answer = parseInt(answer)
    //   var ret = [];
    //   var temp = 1;
    //   while(answer>=temp){
    //     if((answer & temp) != 0){
    //       ret.push(true);
    //     }else {
    //       ret.push(false)
    //     }
    //     temp = temp * 2;
    //   }
    //   return ret;
    // },
    myCheckAnswered: function (index) {
      return typeof this.questionList[index].answer != 'undefined' && this.questionList[index].answer != null && this.questionList[index].answer != '';
    },
    closeResult: function () {
      this.$router.push({path: '/home'})
    }
  },
  created() {
    // 提示用户是否离开此页面（关闭、刷新或者点击后退等）
    window.addEventListener("beforeunload", () => {
        sessionStorage.setItem('questions', JSON.stringify(this.questionList));
    });
    if (typeof sessionStorage.getItem('questions') == "undefined" || sessionStorage.getItem('questions') == null) {
      var self = this
      self.webrequest('get','/exam/exam/'+self.examId,null,function (data) {
        let respData = data;
        self.questionList = respData.questions;
        self.startTime = new Date().getTime();
        self.endTime = self.startTime + Number(60000)*parseInt(respData.time)
        sessionStorage.setItem('startTime', self.startTime)
        sessionStorage.setItem('endTime', self.endTime)
        self.initExam();
      },function (error) {
        self.$message.error('试卷获取失败');
      });
      }else {
        this.questionList = JSON.parse(sessionStorage.getItem('questions'))
        this.startTime = sessionStorage.getItem('startTime')
        this.endTime = sessionStorage.getItem('endTime');
        this.initExam();
    }
  },
  watch: {
    $route(to, from) {
      // this.checkAnswered()
      this.questionId = parseInt(this.$route.params.questionId);
      this.currentQuestion = this.questionList[this.$route.params.questionId];
      this.questionType = parseInt(this.currentQuestion.type);
      if (typeof this.currentQuestion.answer != "undefined" && this.currentQuestion.answer != null) {
        this.setAnswer(this.questionType, this.currentQuestion.answer);
      }
    }
  }
};
</script>

<style lang="css">
[v-cloak] {
  display: none;
}
.card-header {
  padding: 10px;
  font-size: 20px;
  margin-top: 30px
}
.answer-card {
  cursor: pointer;
}
.time,.answer-card{
  margin-top: 10px;
}
.answer-item {
  padding: 10px;
}
.question {
  padding: 10px 0;
}
.card-content {
  width: 90%;
  margin: 0 auto;
  margin-top: 40px;
}

.card-content .box-card {
  min-height: 500px;
}

.el-button.stu-answer-item {
  margin: 10px;
  width: 60px;
  height: 60px
}
  .answered{
    background-color: grey;
  }
</style>

package com.luoluohao.answer_system.x.as.question.controller;

import com.luoluohao.answer_system.util.ViewJSON;
import com.luoluohao.answer_system.x.as.question.dto.form.AnsView;
import com.luoluohao.answer_system.x.as.question.dto.model.AnsModel;
import com.luoluohao.answer_system.x.as.question.dto.model.QuestionExt;
import com.luoluohao.answer_system.x.as.question.service.IExaminationService;
import com.luoluohao.answer_system.x.as.question.service.IItemsService;
import com.luoluohao.answer_system.x.as.question.service.IQuestionService;
import com.luoluohao.answer_system.x.as.sys.controller.ConfigurationController;
import com.luoluohao.answer_system.x.as.sys.dto.entity.FieldValue;
import com.luoluohao.answer_system.x.as.sys.dto.model.FieldNameModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@EnableAutoConfiguration
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    private IExaminationService examinationService;

    @Autowired
    private IQuestionService questionService;

    @Autowired
    private IItemsService itemsService;

    @Autowired
    private ConfigurationController configurationController;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @ResponseBody
    @RequestMapping(value = "/removeQuestion", method = RequestMethod.DELETE)
    public ViewJSON removeQuestion(
            @RequestBody QuestionExt questionExt
    ){
        try {
            questionService.removeQuestion(questionExt);
        }catch (Exception e){
            logger.debug(e.getMessage());
            return new ViewJSON("400", "服务器错误");
        }
        return new ViewJSON("200", "添加成功");
    }
    @ResponseBody
    @RequestMapping(value = "/addQuestion", method = RequestMethod.PUT)
    public ViewJSON addQuestion(
            @RequestBody QuestionExt questionExt
    ){
        try {
            FieldNameModel questionType = configurationController.getFieldName("questionType");
            String parse =  configurationController.getFieldValue("questionType", questionExt.getType()).getParse();
            questionExt.setTypeparse(parse);
            boolean i = questionService.addQuestion(questionExt);
            if(!i){
                return new ViewJSON("400", "服务器错误");
            }
        }catch (Exception e){
            logger.debug(e.getMessage());
            return new ViewJSON("400", "服务器错误");
        }
        return new ViewJSON("200", "添加成功");
    }

    /**
     * 更新
     * @param questionExt
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updateQuestion", method = RequestMethod.POST)
    public ViewJSON updateQuestion(
            @RequestBody QuestionExt questionExt
    ){
        try {
            boolean b = questionService.updateQuestion(questionExt);
            if(!b){
                return new ViewJSON("400", "更新失败");
            }
        }catch (Exception e){
            logger.debug(e.getMessage());
            return new ViewJSON("400", "服务器错误");
        }
        return new ViewJSON("200", "添加成功");
    }

    /**
     * 获取某一试卷的所有题目
     * @param id
     * @return
     */
    @GetMapping("/getQuestionInfo/{id}")
    @ResponseBody
    public ViewJSON getQuestionByExamId(@PathVariable("id") Integer id){
        List<QuestionExt> listsExt = null;
        try{
            listsExt = questionService.getQuestionByExamWithItems(id);
        }catch (Exception e){
            logger.debug(e.getMessage());
            e.printStackTrace();
            return new ViewJSON("400", "服务器错误");
        }
        return new ViewJSON("200","查询成功",listsExt);
    }
}


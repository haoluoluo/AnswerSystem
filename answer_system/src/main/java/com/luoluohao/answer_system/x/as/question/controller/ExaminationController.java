package com.luoluohao.answer_system.x.as.question.controller;

import com.luoluohao.answer_system.util.ViewJSON;
import com.luoluohao.answer_system.x.as.question.dto.entity.*;
import com.luoluohao.answer_system.x.as.question.dto.form.AnsView;
import com.luoluohao.answer_system.x.as.question.dto.form.SubmitAns;
import com.luoluohao.answer_system.x.as.question.dto.model.AnsModel;
import com.luoluohao.answer_system.x.as.question.dto.model.ExaminationExt;
import com.luoluohao.answer_system.x.as.question.dto.model.QuestionExt;
import com.luoluohao.answer_system.x.as.question.dto.view.ExaminationView;
import com.luoluohao.answer_system.x.as.question.dto.view.ItemsView;
import com.luoluohao.answer_system.x.as.question.dto.view.QuestionView;
import com.luoluohao.answer_system.x.as.question.service.IExaminationService;
import com.luoluohao.answer_system.x.as.question.service.IItemsService;
import com.luoluohao.answer_system.x.as.question.service.IQuestionService;
import com.luoluohao.answer_system.x.as.records.controller.RecordsController;
import com.luoluohao.answer_system.x.as.records.dto.entity.Records;
import com.luoluohao.answer_system.x.as.user.controller.UserController;
import com.luoluohao.answer_system.x.as.user.dto.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@EnableAutoConfiguration
@RequestMapping("/exam")
public class ExaminationController {
    @Autowired
    private IExaminationService examinationService;

    @Autowired
    private IQuestionService questionService;

    @Autowired
    private IItemsService itemsService;

    @Autowired
    private UserController userController;

    @Autowired
    private RecordsController recordsController;


    Logger logger = LoggerFactory.getLogger(this.getClass());

    @ResponseBody
    @RequestMapping(value = "/checkExam", method = RequestMethod.POST)
    public ViewJSON checkExam(
            @RequestBody SubmitAns submitAns
            ){
        List<AnsView> ansView = submitAns.getAnswer();
        Map<Integer, AnsModel> ans = questionService.getAns(submitAns.getExamId());
        Records records = new Records();
        records.setUser(submitAns.getUser());
        records.setExamination(submitAns.getExamId());
        records.setAcnumber(0);
        records.setErnumber(0);
        records.setScope(0);
        records.setUsername(submitAns.getUsername());
        for(AnsView as : ansView){
            //正确答案实体
            AnsModel ansModel = ans.get(as.getQuestion());
            boolean equals = as.getAnswer().equals(ansModel.getAnswer());
            if(equals){
                records.setScope(records.getScope()+ansModel.getScore());
                records.setAcnumber(records.getAcnumber()+1);
            }else {
                records.setErnumber(records.getErnumber()+1);
            }
        }
//        recordsController.addRecordsWTAns(records);
        return new ViewJSON("200","计算成功",records);
    }
    @ResponseBody
    @RequestMapping(value = "/removeExamById", method = RequestMethod.DELETE)
    public ViewJSON removeExamById(
            @RequestParam Integer id
    ){
        int i = 0;
        try{
            i = examinationService.deleteByPrimaryKey(id);
            System.out.println("id:"+id+"i:"+i);
        }catch (Exception e){
            e.printStackTrace();
            logger.debug(e.getMessage());
            System.out.println(e.getMessage());
        }
        if(i<=0){
            return new ViewJSON("400","删除失败");
        }
        return new ViewJSON("200","删除成功");
    }
    /**
     * 添加
     * @param examination
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addExam", method = RequestMethod.PUT)
    public ViewJSON addExam(
           @RequestBody Examination examination
    ){
        try{
            Examination insert = examinationService.insert(examination);
        }catch (Exception e){
            logger.debug(e.getMessage());
            return new ViewJSON("400", "服务器错误");
        }
        return new ViewJSON("200", "添加成功");
    }
    /**
     * 更新
     * @param examination
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updateExam", method = RequestMethod.POST)
    public ViewJSON updateExam(
            @RequestBody Examination examination
    ){
        try{

            Examination examination1 = examinationService.updateByPrimaryKeySelective(examination);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ViewJSON("400","服务器错误");
        }
        return new ViewJSON("200","更新成功");
    }

    /**
     * 获取所有试题
     * @return
     */
    @GetMapping("/getExamInfo")
    @ResponseBody
    public ViewJSON<List<Examination>> getExam(
           Integer user
    ){
        List<Examination> examinations = examinationService.findUntestExam(user);
        return new ViewJSON<List<Examination>>("200","查询成功",examinations);
    }
    /**
     * 获取所有试题
     * @return
     */
    @GetMapping("/getExamAllInfo")
    @ResponseBody
    public ViewJSON<List<Examination>> getExamAll(
    ){
        List<Examination> examinations = examinationService.selectAll();
        return new ViewJSON<List<Examination>>("200","查询成功",examinations);
    }
    /**
     * 获取某一试卷
     * @param id
     * @return
     */
    @GetMapping("/exam/{id}")
    @ResponseBody
    public ViewJSON getExamById(@PathVariable("id") Integer id){
        Examination examination = examinationService.getExamById(id);
        if(examination == null || examination.getId() == null){
            return null;
        }

        ExaminationView examinationView = new ExaminationView();
        BeanUtils.copyProperties(examination, examinationView);
        logger.debug(examinationView.getName());
        List<QuestionExt> questionExt = questionService.getQuestionByExamWithItems(id);

        //整理成viewModel
        List<QuestionView> questionViews = new ArrayList<>();
        for(QuestionExt question : questionExt){
            QuestionView questionView = new QuestionView();
            BeanUtils.copyProperties(question,questionView);
            List<ItemsView> itemsViews = new ArrayList<>();
            for(Items item : question.getItems()){
                ItemsView itemView = new ItemsView();
                BeanUtils.copyProperties(item,itemView);
                itemsViews.add(itemView);
            }
            questionView.setItems(itemsViews);
            questionViews.add(questionView);
        }
        examinationView.setQuestions(questionViews);
        logger.debug("分发试卷:"+examinationView);
        return new ViewJSON("200","查询成功",examinationView);
    }
}


package com.luoluohao.answer_system.x.as.question.controller;

import com.luoluohao.answer_system.x.as.question.dto.model.ExaminationExt;
import com.luoluohao.answer_system.x.as.question.service.IExaminationService;
import com.luoluohao.answer_system.x.as.question.service.IItemsService;
import com.luoluohao.answer_system.x.as.question.service.IQuestionService;
import com.luoluohao.answer_system.x.as.question.utils.GetExam;
import org.springframework.beans.factory.annotation.Autowired;

public class SimpleGetExam implements GetExam {
    @Autowired
    private IExaminationService examinationService;

    @Autowired
    private IQuestionService questionService;

    @Autowired
    private IItemsService itemsService;
    @Override
    public ExaminationExt getExam(Integer id) {
//        ExaminationExt examinationExt = new ExaminationExt();
//        Examination examination = examinationService.getExamById(id);
//        if(examination == null || examination.getId() == null){
//            return null;
//        }
//        BeanUtils.copyProperties(examination, examinationExt);
//        List<Question> questions = questionService.getQuestionByExam(examination.getId());
//        List<QuestionExt> questionExts = new ArrayList<QuestionExt>();
//        for(Question question : questions){
//            List<Items> items = itemsService.getItemsByQue(question.getId());
//            QuestionExt questionExt = new QuestionExt();
//            questionExt.setItems(items);
//            questionExts.add(questionExt);
//        }
//        examinationExt.setQuestions(questionExts);
//        return examinationExt;
        return null;
    }
}

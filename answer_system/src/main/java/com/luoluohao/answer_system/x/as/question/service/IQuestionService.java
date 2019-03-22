package com.luoluohao.answer_system.x.as.question.service;

import com.luoluohao.answer_system.web.mvc.IBaseService;
import com.luoluohao.answer_system.x.as.question.dto.entity.Question;
import com.luoluohao.answer_system.x.as.question.dto.model.AnsModel;
import com.luoluohao.answer_system.x.as.question.dto.model.QuestionExt;

import java.util.List;
import java.util.Map;

public interface IQuestionService extends IBaseService<Question, Integer> {
    List<Question> getQuestionByExam(Integer id);

    List<QuestionExt> getQuestionByExamWithItems(Integer id);

    boolean updateQuestion(QuestionExt questionExt);

    boolean addQuestion(QuestionExt questionExt);

    int removeQuestion(QuestionExt questionExt);

    Map<Integer, AnsModel> getAns(Integer examid);
}

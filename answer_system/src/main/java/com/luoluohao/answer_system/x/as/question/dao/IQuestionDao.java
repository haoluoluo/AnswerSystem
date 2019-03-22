package com.luoluohao.answer_system.x.as.question.dao;

import com.luoluohao.answer_system.web.mvc.IBaseDao;
import com.luoluohao.answer_system.x.as.question.dto.entity.Question;
import com.luoluohao.answer_system.x.as.question.dto.model.QuestionExt;

import java.util.List;

public interface IQuestionDao extends IBaseDao<Question, Integer> {

    List<QuestionExt> selectQuestionsByExam(Integer exam);
    List<QuestionExt> selectQuestionsByExamHIGH(Integer exam);
}

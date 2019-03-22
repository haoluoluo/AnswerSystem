package com.luoluohao.answer_system.x.as.question.dao.mapper;

import com.luoluohao.answer_system.web.mvc.CommonMapper;
import com.luoluohao.answer_system.x.as.question.dao.IQuestionDao;
import com.luoluohao.answer_system.x.as.question.dto.entity.Question;
import org.springframework.stereotype.Repository;

@Repository("questionDao")
public interface QuestionMapper extends IQuestionDao, CommonMapper<Question> {
}
package com.luoluohao.answer_system.x.as.question.service;

import com.luoluohao.answer_system.web.mvc.IBaseService;
import com.luoluohao.answer_system.x.as.question.dto.entity.Examination;

import java.util.List;

public interface IExaminationService extends IBaseService<Examination, Integer> {
    Examination getExamById(Integer id);

    //查询某一用户的未考题目
    List<Examination> findUntestExam(Integer user);
}

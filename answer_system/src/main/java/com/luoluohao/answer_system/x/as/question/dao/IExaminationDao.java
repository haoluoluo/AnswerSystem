package com.luoluohao.answer_system.x.as.question.dao;

import com.luoluohao.answer_system.web.mvc.IBaseDao;
import com.luoluohao.answer_system.x.as.question.dto.entity.Examination;

import java.util.List;

public interface IExaminationDao extends IBaseDao<Examination, Integer> {
    List<Examination> findUntestExam(Integer user);
}

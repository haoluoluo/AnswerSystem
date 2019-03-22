package com.luoluohao.answer_system.x.as.question.dao.mapper;

import com.luoluohao.answer_system.web.mvc.CommonMapper;
import com.luoluohao.answer_system.x.as.question.dao.IExaminationDao;
import com.luoluohao.answer_system.x.as.question.dto.entity.Examination;
import org.springframework.stereotype.Repository;

@Repository("examinationDao")
public interface ExaminationMapper extends IExaminationDao, CommonMapper<Examination> {
}
package com.luoluohao.answer_system.x.as.question.service.imp;

import com.luoluohao.answer_system.web.mvc.BaseService;
import com.luoluohao.answer_system.web.mvc.IBaseDao;
import com.luoluohao.answer_system.x.as.question.dao.IExaminationDao;
import com.luoluohao.answer_system.x.as.question.dto.entity.Examination;
import com.luoluohao.answer_system.x.as.question.service.IExaminationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("examinationService")
public class ExaminationServiceImp extends BaseService<Examination, Integer> implements IExaminationService{
    @Autowired
    private IExaminationDao examinationDao;
    @Override
    public Examination getExamById(Integer id) {
        return examinationDao.selectByPrimaryKey(id);
    }

    @Override
    public List<Examination> findUntestExam(Integer user) {
        return examinationDao.findUntestExam(user);
    }

    @Override
    public IBaseDao getBaseDao() {
        return examinationDao;
    }

}

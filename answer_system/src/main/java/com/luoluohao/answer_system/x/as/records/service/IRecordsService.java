package com.luoluohao.answer_system.x.as.records.service;


import com.luoluohao.answer_system.web.mvc.IBaseService;
import com.luoluohao.answer_system.x.as.records.dto.entity.Records;

import java.util.List;

public interface IRecordsService extends IBaseService<Records, Integer> {
    List<Records> selectByUserno(Integer userno);

    List<Records> selectByExam(Integer examination);
}

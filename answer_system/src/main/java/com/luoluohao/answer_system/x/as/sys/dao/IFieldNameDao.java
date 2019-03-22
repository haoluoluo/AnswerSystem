package com.luoluohao.answer_system.x.as.sys.dao;

import com.luoluohao.answer_system.web.mvc.IBaseDao;
import com.luoluohao.answer_system.x.as.sys.dto.entity.FieldName;
import com.luoluohao.answer_system.x.as.sys.dto.model.FieldNameModel;

import java.util.List;

public interface IFieldNameDao extends IBaseDao<FieldName, Integer> {
    List<FieldNameModel> selectAllFieldNames();
    List<FieldNameModel> selectAllFieldNamesHIGH();
}

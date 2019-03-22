package com.luoluohao.answer_system.x.as.sys.dao;

import com.luoluohao.answer_system.web.mvc.IBaseDao;
import com.luoluohao.answer_system.x.as.sys.dto.entity.FieldValue;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IFieldValueDao extends IBaseDao<FieldValue, Integer> {
    List<FieldValue> selectFieldValuesByFieldNameId(@Param("fnid") Integer fieldnameid);
}

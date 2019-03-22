package com.luoluohao.answer_system.x.as.question.dao;

import com.luoluohao.answer_system.web.mvc.IBaseDao;
import com.luoluohao.answer_system.x.as.question.dto.entity.Items;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IItemsDao extends IBaseDao<Items, Integer> {
    List<Items>  selectItemsByQuestionId(@Param("question") Integer question);
}

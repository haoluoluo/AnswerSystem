package com.luoluohao.answer_system.x.as.question.service;

import com.luoluohao.answer_system.web.mvc.IBaseService;
import com.luoluohao.answer_system.x.as.question.dto.entity.Items;

import java.util.List;

public interface IItemsService extends IBaseService<Items, Integer> {
    List<Items> getItemsByQue(Integer id);
}

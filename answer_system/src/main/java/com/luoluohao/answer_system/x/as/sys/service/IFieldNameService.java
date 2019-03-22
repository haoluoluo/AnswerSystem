package com.luoluohao.answer_system.x.as.sys.service;

import com.luoluohao.answer_system.web.mvc.IBaseService;
import com.luoluohao.answer_system.x.as.sys.dto.entity.FieldName;
import com.luoluohao.answer_system.x.as.sys.dto.model.FieldNameModel;

/**
 * TODO
 *
 * @Filename: com.abcdpower.x.iwill.sys.service.IFieldNameService.java
 * @Type: IFieldNameService
 * @Author: macbook[weide<weide001 @ gmail.com>]
 * @Created: 2018/9/21 上午12:51
 */
public interface IFieldNameService extends IBaseService<FieldName, Integer> {

    FieldNameModel getFieldName(String name);
}

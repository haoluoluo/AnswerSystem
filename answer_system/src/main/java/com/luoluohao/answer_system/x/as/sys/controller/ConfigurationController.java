package com.luoluohao.answer_system.x.as.sys.controller;

import com.luoluohao.answer_system.util.ViewJSON;
import com.luoluohao.answer_system.x.as.sys.dto.entity.FieldValue;
import com.luoluohao.answer_system.x.as.sys.dto.model.FieldNameModel;
import com.luoluohao.answer_system.x.as.sys.service.IFieldNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Path;
import java.util.List;

/**
 * 配置操作控制
 *
 * @Filename: com.abcdpower.x.iwill.sys.controller.ConfigurationController.java
 * @Type: ConfigurationController
 * @Author: macbook[weide<weide001                                                                                                                               @                                                                                                                               gmail.com>]
 * @Created: 2018/9/21 上午1:29
 * @Version: iwill.zt-service V1.0.0
 */
@RestController(value = "configController")
@EnableAutoConfiguration
@RequestMapping("/config")
public class ConfigurationController {

    @Autowired
    private IFieldNameService fieldNameService;


    /**
     * @Description 读取字段配置
     * @Param [name]
     * @Return com.abcdpower.x.iwill.sys.dto.model.FieldNameModel
     */
    public FieldNameModel getFieldName(String name) {
        return this.fieldNameService.getFieldName(name);
    }

    /**
     * 获取FieldName 下 的 某一value的只
     * @param name
     * @param value
     * @return
     */
    public FieldValue getFieldValue(String name ,Integer value){
        FieldNameModel fieldName = this.getFieldName(name);
        List<FieldValue> fieldValues = fieldName.getFieldValues();
        for (FieldValue f : fieldValues){
            if(f.getValue().equals(value)){
                return f;
            }
        }
        return null;
    }


    @ResponseBody
    @RequestMapping(value = "/select/{name}", method = RequestMethod.GET)
    public ViewJSON select(
                    @PathVariable("name") String name
    )
    {
        FieldNameModel fieldName = this.getFieldName(name);
        return new ViewJSON("200","成功",fieldName);
    }
}

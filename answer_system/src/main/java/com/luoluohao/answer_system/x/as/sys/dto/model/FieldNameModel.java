package com.luoluohao.answer_system.x.as.sys.dto.model;


import com.luoluohao.answer_system.x.as.sys.dto.entity.FieldName;
import com.luoluohao.answer_system.x.as.sys.dto.entity.FieldValue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * TODO
 *
 * @Filename: com.abcdpower.x.iwill.sys.dto.model.FieldNameModel.java
 * @Type: FieldNameModel
 * @Author: macbook[weide<weide001 @ gmail.com>]
 * @Created: 2018/9/21 上午1:08
 * @Version: iwill.zt-service V1.0.0
 */
public class FieldNameModel extends FieldName {

    //原始视图
    private List<FieldValue> fieldValues;

    //冗余：（但为了提升整体效率）
    //Tag视图
    private Map<String,FieldValue> tagMap;
    //Value视图
    private Map<Integer,FieldValue> valMap;

    public List<FieldValue> getFieldValues() {
        return fieldValues;
    }

    public void setFieldValues(List<FieldValue> fieldValues) {
        if (null==fieldValues) return;

        this.fieldValues = fieldValues;

        //先要清空
        //this.tagMap = new HashMap<>();
        //this.valMap = new HashMap<>();
        //再去缓存
        //for ( FieldValue fv : this.fieldValues ) {
        //    this.tagMap.put(fv.getTag(),fv);
        //    this.valMap.put(fv.getValue(),fv);
        //}
    }

    public FieldValue getValue(String tag) {
        if (null==this.tagMap && null!=this.fieldValues) {
            this.tagMap = new HashMap<>();
            for ( FieldValue fv : this.fieldValues ) {
                this.tagMap.put(fv.getTag(),fv);
            }
        }

        if (this.tagMap.isEmpty()) return null;

        return this.tagMap.get(tag);
    }

    public FieldValue getValue(Integer value) {
        if (null==this.valMap && null!=this.fieldValues) {
            this.valMap = new HashMap<>();
            for ( FieldValue fv : this.fieldValues ) {
                this.valMap.put(fv.getValue(),fv);
            }
        }

        if (this.valMap.isEmpty()) return null;

        return this.valMap.get(value);
    }
}

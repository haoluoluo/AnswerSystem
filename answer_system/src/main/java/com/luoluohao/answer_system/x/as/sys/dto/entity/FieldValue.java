package com.luoluohao.answer_system.x.as.sys.dto.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import javax.persistence.*;

@Getter
@Setter
@Table(name = "sys_field_value")
public class FieldValue {
    /**
     * 主键ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 枚举名称
     */
    @Column(name = "field_name")
    private Integer fieldName;

    /**
     * 标识串
     */
    private String tag;

    /**
     * 枚举值
     */
    private Integer value;

    /**
     * 枚举值的释义
     */
    private String parse;

    /**
     * 是否已被删除？
     */
    @Column(name = "is_deleted")
    private Boolean isDeleted;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 其他属性（可以是格式字符串，比如json）
     */
    private String properties;

    /**
     * 获取主键ID
     *
     * @return id - 主键ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键ID
     *
     * @param id 主键ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取枚举名称
     *
     * @return field_name - 枚举名称
     */
    public Integer getFieldName() {
        return fieldName;
    }

    /**
     * 设置枚举名称
     *
     * @param fieldName 枚举名称
     */
    public void setFieldName(Integer fieldName) {
        this.fieldName = fieldName;
    }

    /**
     * 获取标识串
     *
     * @return tag - 标识串
     */
    public String getTag() {
        return tag;
    }

    /**
     * 设置标识串
     *
     * @param tag 标识串
     */
    public void setTag(String tag) {
        this.tag = tag;
    }

    /**
     * 获取枚举值
     *
     * @return value - 枚举值
     */
    public Integer getValue() {
        return value;
    }

    /**
     * 设置枚举值
     *
     * @param value 枚举值
     */
    public void setValue(Integer value) {
        this.value = value;
    }

    /**
     * 获取枚举值的释义
     *
     * @return parse - 枚举值的释义
     */
    public String getParse() {
        return parse;
    }

    /**
     * 设置枚举值的释义
     *
     * @param parse 枚举值的释义
     */
    public void setParse(String parse) {
        this.parse = parse;
    }

    /**
     * 获取是否已被删除？
     *
     * @return is_deleted - 是否已被删除？
     */
    public Boolean getIsDeleted() {
        return isDeleted;
    }

    /**
     * 设置是否已被删除？
     *
     * @param isDeleted 是否已被删除？
     */
    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取其他属性（可以是格式字符串，比如json）
     *
     * @return properties - 其他属性（可以是格式字符串，比如json）
     */
    public String getProperties() {
        return properties;
    }

    /**
     * 设置其他属性（可以是格式字符串，比如json）
     *
     * @param properties 其他属性（可以是格式字符串，比如json）
     */
    public void setProperties(String properties) {
        this.properties = properties;
    }
}
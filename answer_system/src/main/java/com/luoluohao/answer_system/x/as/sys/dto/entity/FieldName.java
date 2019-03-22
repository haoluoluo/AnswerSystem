package com.luoluohao.answer_system.x.as.sys.dto.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "sys_field_name")
public class FieldName {
    /**
     * 主键ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 枚举的名称
     */
    private String name;

    /**
     * 取值的释义
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
     * 获取枚举的名称
     *
     * @return name - 枚举的名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置枚举的名称
     *
     * @param name 枚举的名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取取值的释义
     *
     * @return parse - 取值的释义
     */
    public String getParse() {
        return parse;
    }

    /**
     * 设置取值的释义
     *
     * @param parse 取值的释义
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
}
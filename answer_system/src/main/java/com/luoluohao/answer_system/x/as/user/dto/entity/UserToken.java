package com.luoluohao.answer_system.x.as.user.dto.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import javax.persistence.*;

@Getter
@Setter
@Table(name = "crm_user_token")
public class UserToken {
    /**
     * 主键ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 使用客户（外键customer表id）
     */
    private Integer user;

    /**
     * 用途（枚举：CustomerTokenUseageType = ModifyPassword | WeiBoContribution | ...）
     */
    @Column(name = "useage_type")
    private Integer useageType;

    /**
     * 口令
     */
    private String token;

    /**
     * 状态（枚举：CustomerTokenUseState = Unuse | Used）
     */
    private Integer state;

    @Column(name = "register_uuid")
    private String registerUuid;

    /**
     * 截止时间
     */
    @Column(name = "expire_time")
    private Date expireTime;

    /**
     * 属性（自定义格式，比如json）
     */
    private String property;

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
    public Long getId() {
        return id;
    }

    /**
     * 设置主键ID
     *
     * @param id 主键ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取使用客户（外键customer表id）
     *
     * @return user - 使用客户（外键customer表id）
     */
    public Integer getUser() {
        return user;
    }

    /**
     * 设置使用客户（外键customer表id）
     *
     * @param user 使用客户（外键customer表id）
     */
    public void setUser(Integer user) {
        this.user = user;
    }

    /**
     * 获取用途（枚举：CustomerTokenUseageType = ModifyPassword | WeiBoContribution | ...）
     *
     * @return useage_type - 用途（枚举：CustomerTokenUseageType = ModifyPassword | WeiBoContribution | ...）
     */
    public Integer getUseageType() {
        return useageType;
    }

    /**
     * 设置用途（枚举：CustomerTokenUseageType = ModifyPassword | WeiBoContribution | ...）
     *
     * @param useageType 用途（枚举：CustomerTokenUseageType = ModifyPassword | WeiBoContribution | ...）
     */
    public void setUseageType(Integer useageType) {
        this.useageType = useageType;
    }

    /**
     * 获取口令
     *
     * @return token - 口令
     */
    public String getToken() {
        return token;
    }

    /**
     * 设置口令
     *
     * @param token 口令
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * 获取状态（枚举：CustomerTokenUseState = Unuse | Used）
     *
     * @return state - 状态（枚举：CustomerTokenUseState = Unuse | Used）
     */
    public Integer getState() {
        return state;
    }

    /**
     * 设置状态（枚举：CustomerTokenUseState = Unuse | Used）
     *
     * @param state 状态（枚举：CustomerTokenUseState = Unuse | Used）
     */
    public void setState(Integer state) {
        this.state = state;
    }

    /**
     * 获取截止时间
     *
     * @return expire_time - 截止时间
     */
    public Date getExpireTime() {
        return expireTime;
    }

    /**
     * 设置截止时间
     *
     * @param expireTime 截止时间
     */
    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    /**
     * 获取属性（自定义格式，比如json）
     *
     * @return property - 属性（自定义格式，比如json）
     */
    public String getProperty() {
        return property;
    }

    /**
     * 设置属性（自定义格式，比如json）
     *
     * @param property 属性（自定义格式，比如json）
     */
    public void setProperty(String property) {
        this.property = property;
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

    public String getRegisterUuid() {
        return registerUuid;
    }

    public void setRegisterUuid(String registerUuid) {
        this.registerUuid = registerUuid;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }
}
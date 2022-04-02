package org.beyond.library.account.model.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * 用户表
 */
@Entity
@Table(name = "bla_user")
@DynamicInsert
@DynamicUpdate
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * 用户名
     */
    @Column(name = "username", nullable = false)
    private String username;

    /**
     * 密码
     */
    @Column(name = "password", nullable = false)
    private String password;

    /**
     * 邮箱
     */
    @Column(name = "email")
    private String email;

    /**
     * 注册时间
     */
    @Column(name = "register_time", nullable = false)
    private LocalDateTime registerTime;

    /**
     * 是否禁用
     */
    @Column(name = "is_disabled", nullable = false)
    private Boolean disabled;

    /**
     * 是否删除
     */
    @Column(name = "is_deleted", nullable = false)
    private Boolean deleted;

    /**
     * 创建人
     */
    @Column(name = "created_by", nullable = false)
    private Long createdBy;

    /**
     * 创建时间
     */
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    /**
     * 更新人
     */
    @Column(name = "modified_by", nullable = false)
    private Long modifiedBy;

    /**
     * 更新时间
     */
    @Column(name = "modified_at", nullable = false)
    private LocalDateTime modifiedAt;

    /**
     * id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * id
     */
    public Long getId() {
        return id;
    }

    /**
     * 用户名
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 用户名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 注册时间
     */
    public void setRegisterTime(LocalDateTime registerTime) {
        this.registerTime = registerTime;
    }

    /**
     * 注册时间
     */
    public LocalDateTime getRegisterTime() {
        return registerTime;
    }

    /**
     * 是否禁用
     */
    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    /**
     * 是否禁用
     */
    public Boolean isDisabled() {
        return disabled;
    }

    /**
     * 是否删除
     */
    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    /**
     * 是否删除
     */
    public Boolean isDeleted() {
        return deleted;
    }

    /**
     * 创建人
     */
    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * 创建人
     */
    public Long getCreatedBy() {
        return createdBy;
    }

    /**
     * 创建时间
     */
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * 创建时间
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * 更新人
     */
    public void setModifiedBy(Long modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    /**
     * 更新人
     */
    public Long getModifiedBy() {
        return modifiedBy;
    }

    /**
     * 更新时间
     */
    public void setModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    /**
     * 更新时间
     */
    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    @Override
    public String toString() {
        return "User{" +
            "id=" + id + '\'' +
            "username=" + username + '\'' +
            "password=" + password + '\'' +
            "email=" + email + '\'' +
            "registerTime=" + registerTime + '\'' +
            "disabled=" + disabled + '\'' +
            "deleted=" + deleted + '\'' +
            "createdBy=" + createdBy + '\'' +
            "createdAt=" + createdAt + '\'' +
            "modifiedBy=" + modifiedBy + '\'' +
            "modifiedAt=" + modifiedAt + '\'' +
            '}';
    }

}

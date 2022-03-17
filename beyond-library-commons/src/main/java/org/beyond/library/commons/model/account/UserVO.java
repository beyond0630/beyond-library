package org.beyond.library.commons.model.account;

import java.io.Serializable;
import java.util.Date;

public class UserVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 注册时间
     */
    private Date registerTime;

    /**
     * 是否禁用
     */
    private Boolean disabled;

    /**
     * 是否删除
     */
    private Boolean deleted;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(final Date registerTime) {
        this.registerTime = registerTime;
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(final Boolean disabled) {
        this.disabled = disabled;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(final Boolean deleted) {
        this.deleted = deleted;
    }

}

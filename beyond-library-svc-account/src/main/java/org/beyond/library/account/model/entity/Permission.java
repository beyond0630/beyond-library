package org.beyond.library.account.model.entity;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * 权限表
 */
@Entity
@Table(name = "bla_permission")
@DynamicInsert
@DynamicUpdate
public class Permission implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 权限编码
     */
    @Column(name = "code", nullable = false)
    private String code;

    /**
     * 权限名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 请求方法
     */
    @Column(name = "method", nullable = false)
    private String method;

    /**
     * 访问地址 Pattern
     */
    @Column(name = "pattern", nullable = false)
    private String pattern;

    /**
     * 是否允许匿名访问
     */
    @Column(name = "is_allow_anonymous", nullable = false)
    private Boolean allowAnonymous;

    /**
     * 是否禁用
     */
    @Column(name = "is_disabled", nullable = false)
    private Boolean disabled;

    /**
     * id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 权限编码
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 权限编码
     */
    public String getCode() {
        return code;
    }

    /**
     * 权限名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 权限名称
     */
    public String getName() {
        return name;
    }

    /**
     * 请求方法
     */
    public void setMethod(String method) {
        this.method = method;
    }

    /**
     * 请求方法
     */
    public String getMethod() {
        return method;
    }

    /**
     * 访问地址 Pattern
     */
    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    /**
     * 访问地址 Pattern
     */
    public String getPattern() {
        return pattern;
    }

    /**
     * 是否允许匿名访问
     */
    public void setAllowAnonymous(Boolean allowAnonymous) {
        this.allowAnonymous = allowAnonymous;
    }

    /**
     * 是否允许匿名访问
     */
    public Boolean isAllowAnonymous() {
        return allowAnonymous;
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

    @Override
    public String toString() {
        return "Permission{" +
            "id=" + id + '\'' +
            "code=" + code + '\'' +
            "name=" + name + '\'' +
            "method=" + method + '\'' +
            "pattern=" + pattern + '\'' +
            "allowAnonymous=" + allowAnonymous + '\'' +
            "disabled=" + disabled + '\'' +
            '}';
    }

}

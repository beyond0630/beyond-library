package org.beyond.library.account.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * 用户表
 */
@Data
@Entity
@Table(name = "bla_user")
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

}
